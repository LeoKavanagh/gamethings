import scala.util.Random

object Dice:

    def dice(nRolls: Int): Seq[Int] = 
        val r = Seq.fill(nRolls)(Random.between(1, 7))
        r