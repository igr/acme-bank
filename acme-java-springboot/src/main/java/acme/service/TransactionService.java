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

import java.time.LocalDateTime;
import java.util.List;
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
    public TransactionResponse performTransaction(TransactionRequest request) {
        // validate accounts exist
        Account fromAccount = accountRepository.findById(request.from())
            .orElseThrow(() -> new IllegalArgumentException("From account not found: " + request.from()));

        Account toAccount = accountRepository.findById(request.to())
            .orElseThrow(() -> new IllegalArgumentException("To account not found: " + request.to()));

        // validate amount
        if (request.amount() <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }

        // validate sufficient funds
        if (fromAccount.amount() < request.amount()) {
            throw new IllegalArgumentException("Insufficient funds in account: " + request.from());
        }

        if (fromAccount.withdrawalLimit() != -1L) {
            // Check transaction limit for last 10 transactions
            double totalLast10Transactions = transactionRepository.sumLastNTransactionsByAccountId(
                request.from(),
                10
            );

            if (totalLast10Transactions > fromAccount.withdrawalLimit()) {
                throw new IllegalArgumentException(
                    "Transaction would exceed limit for last 10 transactions. " +
                    "Current total of last 10 transactions: " + totalLast10Transactions + ", " +
                    "Limit: " + fromAccount.withdrawalLimit() + ", " +
                    "Attempted amount: " + request.amount()
                );
            }
        }

        // perform the transaction
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

        // save updated accounts
        accountRepository.save(updatedFromAccount);
        accountRepository.save(updatedToAccount);

        // save transaction record
        Transaction transaction = new Transaction(
            null,   // let the database generate the ID
            request.from(),
            request.to(),
            request.amount(),
            LocalDateTime.now(),
            "SUCCESS"
        );
        Transaction savedTransaction = transactionRepository.save(transaction);

        log.info("Transaction performed: {}", savedTransaction);

        UUID transactionId = savedTransaction.transactionId();
        if (transactionId == null) {
            throw new IllegalStateException("Failed to save transaction - no ID generated");
        }

        return new TransactionResponse(
            transactionId,
            request.from(),
            request.to(),
            request.amount()
        );
    }

    public List<Transaction> getTransactionHistory(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
