package com.wolviegames.tigerstory.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

/**
  * The player is the tiger that gets to Move Around The Screen And Do Things.
  */
trait Player {
  var tigerAlive: Boolean
  var satietyRating: Int
  var healthRating: Int
  var energyRating: Int
  var intelligenceRating: Int
  var gridPositionX: Int
  var gridPositionY: Int
  var tigerTexture: Texture

  def getPositionInformation
  def setTigerTexture(texture: Texture)
  def drawTiger(spriteBatch: SpriteBatch, timeSize: Int)
  def drawStats(spriteBatch: SpriteBatch)
  def statCalculator(stat: String, amount: Int)
}

trait BasicPlayer extends Player{
  // These are actions that the player can do with the
  def sleep
  def hunt
  def mate
  def observe
  def move(maxRows:Int, maxCols:Int, direction: String)
}

class BasicTiger extends BasicPlayer{
  var tigerAlive: Boolean = true
  var satietyRating: Int = 50
  var healthRating: Int = 50
  var energyRating: Int = 50
  var intelligenceRating: Int = 0
  var gridPositionX: Int = 0
  var gridPositionY: Int = 0
  var tigerTexture: Texture = null // you're killing me smalls


  def setTigerTexture(texture: Texture): Unit = {
    tigerTexture = texture
  }

  def getPositionInformation: Unit = {

  }

  def drawTiger(spriteBatch: SpriteBatch, tileSize: Int): Unit ={
    spriteBatch.draw(tigerTexture,tileSize * gridPositionX, tileSize * gridPositionY)
  }

  def drawStats(spriteBatch: SpriteBatch): Unit = {
    val font:BitmapFont = new BitmapFont()
    val str: String = "Tiger Statistics:";
    font.draw(spriteBatch, str, 500, 450);

    font.draw(spriteBatch, s"Alive?: $tigerAlive", 500, 430)
    font.draw(spriteBatch, s"Health: $healthRating", 500, 410)
    font.draw(spriteBatch, s"Energy: $energyRating", 500, 390)
    font.draw(spriteBatch, s"Satiety: $satietyRating", 500, 370)
    font.draw(spriteBatch, s"Intelligence: $intelligenceRating", 500, 350)
  }



  def sleep: Unit = {

  }

  def hunt: Unit ={

  }

  def mate: Unit = {

  }

  def observe: Unit ={

  }

  def move(maxRows:Int, maxCols:Int, direction: String): Unit ={
    println(s"Direction: $direction")
    println(s"grid position x: $gridPositionX")
    println(s"grid position y: $gridPositionY")
    println(s"maxRows: $maxRows")
    println(s"maxCols: $maxCols")

    direction.toLowerCase match {
      case "north" => if (gridPositionY < maxRows-1){
        gridPositionY += 1
        statCalculator("energy", -5)
      }
      case "east" => if (gridPositionX < maxCols-1){
        gridPositionX += 1
        statCalculator("energy", -5)
      }
      case "south" => if (gridPositionY > 0){
        gridPositionY -= 1
        statCalculator("energy", -5)
      }
      case "west" => if (gridPositionX > 0){
        gridPositionX -= 1
        statCalculator("energy", -5)
      }
      case _ => println("Invalid direction")
    }
  }

  /*
   * This is actually a major part of the game, where if the tiger's health goes to 0, DED.
   * Getting some stats to 0 will actually affect other stats.
   */
  def statCalculator(stat: String, amount: Int): Unit ={

    // Tiger energy levels
    // I will make this all matchy and happy laters.
    if (stat == "energy") {
      if (amount + energyRating > 100) energyRating = 100
      else if (amount + energyRating < 0) {
        energyRating = 0
        statCalculator("health", -5)
      }
      else
        energyRating += amount
    }

    // Tiger energy levels
    // If health goes to 0, that's it, thanks for playing, mmm
    if (stat == "health") {
      if (amount + healthRating > 100) healthRating = 100
      else if (amount + healthRating < 0) {
        healthRating = 0
        tigerAlive = false
      }
      else
        healthRating += amount
    }

  }
}
