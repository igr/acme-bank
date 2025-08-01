package acme.domain

import java.time.Instant
import java.util.*

data class Account(
	val accountId: UUID,
	val bankId: UUID,
	val routingNumber: String,
	val amount: Long,
	/**
	 * The total amount that can be withdrawn in last 10 transactions.
	 * -1 means unlimited.
	 */
	val withdrawalLimit: Long,
	val lastChange: Instant
) {
}