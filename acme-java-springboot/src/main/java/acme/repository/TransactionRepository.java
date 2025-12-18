package acme.repository;

import acme.entity.Transaction;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId OR to_account_id = :accountId ORDER BY timestamp DESC")
    List<Transaction> findByAccountId(@Param("accountId") UUID accountId);

    @Query("SELECT * FROM transaction WHERE from_account_id = :fromAccountId")
    List<Transaction> findByFromAccountId(@Param("fromAccountId") UUID fromAccountId);

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId AND timestamp >= :timestampAfter")
    List<Transaction> findTransactionsByAccountIdAndTimestampAfter(
        @Param("accountId") UUID accountId,
        @Param("timestampAfter") LocalDateTime timestampAfter
    );

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId ORDER BY timestamp DESC LIMIT :limit")
    List<Transaction> findTopByAccountIdOrderByTimestampDesc(
        @Param("accountId") UUID accountId,
        @Param("limit") int limit
    );

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM (
            SELECT amount FROM transaction
            WHERE from_account_id = :accountId
            ORDER BY timestamp DESC
            LIMIT :limit
        ) t
    """)
    Double sumLastNTransactionsByAccountId(
        @Param("accountId") UUID accountId,
        @Param("limit") int limit
    );
}
