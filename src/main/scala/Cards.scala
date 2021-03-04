import scala.util.Random

object Cards:

  enum Suit:
    case Clubs, Diamonds, Hearts, Spades

  enum Rank:
    case Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King

  import Suit._
  import Rank._

  case class Card (rank: Rank, suit: Suit):
    override def toString() = s"${rank} of ${suit}"


  type Hand = Array[Card]

  def makeDeck(): Hand =
    val deck: Array[Card] = for {
      rank <- Rank.values
      suit <- Suit.values 
      } yield Card(rank, suit)
    deck

  def chooseCards(deck: Hand, n: Int): Hand =
    deck
      .map(x => (Random.nextFloat(), x))
      .sortBy(_._1)
      .map(_._2)
      .take(n)
