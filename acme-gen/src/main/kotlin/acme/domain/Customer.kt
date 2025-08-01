package acme.domain

import java.util.*

data class Customer(
	val customerId: UUID,
	val name: String,
	val accounts: List<Account>
)