package acme.handlers

import acme.events.TransactionCompleted
import acme.events.TransactionRecordCreated
import acme.service.EventPublisher
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TransactionCompletionHandler(
    private val eventPublisher: EventPublisher
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @EventListener
    fun handle(event: TransactionRecordCreated) {
        val transactionId = event.transaction.transactionId
            ?: throw IllegalStateException("Transaction ID should not be null")

        log.info("Transaction completed: ${event.transaction}")

        eventPublisher.publish(
            TransactionCompleted(
                correlationId = event.correlationId,
                transactionId = transactionId,
                fromAccountId = event.transaction.fromAccountId,
                toAccountId = event.transaction.toAccountId,
                amount = event.transaction.amount
            )
        )
    }
}