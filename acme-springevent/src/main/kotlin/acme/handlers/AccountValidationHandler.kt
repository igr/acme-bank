package acme.handlers

import acme.events.AccountValidationCompleted
import acme.events.AccountValidationRequested
import acme.repository.AccountRepository
import acme.service.EventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AccountValidationHandler(
    private val accountRepository: AccountRepository,
    private val eventPublisher: EventPublisher
) {

    @EventListener
    fun handle(event: AccountValidationRequested) {
        try {
            val fromAccount = accountRepository.findById(event.fromAccountId)
                .orElseThrow { IllegalArgumentException("From account not found: ${event.fromAccountId}") }

            val toAccount = accountRepository.findById(event.toAccountId)
                .orElseThrow { IllegalArgumentException("To account not found: ${event.toAccountId}") }

            eventPublisher.publish(
                AccountValidationCompleted(
                    correlationId = event.correlationId,
                    fromAccount = fromAccount,
                    toAccount = toAccount,
                    isValid = true
                )
            )
        } catch (e: Exception) {
            eventPublisher.publish(
                AccountValidationCompleted(
                    correlationId = event.correlationId,
                    fromAccount = null,
                    toAccount = null,
                    isValid = false,
                    errorMessage = e.message
                )
            )
        }
    }
}