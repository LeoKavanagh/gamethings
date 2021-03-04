import Dice.dice
import Cards.{makeDeck, chooseCards}

@main def hello: Unit = {

    val num = dice(1)
    val deck = makeDeck()
    val cards = chooseCards(deck, 1)

    println("Hello world!")
    println(msg)
    println(s"You rolled a ${num.mkString(", ")}")
    println(s"You got a ${cards.mkString(", ")}")
}

def msg = "I was compiled by Scala 3. :)"




