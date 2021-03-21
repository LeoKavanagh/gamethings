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


  class Deck() {

    private def makeDeck(): Hand = {
      val deck: List[Card] = for {
        rank <-ranks
        suit <- suits
        } yield Card(rank, suit)
      val shuffledDeck = deck
        .map(x => (Random.nextFloat(), x))
        .sortBy(_._1)
        .map(_._2)
      shuffledDeck
    }

    var remaining: Hand = makeDeck()
    var seen: Hand = List()

    // Side effects galore
    def dealCard(): Option[Card] = remaining match {
      case Nil => None
      case x :: Nil => {
	seen = x :: seen
	remaining = Nil
	Some(x)
      }
      case x :: xs => {
        seen = x :: seen
        remaining = xs
        Some(x)
      }
    }

    def shuffle() = {
      remaining = makeDeck()
      seen = List()
    }

  }

}
