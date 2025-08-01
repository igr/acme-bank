package acme.repository

import acme.entity.CustomerAccount
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerAccountRepository : CrudRepository<CustomerAccount, UUID> {
    fun findByCustomerId(customerId: UUID): List<CustomerAccount>
}
