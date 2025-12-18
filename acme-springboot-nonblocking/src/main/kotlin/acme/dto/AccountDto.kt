package acme.dto

import java.time.LocalDateTime
import java.util.*

data class AccountDto(
    val accountId: UUID,
    val bankId: UUID,
    val routingNumber: String,
    val amount: Long,
    val withdrawalLimit: Long,
    val lastChange: LocalDateTime
)
