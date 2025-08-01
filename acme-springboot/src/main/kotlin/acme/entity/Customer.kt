package acme.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("customer")
data class Customer(
    @Id
    val customerId: UUID,
    val name: String
)
