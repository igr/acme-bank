package acme.handlers

import acme.events.LimitValidationCompleted
import acme.events.LimitValidationRequested
import acme.repository.TransactionRepository
import acme.service.EventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LimitValidationHandler(
    private val transactionRepository: TransactionRepository,
    private val eventPublisher: EventPublisher
) {

    @EventListener
    fun handle(event: LimitValidationRequested) {
        try {
            if (event.fromAccount.withdrawalLimit == -1L) {
                // No limit set
                eventPublisher.publish(
                    LimitValidationCompleted(
                        correlationId = event.correlationId,
                        isWithinLimit = true
                    )
                )
                return
            }

            val totalLast10Transactions = transactionRepository.sumLastNTransactionsByAccountId(
                event.fromAccount.accountId,
                10
            )

            val wouldExceedLimit = totalLast10Transactions + event.requestedAmount > event.fromAccount.withdrawalLimit
            val errorMessage = if (wouldExceedLimit) {
                "Transaction would exceed limit for last 10 transactions. " +
                        "Current total: $totalLast10Transactions, " +
                        "Limit: ${event.fromAccount.withdrawalLimit}, " +
                        "Attempted amount: ${event.requestedAmount}"
            } else null

            eventPublisher.publish(
                LimitValidationCompleted(
                    correlationId = event.correlationId,
                    isWithinLimit = !wouldExceedLimit,
                    errorMessage = errorMessage
                )
            )
        } catch (e: Exception) {
            eventPublisher.publish(
                LimitValidationCompleted(
                    correlationId = event.correlationId,
                    isWithinLimit = false,
                    errorMessage = e.message
                )
            )
        }
    }
}