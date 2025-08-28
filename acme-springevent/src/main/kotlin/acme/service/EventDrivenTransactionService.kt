package acme.service

import acme.dto.TransactionRequest
import acme.dto.TransactionResponse
import acme.entity.Transaction
import acme.events.TransactionCompleted
import acme.events.TransactionFailed
import acme.events.TransactionRequested
import acme.repository.TransactionRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

@Service
class EventDrivenTransactionService(
    private val eventPublisher: EventPublisher,
    private val transactionRepository: TransactionRepository
) {
    
    private val pendingTransactions = ConcurrentHashMap<String, CompletableFuture<TransactionResponse>>()

    fun performTransaction(request: TransactionRequest): CompletableFuture<TransactionResponse> {
        val correlationId = UUID.randomUUID().toString()
        val future = CompletableFuture<TransactionResponse>()
        
        pendingTransactions[correlationId] = future

        // Publish the initial event
        eventPublisher.publish(
            TransactionRequested(
                correlationId = correlationId,
                fromAccountId = request.from,
                toAccountId = request.to,
                amount = request.amount
            )
        )

        return future.orTimeout(30, TimeUnit.SECONDS)
            .whenComplete { _, _ -> pendingTransactions.remove(correlationId) }
    }

    fun getTransactionHistory(accountId: UUID): List<Transaction> {
        return transactionRepository.findByAccountId(accountId)
    }

    @EventListener
    fun handleTransactionCompleted(event: TransactionCompleted) {
        val future = pendingTransactions[event.correlationId]
        future?.complete(
            TransactionResponse(
                transactionId = event.transactionId,
                fromAccountId = event.fromAccountId,
                toAccountId = event.toAccountId,
                amount = event.amount,
                status = "SUCCESS"
            )
        )
    }

    @EventListener
    fun handleTransactionFailed(event: TransactionFailed) {
        val future = pendingTransactions[event.correlationId]
        future?.completeExceptionally(
            RuntimeException("Failed to perform transaction: ${event.reason} F")
//            TransactionResponse(
//                fromAccountId = event.fromAccountId,
//                toAccountId = event.toAccountId,
//                amount = event.amount,
//                status = "FAILED: ${event.reason}"
//            )
        )
    }
}