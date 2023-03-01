package com.rlcluque.katapotter.shared.domain.event

abstract class DomainEvent {
    abstract fun name(): String
    abstract fun payload(): Payload

    fun isSimilarTo(domainEvent: DomainEvent) = (this.name() == domainEvent.name() && this.payload() == domainEvent.payload())
}
