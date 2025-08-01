package acme

import acme.domain.Account
import acme.domain.Bank
import acme.domain.Customer
import io.github.serpro69.kfaker.Faker
import java.time.Instant
import java.util.*

fun generateCustomers(bank: Bank, config: AcmeConfiguration): List<Customer> {
    val faker = Faker()
    return (1..config.totalCustomers)
        .map { generateCustomer(faker, bank, config) }
        .toList()
}

private fun generateCustomer(faker: Faker, bank: Bank, config: AcmeConfiguration): Customer {
    val accounts = (1..config.accountsPerCustomer())
        .map { generateAccount(faker, bank, config) }
        .toList()

    val validName = generateSequence { faker.name.name() }
        .first { !it.contains("'") }

    return Customer(
        UUID.randomUUID(),
        validName,
        accounts
    )
}
private fun generateAccount(faker: Faker, bank: Bank, config: AcmeConfiguration): Account {
    return Account(
        UUID.randomUUID(),
        bank.bankId,
        faker.string.numerify("############"),
        config.randomAmountPerAccount(),
        config.randomWithdrawalLimit(),
        Instant.now()
    )
}
