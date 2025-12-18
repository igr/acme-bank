package acme.repository

import acme.entity.CustomerAccount
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerAccountRepository : CoroutineCrudRepository<CustomerAccount, UUID> {
    fun findByCustomerId(customerId: UUID): Flow<CustomerAccount>
}
