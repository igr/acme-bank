package acme.dto

import java.time.LocalDateTime
import java.util.*

data class TransactionResponse(
    val transactionId: UUID = UUID.randomUUID(),
    val fromAccountId: UUID,
    val toAccountId: UUID,
    val amount: Long,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: String = "SUCCESS"
)
