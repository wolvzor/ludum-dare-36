package com.wolviegames.tigerstory.player

import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

/**
  * The player is the tiger that gets to Move Around The Screen And Do Things.
  */
trait Player {
  var satietyRating: Int
  var healthRating: Int
  var energyRating: Int
  var intelligenceRating: Int
  var gridPositionX: Int
  var gridPositionY: Int

  def getPositionInformation
  def drawStats(spriteBatch: SpriteBatch)
}

trait BasicPlayer extends Player{
  // These are actions that the player can do with the
  def sleep
  def hunt
  def mate
  def observe
  def move
}

class BasicTiger extends BasicPlayer{
  var satietyRating: Int = 50
  var healthRating: Int = 50
  var energyRating: Int = 50
  var intelligenceRating: Int = 0
  var gridPositionX: Int = 0
  var gridPositionY: Int = 0

  def getPositionInformation: Unit = {

  }

  def sleep: Unit = {

  }

  def hunt: Unit ={

  }

  def mate: Unit = {

  }

  def observe: Unit ={

  }

  def move: Unit ={

  }

  def drawStats(spriteBatch: SpriteBatch): Unit = {
    val font:BitmapFont = new BitmapFont()
    val str: String = "Tiger Statistics:";
    font.draw(spriteBatch, str, 500, 450);

    font.draw(spriteBatch, s"Health: $healthRating", 500, 430)
    font.draw(spriteBatch, s"Energy: $energyRating", 500, 410)
    font.draw(spriteBatch, s"Satiety: $satietyRating", 500, 390)
    font.draw(spriteBatch, s"Intelligence: $intelligenceRating", 500, 370)
  }
}
