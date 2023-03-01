package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shopping_basket.book.domain.Book

data class BasketItem(
        val bookId: BookId,
        val itemId: BasketItemId,
)

fun Book.toBasketItem(itemId: BasketItemId) = BasketItem(
        bookId = this.id,
        itemId = itemId,
)
