package acme.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("transaction")
data class Transaction(
    @Id
    val transactionId: UUID?,
    val fromAccountId: UUID,
    val toAccountId: UUID,
    val amount: Long,
    val timestamp: LocalDateTime,
    val status: String,
    @Version
    val version: Long = 0
)
