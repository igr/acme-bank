package acme.service;

import acme.dto.TransactionRequest;
import acme.dto.TransactionResponse;
import acme.entity.Account;
import acme.entity.Transaction;
import acme.repository.AccountRepository;
import acme.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Mono<TransactionResponse> performTransaction(TransactionRequest request) {
        return Mono.zip(
                accountRepository.findById(request.from())
                    .switchIfEmpty(Mono.error(new IllegalArgumentException("From account not found: " + request.from()))),
                accountRepository.findById(request.to())
                    .switchIfEmpty(Mono.error(new IllegalArgumentException("To account not found: " + request.to())))
            )
            .flatMap(tuple -> {
                Account fromAccount = tuple.getT1();
                Account toAccount = tuple.getT2();

                // validate amount
                if (request.amount() <= 0) {
                    return Mono.error(new IllegalArgumentException("Transaction amount must be positive"));
                }

                // validate sufficient funds
                if (fromAccount.amount() < request.amount()) {
                    return Mono.error(new IllegalArgumentException("Insufficient funds in account: " + request.from()));
                }

                // check withdrawal limit
                if (fromAccount.withdrawalLimit() != -1L) {
                    return transactionRepository.sumLastNTransactionsByAccountId(request.from(), 10)
                        .defaultIfEmpty(0.0)
                        .flatMap(totalLast10Transactions -> {
                            if (totalLast10Transactions > fromAccount.withdrawalLimit()) {
                                return Mono.error(new IllegalArgumentException(
                                    "Transaction would exceed limit for last 10 transactions. " +
                                    "Current total of last 10 transactions: " + totalLast10Transactions + ", " +
                                    "Limit: " + fromAccount.withdrawalLimit() + ", " +
                                    "Attempted amount: " + request.amount()
                                ));
                            }
                            return executeTransaction(fromAccount, toAccount, request);
                        });
                }

                return executeTransaction(fromAccount, toAccount, request);
            });
    }

    private Mono<TransactionResponse> executeTransaction(Account fromAccount, Account toAccount, TransactionRequest request) {
        Account updatedFromAccount = new Account(
            fromAccount.accountId(),
            fromAccount.bankId(),
            fromAccount.routingNumber(),
            fromAccount.amount() - request.amount(),
            fromAccount.withdrawalLimit(),
            LocalDateTime.now(),
            fromAccount.version()
        );

        Account updatedToAccount = new Account(
            toAccount.accountId(),
            toAccount.bankId(),
            toAccount.routingNumber(),
            toAccount.amount() + request.amount(),
            toAccount.withdrawalLimit(),
            LocalDateTime.now(),
            toAccount.version()
        );

        Transaction transaction = new Transaction(
            null,
            request.from(),
            request.to(),
            request.amount(),
            LocalDateTime.now(),
            "SUCCESS"
        );

        return Mono.zip(
                accountRepository.save(updatedFromAccount),
                accountRepository.save(updatedToAccount)
            )
            .then(transactionRepository.save(transaction))
            .flatMap(savedTransaction -> {
                log.info("Transaction performed: {}", savedTransaction);

                UUID transactionId = savedTransaction.transactionId();
                if (transactionId == null) {
                    return Mono.error(new IllegalStateException("Failed to save transaction - no ID generated"));
                }

                return Mono.just(new TransactionResponse(
                    transactionId,
                    request.from(),
                    request.to(),
                    request.amount()
                ));
            });
    }

    public Flux<Transaction> getTransactionHistory(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
