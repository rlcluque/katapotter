package com.rlcluque.katapotter.shopping_basket.basket.application.use_case

import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketRepository
import com.rlcluque.katapotter.shopping_basket.book.domain.BookRepository

class AddItemToBasketUseCase(
        private val basketRepository: BasketRepository,
        private val bookRepository: BookRepository,
) {
    fun execute(parameters: AddItemToBasketParameters) {

    }
}

data class AddItemToBasketParameters(
        val bookId: BookId,
        val basketId: BasketId,
        val itemId: BasketItemId,
)
