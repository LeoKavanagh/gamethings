#  Game Things

My other branch uses State Monads.

## Background

This started out as a toy project for playing with Scala.js, Scala 3, and
mutating state (fairly) functionally.

At time of writing it is Scala.js,
Scala 2.13 (because Scala.js DOM is not on Scala 3 yet),
and mutating state with vars and global variables.

But it does what I want it do to, and it works on the very basic web hosting
I'm using.

## Description

The whole thing is a simple web page with a few buttons on it.
The buttons let you roll the dice or draw one card at a time from a deck.

I like to think that I'm helping to keep things Covid-safe by letting people
avoid touching the same dice or cards.

The web page itself is created with copied-and-pasted Scala.js in a fairly
messy and imperative way. I'll clean it up some day...

### Dice Game

The "Roll The Dice" button generates prints two random numbers
between 1 and 6, and then prints them to the screen.

### Card Game

The "Pick A Card" button picks a card from a (shuffled) standard deck of 52
cards and prints it to the screen.

Repeated presses of the button generate new card draws. When you have dealt
all 52 cards (or earlier, if you like), you can reset the whole process and
generate a new shuffled deck by pressing the "Shuffle The Deck".

This is the maintaining-state issue that I mainly wanted to play with.

As an aside, I did not put any effort into deck shuffling algorithms or
random number generators. That's a subject for another day.

## Installation

You'll need Scala, sbt, and
[Node.js](https://nodejs.org/en/) installed.

Install the required Node.js packages from `package-lock.json`:

``
npm ci
``

Generate the Javascript file with

``
sbt fullOptJS
``

Copy the optimised Javascript file to `express/public`:

```
cp target/scala-2.13/gamethings-opt.js express/public/gamethings.opt.js
```

There's a way of making sbt do all of this in the one go for you,
but I didn't bother trying to figure it out.

## Usage

The Scala code itself places all the various HTML tags on the web page.

In and of itself, we can't actually do anything with this file, so we'll load it
into a HTML file and run it with a really simple ExpressJS web app.

``
node express/app.js
``

Now go to [http://localhost:3000](http://localhost:3000).
All going well, you should see a button to press. Bain triail as.

## Contact

Pull Requests, Issues etc are in the usual place.
But to paraphrase Mitch Hedberg, if anyone wants to talk to me
about this project I will be... surprised.

