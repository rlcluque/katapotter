package com.rlcluque.katapotter.shopping_basket.basket.domain

import com.rlcluque.katapotter.shared.domain.Aggregate
import com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket.ItemAddedToBasketV1
import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItem
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmount
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.toBasketItem
import com.rlcluque.katapotter.shopping_basket.book.domain.Book

data class Basket(
        private val id: BasketId,
        private var items: List<BasketItem>,
        private var totalAmount: BasketTotalAmount,
) : Aggregate() {
    fun addItem(itemId: BasketItemId, book: Book) {
        val newBasketItem = book.toBasketItem(itemId)
        val newItems = items.toMutableList()
        val newTotalAmount = totalAmount.plus(book.price())

        newItems.add(newBasketItem)

        items = newItems
        totalAmount = newTotalAmount

        basketItemAdded(newBasketItem)
    }

    private fun basketItemAdded(newBasketItem: BasketItem) {
        recordDomain(
                ItemAddedToBasketV1.create(
                        aggregateId = id.value,
                        newItem = newBasketItem.itemId.value,
                        newTotalAmount = totalAmount.value,
                )
        )
    }
}
