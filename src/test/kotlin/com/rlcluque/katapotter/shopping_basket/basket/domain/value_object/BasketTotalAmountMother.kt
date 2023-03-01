package com.rlcluque.katapotter.shopping_basket.basket.domain.value_object

class BasketTotalAmountMother {
    companion object {
        fun create(value: Double) = BasketTotalAmount(value)
        fun random() = create(3.0)
    }
}
