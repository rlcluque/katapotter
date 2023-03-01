package com.rlcluque.katapotter.shared.domain

import com.rlcluque.katapotter.shared.domain.event.DomainEvent

open class Aggregate {
    private val domainEvents: MutableList<DomainEvent> = mutableListOf()

    protected fun recordDomain(event: DomainEvent) {
        domainEvents.add(event)
    }

    fun domainEvents() : List<DomainEvent> {
        val domainEvents = this.domainEvents.toList()

        this.domainEvents.clear()

        return domainEvents
    }
}
