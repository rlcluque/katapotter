package com.rlcluque.katapotter.shopping_basket.basket.application.use_case

import com.rlcluque.katapotter.shared.domain.id.*

class AddItemToBasketParametersMother {
    companion object {
        fun create(
                bookId: BookId = BookIdMother.random(),
                basketId: BasketId = BasketIdMother.random(),
                itemId: BasketItemId = BasketItemIdMother.random(),
        ) = AddItemToBasketParameters (
                bookId = bookId,
                basketId = basketId,
                itemId = itemId,
        )
    }
}
