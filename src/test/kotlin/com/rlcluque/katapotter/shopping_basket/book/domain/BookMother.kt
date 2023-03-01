package com.rlcluque.katapotter.shopping_basket.book.domain

import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shared.domain.id.BookIdMother

class BookMother {
    companion object {
        fun create(
                id: BookId = BookIdMother.random(),
        ) = Book(
                id = id,
        )
    }
}
