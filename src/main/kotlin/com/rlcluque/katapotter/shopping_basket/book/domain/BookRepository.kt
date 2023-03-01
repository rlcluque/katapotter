package com.rlcluque.katapotter.shopping_basket.book.domain

import com.rlcluque.katapotter.shared.domain.id.BookId

interface BookRepository {
    fun find(id: BookId): Book
}
