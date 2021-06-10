import scala.util.Random

object Dice {
    def roll(nDice: Int): Int =
        Seq.fill(nDice)(Random.between(1, 7)).reduce(_ + _)
}
