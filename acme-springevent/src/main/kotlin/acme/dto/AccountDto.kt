package acme.dto

import java.time.Instant
import java.util.*

data class AccountDto(
    val accountId: UUID,
    val bankId: UUID,
    val routingNumber: String,
    val amount: Long,
    val withdrawalLimit: Long,
    val lastChange: Instant
)
