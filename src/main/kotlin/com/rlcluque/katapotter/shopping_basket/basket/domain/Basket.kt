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

        newItems.add(newBasketItem)

        items = newItems

        calculateTotalAmount()

        basketItemAdded(newBasketItem)
    }

    private fun calculateTotalAmount() {
        val distinctCount = items.distinctBy { it.bookId }.count()
        val repeatedCount = items.count() - distinctCount
        val discount = discountPerDistinctItems(distinctCount)
        val totalAmountForDistinctBooks = distinctCount.toDouble() * Book.DEFAULT_PRICE * (100 - discount) / 100
        val totalAmountForRepeatedBooks = repeatedCount * Book.DEFAULT_PRICE

        totalAmount = BasketTotalAmount(totalAmountForDistinctBooks + totalAmountForRepeatedBooks)
    }

    private fun discountPerDistinctItems(count: Int) = if (count <= 1) 0
        else when(count) {
            2 -> 5
            3 -> 10
            4 -> 20
            5 -> 25
            else -> throw Exception("End of the world")
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
