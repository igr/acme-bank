package acme.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Table("account")
data class Account(
    @Id
    val accountId: UUID,
    val bankId: UUID,
    val routingNumber: String,
    val amount: Long,
    val withdrawalLimit: Long,
    val lastChange: Instant,
    @Version
    val version: Long = 0
)
