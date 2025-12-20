package acme.repository;

import acme.entity.Transaction;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, UUID> {

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId OR to_account_id = :accountId ORDER BY timestamp DESC")
    Flux<Transaction> findByAccountId(UUID accountId);

    @Query("SELECT * FROM transaction WHERE from_account_id = :fromAccountId")
    Flux<Transaction> findByFromAccountId(UUID fromAccountId);

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId AND timestamp >= :timestampAfter")
    Flux<Transaction> findTransactionsByAccountIdAndTimestampAfter(
        UUID accountId,
        LocalDateTime timestampAfter
    );

    @Query("SELECT * FROM transaction WHERE from_account_id = :accountId ORDER BY timestamp DESC LIMIT :limit")
    Flux<Transaction> findTopByAccountIdOrderByTimestampDesc(
        UUID accountId,
        int limit
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
    Mono<Double> sumLastNTransactionsByAccountId(
        UUID accountId,
        int limit
    );
}
