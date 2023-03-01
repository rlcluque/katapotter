package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketIdMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItem
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItemMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmount

class BasketMother {
    companion object {
        fun create(
                id: BasketId = BasketIdMother.random(),
                items: List<BasketItem> = listOf(BasketItemMother.create()),
                totalAmount: BasketTotalAmount
        ) = Basket(
                id = id,
                items = items,
                totalAmount = totalAmount,
        )
    }
}
