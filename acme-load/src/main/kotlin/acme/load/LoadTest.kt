package acme.load

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.net.http.HttpClient
import java.time.Duration
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

data class Transaction(
    val from: String,
    val to: String,
    val amount: Int
)

data class State(
    val transactionUrl: String,
    val totalAmountUrl: String,
    val httpClient: HttpClient,
    val fromAddressMutexes: MutableMap<String, Mutex>,
    val mutexMapLock: Mutex
)

data class Counters(
    val transactions: AtomicLong = AtomicLong(0),
    val errors: AtomicLong = AtomicLong(0),
    val retries: AtomicLong = AtomicLong(0)
)

suspend fun runLoadTest(transactionUrl: String, totalAmountUrl: String, inputFile: String) {
    val state = State(
        transactionUrl = transactionUrl,
        totalAmountUrl = totalAmountUrl,
        httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build(),
        fromAddressMutexes = mutableMapOf(),
        mutexMapLock = Mutex()
    )

    val counters = Counters()

    println("🚀 Starting load test...")

    val transactions = loadTransactionsFromCsv(inputFile)

    println("Loaded ${transactions.size} transactions")

    val totalAmountBefore = fetchTotalAmount(state.httpClient, state.totalAmountUrl)

    val executionTime = measureTimeMillis {
        // Execute all transactions concurrently with proper synchronization:
        // no 2 FROM transactions will be executed in the same time.
        coroutineScope {
            // send only ONE transaction (debugging)
//            transactions.first().let {
//                transaction ->
//                    async {
//                        executeTransactionWithLock(state, transaction)
//                    }.await()
//            }
            // send ALL transactions
            transactions.map { transaction ->
                async {
                    executeTransactionWithLock(state, counters, transaction)
                }
            }.awaitAll()
        }
    }

    println("Load test completed in ${executionTime}ms")

    val totalAmountAfter = fetchTotalAmount(state.httpClient, state.totalAmountUrl)

    println("💰Total amounts")
    println("Before: $totalAmountBefore, after: $totalAmountAfter")
    println("Difference: ${totalAmountAfter - totalAmountBefore}")

    println("🔄 Counters")
    println("Transactions: ${counters.transactions.get()}")
    println("Errors: ${counters.errors.get()}")
    println("Retries: ${counters.retries.get()}")
}

private suspend fun executeTransactionWithLock(state: State, counters: Counters, transaction: Transaction) {
    // get/create mutex for this 'from' address
    val fromMutex = state.mutexMapLock.withLock {
        state.fromAddressMutexes.getOrPut(transaction.from) { Mutex() }
    }
    fromMutex.withLock {
        executeTransaction(counters, state.httpClient, state.transactionUrl, transaction)
    }
}

suspend fun main(args: Array<String>) {
//    if (args.isEmpty()) {
//        println("Usage: kotlin LoadTestApp <serverUrl> [inputFile]")
//        println("Example: kotlin LoadTestApp http://localhost:8080/api/transfers input.csv")
//        return
//    }

    val transactionUrl = "http://127.0.0.1:8080/api/transactions"//args[0]
    val totalAmountUrl = "http://127.0.0.1:8080/api/accounts/total-amount"
    val inputFile = if (args.size > 1) args[1] else "in/input.csv"

    try {
        runLoadTest(transactionUrl, totalAmountUrl, inputFile)
    } catch (e: Exception) {
        println("Error: ${e.message}")
        e.printStackTrace()
    }
}