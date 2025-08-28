package acme.service

import acme.events.TransactionEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

interface EventPublisher {
    fun publish(event: TransactionEvent)
}

@Component
class SpringEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) : EventPublisher {

    override fun publish(event: TransactionEvent) {
        applicationEventPublisher.publishEvent(event)
    }
}