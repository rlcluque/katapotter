package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket.ItemAddedToBasketV1

class ItemAddedToBasketMother {
    companion object {
        fun create(
                aggregateId: String = "00b038e6-1d53-41bc-9006-88e50b258835",
                newItem: String = "81df4e9a-0a3b-4a64-a6d1-02630e64e38c",
                newTotalAmount: Double = 45.0
        ) = ItemAddedToBasketV1(
                aggregateId = aggregateId,
                newItem = newItem,
                newTotalAmount = newTotalAmount,
        )
    }
}
