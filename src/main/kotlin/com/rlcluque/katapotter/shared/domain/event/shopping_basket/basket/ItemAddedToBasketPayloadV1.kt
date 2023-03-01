package com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket

import com.rlcluque.katapotter.shared.domain.event.Payload

data class ItemAddedToBasketPayloadV1(
        val newItem: String,
        val newTotalAmount: Double,
) : Payload
