package acme.repository

import acme.entity.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : CoroutineCrudRepository<Customer, UUID>
