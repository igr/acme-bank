package acme.service

import acme.entity.Account
import acme.events.*
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionSaga(
    private val eventPublisher: EventPublisher
) {

    private val sagaStates = mutableMapOf<String, SagaState>()

    @EventListener
    fun handle(event: TransactionRequested) {
        val sagaState = SagaState(
            correlationId = event.correlationId,
            fromAccountId = event.fromAccountId,
            toAccountId = event.toAccountId,
            amount = event.amount
        )
        sagaStates[event.correlationId] = sagaState

        // Start validation process
        eventPublisher.publish(
            AccountValidationRequested(
                correlationId = event.correlationId,
                fromAccountId = event.fromAccountId,
                toAccountId = event.toAccountId
            )
        )
    }

    @EventListener
    fun handle(event: AccountValidationCompleted) {
        val sagaState = sagaStates[event.correlationId] ?: return

        if (!event.isValid) {
            publishFailure(sagaState, event.errorMessage ?: "Account validation failed")
            return
        }

        sagaState.fromAccount = event.fromAccount
        sagaState.toAccount = event.toAccount

        // Proceed with funds validation
        eventPublisher.publish(
            FundsValidationRequested(
                correlationId = event.correlationId,
                fromAccount = event.fromAccount!!,
                requestedAmount = sagaState.amount
            )
        )
    }

    @EventListener
    fun handle(event: FundsValidationCompleted) {
        val sagaState = sagaStates[event.correlationId] ?: return

        if (!event.hasValidFunds) {
            publishFailure(sagaState, event.errorMessage ?: "Insufficient funds")
            return
        }

        // Proceed with limit validation
        eventPublisher.publish(
            LimitValidationRequested(
                correlationId = event.correlationId,
                fromAccount = sagaState.fromAccount!!,
                requestedAmount = sagaState.amount
            )
        )
    }

    @EventListener
    fun handle(event: LimitValidationCompleted) {
        val sagaState = sagaStates[event.correlationId] ?: return

        if (!event.isWithinLimit) {
            publishFailure(sagaState, event.errorMessage ?: "Transaction limit exceeded")
            return
        }

        // All validations passed, proceed with transaction processing
        eventPublisher.publish(
            TransactionProcessingRequested(
                correlationId = event.correlationId,
                fromAccount = sagaState.fromAccount!!,
                toAccount = sagaState.toAccount!!,
                amount = sagaState.amount
            )
        )
    }

    @EventListener
    fun handle(event: TransactionCompleted) {
        // Clean up saga state
        sagaStates.remove(event.correlationId)
    }

    @EventListener
    fun handle(event: TransactionFailed) {
        // Clean up saga state
        sagaStates.remove(event.correlationId)
    }

    private fun publishFailure(sagaState: SagaState, reason: String) {
        eventPublisher.publish(
            TransactionFailed(
                correlationId = sagaState.correlationId,
                fromAccountId = sagaState.fromAccountId,
                toAccountId = sagaState.toAccountId,
                amount = sagaState.amount,
                reason = reason
            )
        )
        sagaStates.remove(sagaState.correlationId)
    }

    data class SagaState(
        val correlationId: String,
        val fromAccountId: UUID,
        val toAccountId: UUID,
        val amount: Long,
        var fromAccount: Account? = null,
        var toAccount: Account? = null
    )
}