package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItem
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmount

data class Basket(
        private val id: BasketId,
        private val items: List<BasketItem>,
        private val totalAmount: BasketTotalAmount,
)
