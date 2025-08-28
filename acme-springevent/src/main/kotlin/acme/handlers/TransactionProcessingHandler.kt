package acme.handlers

import acme.events.AccountsUpdated
import acme.events.TransactionFailed
import acme.events.TransactionProcessingRequested
import acme.repository.AccountRepository
import acme.service.EventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class TransactionProcessingHandler(
    private val accountRepository: AccountRepository,
    private val eventPublisher: EventPublisher
) {

    @EventListener
    @Transactional
    fun handle(event: TransactionProcessingRequested) {
        try {
            // Update account balances
            val updatedFromAccount = event.fromAccount.copy(
                amount = event.fromAccount.amount - event.amount,
                lastChange = Instant.now()
            )

            val updatedToAccount = event.toAccount.copy(
                amount = event.toAccount.amount + event.amount,
                lastChange = Instant.now()
            )

            // Save updated accounts
            val savedFromAccount = accountRepository.save(updatedFromAccount)
            val savedToAccount = accountRepository.save(updatedToAccount)

            eventPublisher.publish(
                AccountsUpdated(
                    correlationId = event.correlationId,
                    updatedFromAccount = savedFromAccount,
                    updatedToAccount = savedToAccount
                )
            )
        } catch (e: Exception) {
            eventPublisher.publish(
                TransactionFailed(
                    correlationId = event.correlationId,
                    fromAccountId = event.fromAccount.accountId,
                    toAccountId = event.toAccount.accountId,
                    amount = event.amount,
                    reason = "Failed to update accounts: ${e.message}"
                )
            )
        }
    }
}