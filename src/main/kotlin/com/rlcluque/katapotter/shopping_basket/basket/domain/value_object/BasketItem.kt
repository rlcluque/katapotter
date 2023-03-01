package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BookId

data class BasketItem(
        val bookId: BookId,
        val itemId: BasketItemId,
)
