package com.rlcluque.katapotter.shared.domain.id

import java.util.UUID

data class BasketId(val value: String) {
    init {
        validate(value)
    }

    private fun validate(value: String) {
        UUID.fromString(value)
    }
}
