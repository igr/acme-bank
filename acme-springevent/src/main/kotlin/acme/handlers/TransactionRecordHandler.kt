package acme.handlers

import acme.entity.Transaction
import acme.events.AccountsUpdated
import acme.events.TransactionFailed
import acme.events.TransactionRecordCreated
import acme.repository.TransactionRepository
import acme.service.EventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class TransactionRecordHandler(
    private val transactionRepository: TransactionRepository,
    private val eventPublisher: EventPublisher
) {

    @EventListener
    fun handle(event: AccountsUpdated) {
        try {
            val transaction = Transaction(
                transactionId = null,
                fromAccountId = event.updatedFromAccount.accountId,
                toAccountId = event.updatedToAccount.accountId,
                amount = event.updatedFromAccount.amount - event.updatedToAccount.amount,
                timestamp = Instant.now(),
                status = "SUCCESS"
            )

            val savedTransaction = transactionRepository.save(transaction)

            eventPublisher.publish(
                TransactionRecordCreated(
                    correlationId = event.correlationId,
                    transaction = savedTransaction
                )
            )
        } catch (e: Exception) {
            eventPublisher.publish(
                TransactionFailed(
                    correlationId = event.correlationId,
                    fromAccountId = event.updatedFromAccount.accountId,
                    toAccountId = event.updatedToAccount.accountId,
                    amount = event.updatedFromAccount.amount - event.updatedToAccount.amount,
                    reason = "Failed to create transaction record: ${e.message}"
                )
            )
        }
    }
}