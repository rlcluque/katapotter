package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

import com.rlcluque.katapotter.shopping_basket.book.domain.BookPrice

data class BasketTotalAmount(
        val value: Double
) {
    fun plus(price: BookPrice) = BasketTotalAmount(this.value + price.value)
}
