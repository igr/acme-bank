package acme.repository

import acme.entity.Account
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : CoroutineCrudRepository<Account, UUID> {

    @Query("""
        SELECT a.* FROM account a
        JOIN customer_account ca ON a.account_id = ca.account_id
        WHERE ca.customer_id = :customerId
    """)
    fun findAccountsByCustomerId(@Param("customerId") customerId: UUID): Flow<Account>

    @Query("SELECT SUM(amount) FROM account")
    suspend fun getTotalAmount(): Long?
}
