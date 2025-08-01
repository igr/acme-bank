package acme.entity

import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("customer_account")
data class CustomerAccount(
    val customerId: UUID,
    val accountId: UUID
)
