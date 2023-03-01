package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket.ItemAddedToBasketV1
import com.rlcluque.katapotter.shared.domain.id.BasketId

interface BasketRepository {
    fun find(id: BasketId): Basket?
    fun save(expectedBasket: Basket)
    fun notify(expectedEvents: List<ItemAddedToBasketV1>)
}
