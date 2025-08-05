package acme.service

import acme.dto.TransactionRequest
import acme.dto.TransactionResponse
import acme.entity.Transaction
import acme.repository.AccountRepository
import acme.repository.TransactionRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class TransactionService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)
    
    @Transactional
    fun performTransaction(request: TransactionRequest): TransactionResponse {
        // validate accounts exist
        val fromAccount = accountRepository.findById(request.from)
            .orElseThrow { IllegalArgumentException("From account not found: ${request.from}") }
        
        val toAccount = accountRepository.findById(request.to)
            .orElseThrow { IllegalArgumentException("To account not found: ${request.to}") }
        
        // validate amount
        if (request.amount <= 0) {
            throw IllegalArgumentException("Transaction amount must be positive")
        }
        
        // validate sufficient funds
        if (fromAccount.amount < request.amount) {
            throw IllegalArgumentException("Insufficient funds in account: ${request.from}")
        }

        if (fromAccount.withdrawalLimit != -1L) {
            // Check transaction limit for last 10 transactions
            val totalLast10Transactions = transactionRepository.sumLastNTransactionsByAccountId(
                request.from,
                10
            )

            if (totalLast10Transactions > fromAccount.withdrawalLimit) {
                throw IllegalArgumentException(
                    "Transaction would exceed limit for last 10 transactions. " +
                            "Current total of last 10 transactions: $totalLast10Transactions, " +
                            "Limit: ${fromAccount.withdrawalLimit}, " +
                            "Attempted amount: ${request.amount}"
                )
            }
        }
        
        // perform the transaction
        val updatedFromAccount = fromAccount.copy(
            amount = fromAccount.amount - request.amount,
            lastChange = LocalDateTime.now()
        )
        
        val updatedToAccount = toAccount.copy(
            amount = toAccount.amount + request.amount,
            lastChange = LocalDateTime.now()
        )
        
        // save updated accounts
        accountRepository.save(updatedFromAccount)
        accountRepository.save(updatedToAccount)
        
        // save transaction record
        val transaction = Transaction(
            transactionId = null,   // let the database generate the ID
            fromAccountId = request.from,
            toAccountId = request.to,
            amount = request.amount,
            timestamp = LocalDateTime.now(),
            status = "SUCCESS"
        )
        val savedTransaction = transactionRepository.save(transaction)

        log.info("Transaction performed: $savedTransaction")

        val transactionId = savedTransaction.transactionId 
            ?: throw IllegalStateException("Failed to save transaction - no ID generated")

        return TransactionResponse(
            transactionId = transactionId,
            fromAccountId = request.from,
            toAccountId = request.to,
            amount = request.amount
        )
    }

    fun getTransactionHistory(accountId: UUID): List<Transaction> {
        return transactionRepository.findByAccountId(accountId)
    }
}
