package acme.load

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import kotlin.system.measureTimeMillis

data class Transaction(
    val from: String,
    val to: String,
    val amount: Int
)

data class State(
    val serverUrl: String,
    val httpClient: HttpClient,
    val fromAddressMutexes: MutableMap<String, Mutex>,
    val mutexMapLock: Mutex
)

suspend fun runLoadTest(serverUrl: String, inputFile: String) {
    val state = State(
        serverUrl = serverUrl,
        httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build(),
        fromAddressMutexes = mutableMapOf(),
        mutexMapLock = Mutex()
    )

    println("🚀 Starting load test...")

    val transactions = loadTransactionsFromCsv(inputFile)

    println("Loaded ${transactions.size} transactions")

    val executionTime = measureTimeMillis {
        // Execute all transactions concurrently with proper synchronization:
        // no 2 FROM transactions will be executed in the same time.
        coroutineScope {
            transactions.map { transaction ->
                async {
                    executeTransactionWithLock(state, transaction)
                }
            }.awaitAll()
        }
    }

    println("Load test completed in ${executionTime}ms")
}

private suspend fun executeTransactionWithLock(state: State, transaction: Transaction) {
    // get/create mutex for this 'from' address
    val fromMutex = state.mutexMapLock.withLock {
        state.fromAddressMutexes.getOrPut(transaction.from) { Mutex() }
    }
    fromMutex.withLock {
        executeTransaction(state.httpClient, state.serverUrl, transaction)
    }
}

private suspend fun executeTransaction(httpClient: HttpClient, serverUrl: String, transaction: Transaction) {
    try {
        val requestBody = requestBodyOf(transaction)

        val request = HttpRequest.newBuilder()
            .uri(URI.create(serverUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()

        withContext(Dispatchers.IO) {
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

            when (response.statusCode()) {
                in 200..299 -> {
                    println("✅ Success: ${transaction.from} -> ${transaction.to} (${transaction.amount}) - Status: ${response.statusCode()}")
                }
                else -> {
                    println("❌ Error: ${transaction.from} -> ${transaction.to} (${transaction.amount}) - Status: ${response.statusCode()}, Body: ${response.body()}")
                }
            }
        }
    } catch (e: Exception) {
        println("☢️ Exception for ${transaction.from} -> ${transaction.to} (${transaction.amount}): ${e.message}")
    }
}

private fun requestBodyOf(transaction: Transaction): String {
    val requestBody = """
                {
                    "from": "${transaction.from}",
                    "to": "${transaction.to}",
                    "amount": ${transaction.amount}
                }
            """.trimIndent()
    return requestBody
}

private fun loadTransactionsFromCsv(inputFile: String): List<Transaction> {
    val file = File(inputFile)
    if (!file.exists()) {
        throw IllegalArgumentException("Input file '$inputFile' not found")
    }

    return file.readLines()
        .filter { it.isNotBlank() }
        .map { line ->
            val parts = line.split(",").map { it.trim() }
            if (parts.size != 3) {
                throw IllegalArgumentException("Invalid CSV line: $line")
            }

            val amount = parts[2].toIntOrNull()
                ?: throw IllegalArgumentException("Invalid amount in line: $line")

            Transaction(
                from = parts[0],
                to = parts[1],
                amount = amount
            )
        }
}

suspend fun main(args: Array<String>) {
//    if (args.isEmpty()) {
//        println("Usage: kotlin LoadTestApp <serverUrl> [inputFile]")
//        println("Example: kotlin LoadTestApp http://localhost:8080/api/transfers input.csv")
//        return
//    }

    val serverUrl = "http://127.0.0.1:8080/api/transactions"//args[0]
    val inputFile = if (args.size > 1) args[1] else "in/input.csv"

    try {
        runLoadTest(serverUrl, inputFile)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        e.printStackTrace()
    }
}