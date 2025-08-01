package acme

import acme.domain.Account
import acme.domain.Bank
import acme.domain.Customer
import kotlin.random.Random

fun generateLoadTransactions(bank: Bank, customers: List<Customer>, config: LoadConfiguration): String {
    val accounts = customers.flatMap { it.accounts }
    val s = StringBuilder()

    var totalCount = config.totalRequests
    while (totalCount > 0) {
        val from = accounts.random()
        val to = accounts.random()
        if (from == to) {
            continue
        }
        val amount = randomAmount(from)
        val tx = "${from.accountId},${to.accountId},$amount\n"
        s.append(tx)
        totalCount--
    }
    return s.toString()
}

private fun randomAmount(from: Account): Long {
    return (from.amount * (Random.nextDouble(0.5) + 0.1)).toLong()
}
