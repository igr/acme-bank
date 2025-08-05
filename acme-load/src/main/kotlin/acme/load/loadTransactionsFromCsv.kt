package acme.load

import java.io.File

fun loadTransactionsFromCsv(inputFile: String): List<Transaction> {
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