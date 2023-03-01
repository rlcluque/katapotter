package com.rlcluque.katapotter.shopping_basket.basket.application.use_case

import com.rlcluque.katapotter.shared.domain.event.shopping_basket.basket.ItemAddedToBasketV1
import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shopping_basket.basket.domain.Basket
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketRepository
import com.rlcluque.katapotter.shopping_basket.basket.domain.ItemAddedToBasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItemMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmountMother
import com.rlcluque.katapotter.shopping_basket.book.domain.Book
import com.rlcluque.katapotter.shopping_basket.book.domain.BookMother
import com.rlcluque.katapotter.shopping_basket.book.domain.BookRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.*

internal class AddItemToBasketUseCaseTest {
    private val basketRepository: BasketRepository = mock()
    private val bookRepository: BookRepository = mock()

    private val systemUnderTest = AddItemToBasketUseCase(basketRepository,bookRepository)

    @Test
    fun `GIVEN an existing basket with one existing potter book WHEN I add to basket the same book THEN total should be 16 euros`() {
        val inputParameters = AddItemToBasketParametersMother.create()
        val existingBook = BookMother.create(id = inputParameters.bookId)
        val existingBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(
                        BasketItemMother.create(bookId = inputParameters.bookId),
                ),
                totalAmount = BasketTotalAmountMother.create(8.0),
        )

        val expectedBasket = BasketMother.create(
                id = inputParameters.basketId,
                items = listOf(
                        BasketItemMother.create(bookId = inputParameters.bookId),
                        BasketItemMother.create(
                                itemId = inputParameters.itemId,
                                bookId = inputParameters.bookId,
                        )
                ),
                totalAmount = BasketTotalAmountMother.create(value = 16.0),
        )
        val expectedEvents = listOf(
                ItemAddedToBasketMother.create(
                        aggregateId = inputParameters.basketId.toString(),
                        newItem = inputParameters.itemId.toString(),
                        newTotalAmount = 16.0,
                )
        )

        givenExistingBasket(existingBasket)
        givenExistingBook(existingBook)

        whenIAddItemToBasket(inputParameters)

        shouldFindBasketById(inputParameters.basketId)
        shouldFindBookById(inputParameters.bookId)
        shouldSave(expectedBasket)
        shouldNotifyAbout(expectedEvents)
    }

    private fun shouldNotifyAbout(expectedEvents: List<ItemAddedToBasketV1>) {
        verify(basketRepository, times(1)).notify(expectedEvents)
    }

    private fun shouldSave(expectedBasket: Basket) {
        verify(basketRepository,times(1)).save(expectedBasket)
    }

    private fun shouldFindBookById(bookId: BookId) {
        verify(bookRepository, times(1)).find(bookId)
    }

    private fun shouldFindBasketById(basketId: BasketId) {
        verify(basketRepository, times(1)).find(basketId)
    }

    private fun whenIAddItemToBasket(inputParameters: AddItemToBasketParameters) {
        systemUnderTest.execute(inputParameters)
    }

    private fun givenExistingBook(existingBook: Book) {
        Mockito.`when`(bookRepository.find(any())).thenReturn(existingBook)
    }

    private fun givenExistingBasket(existingBasket: Basket) {
        Mockito.`when`(basketRepository.find(any())).thenReturn(existingBasket)
    }
}