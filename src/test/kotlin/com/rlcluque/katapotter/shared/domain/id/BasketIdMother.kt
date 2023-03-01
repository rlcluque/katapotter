package com.rlcluque.katapotter.shared.domain.id

import java.util.UUID

class BasketIdMother {
    companion object {
        fun random() = BasketId(
                UUID.randomUUID().toString()
        )
    }
}
