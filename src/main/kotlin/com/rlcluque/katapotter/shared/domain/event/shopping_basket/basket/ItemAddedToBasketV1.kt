package com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket

import com.rlcluque.katapotter.shared.domain.event.DomainEvent

data class ItemAddedToBasketV1 (
        val aggregateId: String,
        val payload: ItemAddedToBasketPayloadV1,
) : DomainEvent() {
    override fun name() = NAME

    override fun payload() = payload

    companion object {
        fun create(aggregateId: String, newItem: String, newTotalAmount: Double) = ItemAddedToBasketV1(
                aggregateId = aggregateId,
                ItemAddedToBasketPayloadV1(
                        newItem = newItem,
                        newTotalAmount = newTotalAmount,
                )
        )

        private const val NAME = "item_added_to_basket"
    }
}
