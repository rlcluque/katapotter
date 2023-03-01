package com.rlcluque.katapotter.shared.domain.id

import java.util.*

class BookIdMother {
    companion object {
        fun harryPotterAndThePhilosophesStone() = BookId(
                value = "12a4aaee-8193-4ebf-85b1-466b0d6500ff"
        )

        fun random() = BookId(UUID.randomUUID().toString())
    }
}
