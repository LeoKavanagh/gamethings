import scala.util.Random

object Cards {

  val suits = List("Clubs", "Diamonds", "Hearts", "Spades")
  val ranks = List("Ace", "Two", "Three", "Four", "Five",
                   "Six", "Seven", "Eight", "Nine", "Ten",
                   "Jack", "Queen", "King")

  case class Card (rank: String, suit: String) {
    override def toString() = s"${rank} of ${suit}"
  }

  type Hand = List[Card]

  def makeDeck(): Hand = {
    val deck: List[Card] = for {
      rank <-ranks
      suit <- suits
      } yield Card(rank, suit)
    deck
  }

  def dealCards(deck: Hand, n: Int): (Hand, Hand) = {
    val hand = deck
      .map(x => (Random.nextFloat(), x))
      .sortBy(_._1)
      .map(_._2)
      .take(n)
    val restOfDeck = deck.diff(hand)
    (hand, restOfDeck)
  }
}
