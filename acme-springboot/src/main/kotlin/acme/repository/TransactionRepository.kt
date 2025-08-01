package acme.repository

import acme.entity.Transaction
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface TransactionRepository : CrudRepository<Transaction, UUID> {
    
    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId OR to_account_id = :accountId ORDER BY timestamp DESC")
    fun findByAccountId(@Param("accountId") accountId: UUID): List<Transaction>
    
    @Query("SELECT * FROM transaction WHERE from_account_id = :fromAccountId")
    fun findByFromAccountId(@Param("fromAccountId") fromAccountId: UUID): List<Transaction>
    
    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId AND timestamp >= :timestampAfter")
    fun findTransactionsByAccountIdAndTimestampAfter(
        @Param("accountId") accountId: UUID,
        @Param("timestampAfter") timestampAfter: LocalDateTime
    ): List<Transaction>
    
    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId ORDER BY timestamp DESC LIMIT :limit")
    fun findTopByAccountIdOrderByTimestampDesc(
        @Param("accountId") accountId: UUID,
        @Param("limit") limit: Int
    ): List<Transaction>
    
    @Query("""
        SELECT COALESCE(SUM(t.amount), 0) 
        FROM (
            SELECT amount FROM transaction 
            WHERE from_account_id = :accountId 
            ORDER BY timestamp DESC 
            LIMIT :limit
        ) t
    """)
    fun sumLastNTransactionsByAccountId(
        @Param("accountId") accountId: UUID,
        @Param("limit") limit: Int
    ): Double
    
}