package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

class BasketTotalAmountMother {
    companion object {
        fun create(value: Double = 3.0) = BasketTotalAmount(value)
    }
}
