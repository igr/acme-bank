package acme.handlers

import acme.events.FundsValidationCompleted
import acme.events.FundsValidationRequested
import acme.service.EventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class FundsValidationHandler(
    private val eventPublisher: EventPublisher
) {

    @EventListener
    fun handle(event: FundsValidationRequested) {
        val hasValidFunds = event.fromAccount.amount >= event.requestedAmount
        val errorMessage = if (!hasValidFunds) {
            "Insufficient funds in account: ${event.fromAccount.accountId}"
        } else null

        eventPublisher.publish(
            FundsValidationCompleted(
                correlationId = event.correlationId,
                hasValidFunds = hasValidFunds,
                errorMessage = errorMessage
            )
        )
    }
}