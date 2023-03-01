package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BasketItemIdMother
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shared.domain.id.BookIdMother

class BasketItemMother {
    companion object {
        fun create(
                bookId: BookId = BookIdMother.random(),
                itemId: BasketItemId = BasketItemIdMother.random(),
        ) = BasketItem(
                bookId = bookId,
                itemId = itemId,
        )
    }
}
