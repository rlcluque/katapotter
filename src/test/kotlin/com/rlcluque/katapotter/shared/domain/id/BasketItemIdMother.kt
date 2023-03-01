package com.rlcluque.katapotter.shared.domain.id

import java.util.UUID

class BasketItemIdMother {
    companion object {
        fun random() = BasketItemId(UUID.randomUUID().toString())
    }
}
