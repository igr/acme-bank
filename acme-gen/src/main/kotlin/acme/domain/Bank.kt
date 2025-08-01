package acme.domain

import java.util.*

data class Bank(
    val bankId: UUID,
    val name: String,
    val address: String,
)
