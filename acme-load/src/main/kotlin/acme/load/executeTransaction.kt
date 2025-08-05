package acme.load

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

suspend fun executeTransaction(
    counters: Counters,
    httpClient: HttpClient,
    serverUrl: String,
    transaction: Transaction
) {
    executeTransactionWithRetry(counters, httpClient, serverUrl, transaction, maxRetries = 3)
}

private suspend fun executeTransactionWithRetry(
    counters: Counters,
    httpClient: HttpClient,
    serverUrl: String,
    transaction: Transaction,
    maxRetries: Int,
    currentAttempt: Int = 1
) {
    try {
        val requestBody = requestBodyOf(transaction)

        val request = HttpRequest.newBuilder()
            .uri(URI.create(serverUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build()

        withContext(Dispatchers.IO) {
            counters.transactions.incrementAndGet()
            val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

            when (response.statusCode()) {
                in 200..299 -> {
                    println("✅ Success: ${transaction.from} -> ${transaction.to} (${transaction.amount}) - Status: ${response.statusCode()}" + 
                        if (currentAttempt > 1) " (attempt $currentAttempt)" else "")
                }
                
                409 -> {
                    if (currentAttempt <= maxRetries) {
                        counters.retries.incrementAndGet()
                        println("🔄 Retry ${currentAttempt}/${maxRetries}: ${transaction.from} -> ${transaction.to} (${transaction.amount}) - Status: 409 Conflict")
                        executeTransactionWithRetry(counters, httpClient, serverUrl, transaction, maxRetries, currentAttempt + 1)
                    } else {
                        counters.errors.incrementAndGet()
                        println("❌ Max retries exceeded: ${transaction.from} -> ${transaction.to} (${transaction.amount}) - Status: 409, Body: ${response.body()}")
                    }
                }

                else -> {
                    counters.errors.incrementAndGet()
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