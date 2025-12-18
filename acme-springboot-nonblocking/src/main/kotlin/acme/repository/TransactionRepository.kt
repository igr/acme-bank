package acme.repository

import acme.entity.Transaction
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface TransactionRepository : CoroutineCrudRepository<Transaction, UUID> {

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId OR to_account_id = :accountId ORDER BY timestamp DESC")
    fun findByAccountId(@Param("accountId") accountId: UUID): Flow<Transaction>

    @Query("SELECT * FROM transaction WHERE from_account_id = :fromAccountId")
    fun findByFromAccountId(@Param("fromAccountId") fromAccountId: UUID): Flow<Transaction>

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId AND timestamp >= :timestampAfter")
    fun findTransactionsByAccountIdAndTimestampAfter(
        @Param("accountId") accountId: UUID,
        @Param("timestampAfter") timestampAfter: LocalDateTime
    ): Flow<Transaction>

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId ORDER BY timestamp DESC LIMIT :limit")
    fun findTopByAccountIdOrderByTimestampDesc(
        @Param("accountId") accountId: UUID,
        @Param("limit") limit: Int
    ): Flow<Transaction>

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM (
            SELECT amount FROM transaction
            WHERE from_account_id = :accountId
            ORDER BY timestamp DESC
            LIMIT :limit
        ) t
    """)
    suspend fun sumLastNTransactionsByAccountId(
        @Param("accountId") accountId: UUID,
        @Param("limit") limit: Int
    ): Double
}
