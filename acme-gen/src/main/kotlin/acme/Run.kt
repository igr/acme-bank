package acme

import acme.db.generateSql
import acme.domain.Bank
import java.io.File
import java.util.*

fun main() {
    val acmeConfig = AcmeConfiguration()
    val bank = Bank(
        UUID.randomUUID(),
        "Acme Bank",
        "123 Main St"
    )

    val customers = generateCustomers(bank, acmeConfig)
    val totalAmount = customers.sumOf { it.accounts.sumOf { it.amount } }

    println("🔥 Total amount: $totalAmount")

    generateSql(bank, customers).also { File("out/acme.sql").writeText(it) }
    println("✅ Generated SQL file")
    generateLoadTransactions(bank, customers, LoadConfiguration()).also { File("out/input.csv").writeText(it) }
    println("✅ Generated input file")
}
