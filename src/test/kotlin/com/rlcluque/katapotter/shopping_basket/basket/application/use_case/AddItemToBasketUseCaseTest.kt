package com.rlcluque.katapotter.shopping_basket.basket.application.use_case

import com.rlcluque.katapotter.shared.domain.event.DomainEvent
import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shopping_basket.basket.domain.Basket
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketRepository
import com.rlcluque.katapotter.shopping_basket.basket.domain.ItemAddedToBasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketItemMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.value_object.BasketTotalAmountMother
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

    private fun shouldNotifyAbout(expectedEvents: List<DomainEvent>) {
        verify(basketRepository, times(1)).notify(
                argThat{
                    similarDomainEvents(this,expectedEvents)
                }
        )
    }

    private fun similarDomainEvents(domainEvents: List<DomainEvent>, expectedEvents: List<DomainEvent>) : Boolean {
        if (domainEvents.count() != expectedEvents.count()) return false

        domainEvents.forEach{ domainEvent ->
            if (expectedEvents.find { expectedEvent -> expectedEvent.isSimilarTo(domainEvent) } == null)
                return false
        }

        return true
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

    private fun givenExistingBook(bookId: BookId) {
        val existingBook = BookMother.create(id = bookId)

        Mockito.`when`(bookRepository.find(any())).thenReturn(existingBook)
    }

    private fun givenExistingBasket(existingBasket: Basket) {
        Mockito.`when`(basketRepository.find(any())).thenReturn(existingBasket)
    }
}