import scala.util.Random

object Dice {
    def dice(nRolls: Int): Seq[Int] =
        Seq.fill(nRolls)(Random.between(1, 7))
}