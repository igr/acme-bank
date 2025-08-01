package acme.dto

import java.util.*

data class TransactionRequest(
    val from: UUID,
    val to: UUID,
    val amount: Long
)
