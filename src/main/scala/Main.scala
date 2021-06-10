import com.raquo.laminar.api.L.{*, given}
import Dice.roll
import org.scalajs.dom


object App:

  sealed trait Command
  case class Update(value: Int) extends Command
  case class Set(value: Int) extends Command


  def Counter(label: String): HtmlElement =

    val countVar = Var(0)
    val nDiceVar = Var(2)
    val nDice = 2
    val allowedDice = 1 to 5

    val commandObserver = Observer[Command] {
      case Update(value) => countVar.update(_ + value)
      case Set(value) => countVar.set(value)
    }

    div(
      p(label + ": ", b(child.text <-- countVar.signal),
      p("No. Dice: ",
        select(
          value <-- nDiceVar.signal.map(_.toString),
          inContext { thisNode =>
            onChange.mapTo(thisNode.ref.value.toInt) --> nDiceVar },
          allowedDice.map {n => option(value := n.toString, n)
          }
        )
      ),
      p("Roll The Dice: ", button("?", onClick.mapTo(Update(roll(nDiceVar.now()))) --> commandObserver)),
      p("Reset Sum: ", button("0", onClick.mapTo(Set(0)) --> commandObserver))
      )
    )

  val app = div(
    h1("Roll the Dice!"),
    Counter("Sum of Rolls")
  )


  def main(args: Array[String]): Unit =
    documentEvents.onDomContentLoaded.foreach {
      _ =>
        val appContainer = dom.document.querySelector("#appContainer")
        render(appContainer, app)
    }(unsafeWindowOwner)

