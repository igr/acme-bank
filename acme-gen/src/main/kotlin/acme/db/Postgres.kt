package acme.db

import acme.domain.Bank
import acme.domain.Customer

fun generateSql(bank: Bank, customers: List<Customer>): String {

    val initialSql = """
create table if not exists bank (
    bank_id uuid not null,
    name text not null,
    address text not null,
    primary key (bank_id)
);

create table if not exists customer (
    customer_id uuid not null,
    name text not null,
    primary key (customer_id)
);

create table if not exists account (
    account_id uuid not null,
    bank_id uuid not null,
    routing_number text not null,
    amount bigint not null,
    withdrawal_limit bigint not null,
    last_change timestamp not null,
    primary key (account_id),
    foreign key (bank_id) references bank(bank_id)
);

create table if not exists customer_account (
    customer_id uuid not null,
    account_id uuid not null,
    primary key (customer_id, account_id),
    foreign key (customer_id) references customer(customer_id),
    foreign key (account_id) references account(account_id)
);
    """.trimIndent()

    // insert bank

    val bankSql = "insert into bank (bank_id, name, address) values ('${bank.bankId}', '${bank.name}', '${bank.address}');"

    // insert customers

    val customersSql = customers.joinToString("\n") { "insert into customer (customer_id, name) values ('${it.customerId}', '${it.name}');" }

    // insert accounts

    val accountsSql = customers.flatMap { it.accounts }.joinToString("\n") {
        "insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('${it.accountId}', '${it.bankId}', '${it.routingNumber}', ${it.amount}, ${it.withdrawalLimit}, '${it.lastChange}');"
    }

    return "$initialSql\n$bankSql\n\n$customersSql\n\n$accountsSql"
}
