# KataPotter

This project is trying to resolve with a DDD aproach the fammous kata 'Potter Kata'.

You can check the statement of the exercise in this link: https://codingdojo.org/kata/Potter/

At the moment there is two context:

**Shopping Basket:** Its responsability is to manage shopping baskets of the users and ensure that all rules about discount are applied correctly.

It is composed by two modules
* Basket: Ensure consistency of the items added to each basket and its total amount.
* Book: It has relevant information for the shopping basket context related to Books.

**Shared:** Context with objects used by all others contexts.


Each module within a context has an hexagonal architecture like next:
* Application:
  * UseCase
  * Interaction
    * CommandHandler
    * QueryHandler
    * Command
    * Query
    * EventHandler
* Domain:
  * ValueObject
* Infrastructure
  * PrimaryAdapter
  * SecondaryAdapter
