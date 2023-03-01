package com.rlcluque.katapotter.shopping_basket.basket.application.use_case

import com.rlcluque.katapotter.shopping_basket.ShoppingBasketTest
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.ItemAddedToBasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItemMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmountMother
import org.junit.jupiter.api.Test

internal class AddItemToBasketUseCaseTest : ShoppingBasketTest() {

    private val systemUnderTest = AddItemToBasketUseCase(basketRepository,bookRepository)

    @Test
    fun `GIVEN an existing basket with one existing potter book WHEN I add to basket the same book THEN total should be 16 euros`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBasketItem = BasketItemMother.create(
                bookId = inputParameters.bookId,
        )
        val existingBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem),
                totalAmount = BasketTotalAmountMother.create(8.0),
        )
        val expectedBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(
                        existingBasketItem,
                        BasketItemMother.create(
                                itemId = inputParameters.itemId,
                                bookId = inputParameters.bookId,
                        )
                ),
                totalAmount = BasketTotalAmountMother.create(value = 16.0),
        )
        val expectedEvents = listOf(
                ItemAddedToBasketMother.create(
                        aggregateId = inputParameters.basketId.value,
                        newItem = inputParameters.itemId.value,
                        newTotalAmount = 16.0,
                )
        )

        givenExistingBasket(existingBasket)
        givenExistingBook(inputParameters.bookId)

        whenIAddItemToBasket(inputParameters)

        shouldFindBasketById(inputParameters.basketId)
        shouldFindBookById(inputParameters.bookId)
        shouldSave(expectedBasket)
        shouldNotifyAbout(expectedEvents)
    }

    @Test
    fun `GIVEN an existing basket with one existing potter book WHEN I add to basket the same book THEN total should be 16 euros`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBasketItem = BasketItemMother.create(
                bookId = inputParameters.bookId,
        )
        val existingBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem),
                totalAmount = BasketTotalAmountMother.create(8.0),
        )
        val expectedBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(
                        existingBasketItem,
                        BasketItemMother.create(
                                itemId = inputParameters.itemId,
                                bookId = inputParameters.bookId,
                        )
                ),
                totalAmount = BasketTotalAmountMother.create(value = 16.0),
        )
        val expectedEvents = listOf(
                ItemAddedToBasketMother.create(
                        aggregateId = inputParameters.basketId.value,
                        newItem = inputParameters.itemId.value,
                        newTotalAmount = 16.0,
                )
        )

        givenExistingBasket(existingBasket)
        givenExistingBook(inputParameters.bookId)

        whenIAddItemToBasket(inputParameters)

        shouldFindBasketById(inputParameters.basketId)
        shouldFindBookById(inputParameters.bookId)
        shouldSave(expectedBasket)
        shouldNotifyAbout(expectedEvents)
    }

    private fun whenIAddItemToBasket(inputParameters: AddItemToBasketParameters) {
        systemUnderTest.execute(inputParameters)
    }
}