import Dice.dice
import Cards.{Card, Hand, Deck}
import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def appendTextNode(targetNode: dom.Node, level: String, text: String): Unit = {
    val newNode = document.createElement(level)
    newNode.textContent = text
    newNode.setAttribute("style", "text-align: center")
    targetNode.appendChild(newNode)
  }
  
  def changePar(targetNode: dom.Node, text: String, elementId: String): Unit = {
    val parNode = document.getElementById(elementId)
    parNode.textContent = text
  }

  def addDieRoll(targetTag: String): Unit = {
    val diceValues = dice(2)
    val msg = s"You rolled ${diceValues.mkString(" and ")}, giving ${diceValues.sum} "
    changePar(document.body, msg, targetTag)
  }

  def cardMessage(card: Option[Card]): String = card match {
    case Some(x) => s"You were dealt the ${x}"
    case None => "No cards left. Shuffle the deck."
  }

  def gameButton(buttonText: String, buttonFunction: (String => Unit), thing: String): org.scalajs.dom.Node = {

    val button = document.createElement("button")
    button.textContent = buttonText
    button.setAttribute("class", "block")
    button.addEventListener("click", { (e: dom.MouseEvent) =>
      buttonFunction(thing)
    })
    button
  }

  def setupUI(): Unit = {

    val deck = new Deck()
    println(deck.remaining)

    def pickACard(deck: Deck, targetTag: String): Unit = {

      val card = deck.dealCard()
      val msg: String = cardMessage(card)
      
      changePar(document.getElementById("cardGame"), msg, "cardGameResult")
    }

    appendTextNode(document.body, "h1", "Game Things")

    val mainDiv = document.createElement("div")
    mainDiv.setAttribute("id", "mainDiv")
    mainDiv.setAttribute("style", "justify-content: center; text-align: center;")

    document.body.appendChild(mainDiv)

    /* ==========================================================================  */

    val diceElemId = "diceGame"
    val diceDiv = document.createElement("div")
    diceDiv.setAttribute("id", diceElemId)
    diceDiv.setAttribute("style", "justify-content: center; text-align: center;")

    val diceHeader = document.createElement("h2")
    diceHeader.textContent = "Dice"
    diceHeader.setAttribute("id", diceElemId)
    diceHeader.setAttribute("style", "text-align: center")

    val diceResult = document.createElement("h3")
    diceResult.setAttribute("id", diceElemId + "Result")
    diceResult.setAttribute("style", "text-align: center")

    diceDiv.appendChild(diceHeader)
    diceDiv.appendChild(diceResult)
    document.body.appendChild(diceDiv)

    val diceButton = gameButton("Roll The Dice", addDieRoll, "diceGameResult")
    document.getElementById(diceElemId).appendChild(diceButton)

    /* ==========================================================================  */

    val cardDiv = document.createElement("div")
    val cardElemId: String = "cardGame"
    cardDiv.setAttribute("id", cardElemId)
    cardDiv.setAttribute("style", "justify-content: center; text-align: center;")

    val cardHeader = document.createElement("h2")
    cardHeader.textContent = "Card"
    cardHeader.setAttribute("id", cardElemId)
    cardHeader.setAttribute("style", "text-align: center")

    val cardResult = document.createElement("h3")
    cardResult.setAttribute("id", cardElemId + "Result")
    cardResult.setAttribute("style", "text-align: center")

    cardDiv.appendChild(cardHeader)
    cardDiv.appendChild(cardResult)
    document.body.appendChild(cardDiv)

    val dealButton = document.createElement("button")
    dealButton.textContent = "Pick A Card"
    dealButton.setAttribute("id", "dealButton")
    dealButton.setAttribute("class", "block")
    
    dealButton.addEventListener("click", { (e: dom.MouseEvent) =>
      pickACard(deck, cardElemId)
    })

    document.getElementById(cardElemId).appendChild(dealButton)

    val shuffleButton = document.createElement("button")
    shuffleButton.textContent = "Shuffle The Deck"
    shuffleButton.setAttribute("id", "shuffleButton")
    shuffleButton.setAttribute("class", "block")
    
    shuffleButton.addEventListener("click", { (e: dom.MouseEvent) =>
      deck.shuffle()
    })

    document.getElementById(cardElemId).appendChild(shuffleButton)
   
  }

  def main(args: Array[String]): Unit = {

    document.addEventListener("DOMContentLoaded", { (e: dom.Event) =>
      setupUI()
    })

  }

}

