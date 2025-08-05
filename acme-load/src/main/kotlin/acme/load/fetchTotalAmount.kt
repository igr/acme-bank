package acme.load

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

suspend fun fetchTotalAmount(httpClient: HttpClient, totalAmountUrl: String): Long {
    val request = HttpRequest.newBuilder()
        .uri(URI.create(totalAmountUrl))
        .header("Content-Type", "application/json")
        .GET()
        .build()

    return withContext(Dispatchers.IO) {
        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
        response.body()
            .substringAfter("\"totalAmount\":")
            .substringBefore("}")
            .trim()
            .toLong()
    }
}