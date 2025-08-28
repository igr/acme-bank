package acme.repository

import acme.entity.Account
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : CrudRepository<Account, UUID> {
    
    @Query("""
        SELECT a.* FROM account a 
        JOIN customer_account ca ON a.account_id = ca.account_id 
        WHERE ca.customer_id = :customerId
    """)
    fun findAccountsByCustomerId(@Param("customerId") customerId: UUID): List<Account>

    @Query("SELECT SUM(amount) FROM account")
    fun getTotalAmount(): Long?
}
