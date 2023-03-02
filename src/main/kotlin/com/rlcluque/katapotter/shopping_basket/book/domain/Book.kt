package com.rlcluque.katapotter.shopping_basket.book.domain

import com.rlcluque.katapotter.shared.domain.id.BookId

data class Book(
        val id: BookId,
) {
    companion object{
        const val DEFAULT_PRICE = 8.0
    }
}