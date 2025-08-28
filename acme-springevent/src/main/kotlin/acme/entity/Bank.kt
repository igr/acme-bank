package acme.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("bank")
data class Bank(
    @Id
    val bankId: UUID,
    val name: String,
    val address: String,
    @Version
    val version: Long = 0
)
