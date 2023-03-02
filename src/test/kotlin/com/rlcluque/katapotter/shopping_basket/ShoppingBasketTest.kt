package com.rlcluque.katapotter.shopping_basket

import com.rlcluque.katapotter.shared.domain.event.DomainEvent
import com.rlcluque.katapotter.shared.domain.id.BasketId
import com.rlcluque.katapotter.shared.domain.id.BasketItemId
import com.rlcluque.katapotter.shared.domain.id.BookId
import com.rlcluque.katapotter.shopping_basket.basket.application.use_case.AddItemToBasketParameters
import com.rlcluque.katapotter.shopping_basket.basket.domain.Basket
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketMother
import com.rlcluque.katapotter.shopping_basket.basket.domain.BasketRepository
import com.rlcluque.katapotter.shopping_basket.book.domain.BookMother
import com.rlcluque.katapotter.shopping_basket.book.domain.BookRepository
import org.mockito.Mockito
import org.mockito.kotlin.*

open class ShoppingBasketTest {
    protected val basketRepository: BasketRepository = mock()
    protected val bookRepository: BookRepository = mock()

    protected fun shouldNotifyAbout(expectedEvents: List<DomainEvent>) {
        verify(basketRepository, times(1)).notify(
                argThat{
                    similarDomainEvents(this,expectedEvents)
                }
        )
    }

    protected fun shouldNotNotifyAbout() {
        verify(basketRepository, never()).notify(any())
    }

    private fun similarDomainEvents(domainEvents: List<DomainEvent>, expectedEvents: List<DomainEvent>) : Boolean {
        if (domainEvents.count() != expectedEvents.count()) return false

        domainEvents.forEach{ domainEvent ->
            if (expectedEvents.find { expectedEvent -> expectedEvent.isSimilarTo(domainEvent) } == null)
                return false
        }

        return true
    }

    protected fun shouldSave(expectedBasket: Basket) {
        verify(basketRepository, times(1)).save(expectedBasket)
    }

    protected fun shouldNotSave() {
        verify(basketRepository, never()).save(any())
    }

    protected fun shouldFindBookById(bookId: BookId) {
        verify(bookRepository, times(1)).find(bookId)
    }

    protected fun shouldNotFindBookById() {
        verify(bookRepository, never()).find(any())
    }

    protected fun shouldFindBasketById(basketId: BasketId) {
        verify(basketRepository, times(1)).find(basketId)
    }

    protected fun shouldNotFindBasketById() {
        verify(basketRepository, never()).find(any())
    }

    protected fun shouldFindBasketByItemId(itemId: BasketItemId) {
        verify(basketRepository, times(1)).findByItem(itemId)
    }

    protected fun givenExistingBook(bookId: BookId) {
        val existingBook = BookMother.create(id = bookId)

        Mockito.`when`(bookRepository.find(any())).thenReturn(existingBook)
    }

    protected fun givenNotExistingBook() {
        Mockito.`when`(bookRepository.find(any())).thenReturn(null)
    }

    protected fun givenExistingBasket(existingBasket: Basket) {
        Mockito.`when`(basketRepository.find(any())).thenReturn(existingBasket)
    }

    protected fun givenExistingBasket() {
        Mockito.`when`(basketRepository.findByItem(any())).thenReturn(BasketMother.create())
    }

    protected fun givenNotExistingBasket() {
        Mockito.`when`(basketRepository.find(any())).thenReturn(null)
    }
}