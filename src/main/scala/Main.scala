import Dice.dice
import Cards.{makeDeck, chooseCards}
import org.scalajs.dom
import org.scalajs.dom.document

object Main {

  def appendTextNode(targetNode: dom.Node, level: String, text: String): Unit = {
    val newNode = document.createElement(level)
    newNode.textContent = text
    newNode.setAttribute("style", "text-align: center")
    targetNode.appendChild(newNode)
  }

  def setHeader(targetNode: dom.Node): Unit = {
    appendTextNode(targetNode, "h1", "Game Things")
  }

  def changePar(targetNode: dom.Node, text: String, elementId: String): Unit = {
    val parNode = document.getElementById(elementId)
    parNode.textContent = text
  }

  def addDieRoll(targetTag: String): Unit = {
    val diceValues = dice(1)
    val msg = s"You rolled ${diceValues.mkString}"
    changePar(document.body, msg, targetTag)
  }

  def pickACard(targetTag: String): Unit = {
    val deck = makeDeck()
    val hand = chooseCards(deck, 5)._1
    val msg = s"You got ${hand.mkString(", ")}"
    changePar(document.body, msg, targetTag)
  }

  def appendGame(targetNode: dom.Node, elemId: String, headerText: String): Unit = {

    val gameDiv = document.createElement("div")
    gameDiv.setAttribute("id", elemId)
    gameDiv.setAttribute("style", "justify-content: center; text-align: center;")

    val gameHeader = document.createElement("h2")
    gameHeader.textContent = headerText
    gameHeader.setAttribute("id", elemId)
    gameHeader.setAttribute("style", "text-align: center")

    val gameResult = document.createElement("h3")
    gameResult.setAttribute("id", elemId + "Result")
    gameResult.setAttribute("style", "text-align: center")

    gameDiv.appendChild(gameHeader)
    gameDiv.appendChild(gameResult)
    targetNode.appendChild(gameDiv)

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

    setHeader(document.body)

    val mainDiv = document.createElement("div")
    mainDiv.setAttribute("id", "mainDiv")
    mainDiv.setAttribute("style", "justify-content: center; text-align: center;")

    document.body.appendChild(mainDiv)

    appendGame(document.body, elemId="diceGame", headerText="Dice")
    val diceButton = gameButton("Roll The Dice", addDieRoll, "diceGameResult")
    document.getElementById("diceGame").appendChild(diceButton)

    appendGame(document.body, elemId="cardGame", headerText="Card")
    val cardButton = gameButton("Pick A Card", pickACard, "cardGameResult")
    document.getElementById("cardGame").appendChild(cardButton)

  }

  def main(args: Array[String]): Unit = {

    document.addEventListener("DOMContentLoaded", { (e: dom.Event) =>
      setupUI()
    })

  }

}

