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
                items = listOf(existingBasketItem,
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
    fun `GIVEN an existing basket with one existing potter book WHEN I add to basket a different existing book THEN total should be 15,2 euros`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBasketItem = BasketItemMother.create()
        val existingBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem),
                totalAmount = BasketTotalAmountMother.create(8.0),
        )
        val expectedBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem,
                        BasketItemMother.create(
                                itemId = inputParameters.itemId,
                                bookId = inputParameters.bookId,
                        )
                ),
                totalAmount = BasketTotalAmountMother.create(value = 15.2),
        )
        val expectedEvents = listOf(
                ItemAddedToBasketMother.create(
                        aggregateId = inputParameters.basketId.value,
                        newItem = inputParameters.itemId.value,
                        newTotalAmount = 15.2,
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
    fun `GIVEN an existing basket with 2 different existing potter book WHEN I add to basket a same book THEN total should be 23,2 euros`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBasketItem1 = BasketItemMother.create()
        val existingBasketItem2 = BasketItemMother.create(bookId = existingBasketItem1.bookId)
        val existingBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem1,existingBasketItem2),
                totalAmount = BasketTotalAmountMother.create(8.0),
        )
        val expectedBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(existingBasketItem1,existingBasketItem2,
                        BasketItemMother.create(
                                itemId = inputParameters.itemId,
                                bookId = inputParameters.bookId,
                        )
                ),
                totalAmount = BasketTotalAmountMother.create(value = 23.2),
        )
        val expectedEvents = listOf(
                ItemAddedToBasketMother.create(
                        aggregateId = inputParameters.basketId.value,
                        newItem = inputParameters.itemId.value,
                        newTotalAmount = 23.2,
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
    fun `GIVEN a not existing basket WHEN I add to basket some book THEN nothing should happen`() {
        val inputParameters = AddItemToBasketParametersMother.create()

        givenNotExistingBasket()

        whenIAddItemToBasket(inputParameters)

        shouldNotFindBookById()
        shouldNotSave()
        shouldNotNotifyAbout()
    }

    @Test
    fun `GIVEN an existing basket WHEN I add to basket a book that does not exist THEN nothing should happen`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBasket = BasketMother.create()

        givenExistingBasket(existingBasket)
        givenNotExistingBook()

        whenIAddItemToBasket(inputParameters)

        shouldNotSave()
        shouldNotNotifyAbout()
    }

    private fun whenIAddItemToBasket(inputParameters: AddItemToBasketParameters) {
        systemUnderTest.execute(inputParameters)
    }
}