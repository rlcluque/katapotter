package com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket

data class ItemAddedToBasketV1 (
        val aggregateId: String,
        val newItem: String,
        val newTotalAmount: Double,
)
