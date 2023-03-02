package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.event.DomainEvent
import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketItemId

interface BasketRepository {
    fun find(id: BasketId): Basket?
    fun save(expectedBasket: Basket)
    fun notify(expectedEvents: List<DomainEvent>)
    fun findByItem(itemId: BasketItemId): Basket?
}
