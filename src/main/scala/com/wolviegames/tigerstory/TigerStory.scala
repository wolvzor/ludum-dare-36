package com.wolviegames.tigerstory

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx, Input}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wolviegames.tigerstory.player.{BasicPlayer, BasicTiger, Player}
import com.wolviegames.tigerstory.world.{GameTile, Tile}
import org.joda.time.DateTime

class TigerStory extends Game {

  val TILE_SIZE = 90
  val GAME_ROWS = 5
  val GAME_COLS = 5

  // I will enum/class the SHIT out of this in a bit
  var gridMcGridFace = Array.ofDim[GameTile](GAME_ROWS,GAME_COLS)

  // I REALLY HATE THIS, WHY LIBGDX WHY
  var spriteBatch: SpriteBatch = null
  var tigerTexture: Texture = null
  var defaultTile: GameTile = null

  var tigerPlayer: BasicTiger = new BasicTiger()

  var inputTimestamp = DateTime.now()

  def buildTileMap: Map[String, GameTile] = Map(
    ("boar", new GameTile("boar", new Texture(Gdx.files.internal("boar.jpg")))),
    ("cave", new GameTile("cave", new Texture(Gdx.files.internal("cave.jpg")))),
    ("deer", new GameTile("deer", new Texture(Gdx.files.internal("deer.jpg")))),
    ("grass", new GameTile("grass",new Texture(Gdx.files.internal("grass.jpg")))),
    ("mate", new GameTile("mate",new Texture(Gdx.files.internal("mate.jpg")))),
    ("rabbit", new GameTile("rabbit",new Texture(Gdx.files.internal("rabbit.jpg"))))
  )

  def loadSounds: Map[String, Sound] = Map (
    ("birds", Gdx.audio.newSound(Gdx.files.internal("sounds//Bird Song-SoundBible.com-1355483122.mp3"))),
    ("nature", Gdx.audio.newSound(Gdx.files.internal("sounds//Nature Ambiance-SoundBible.com-1444637890.mp3"))),
    ("tiger", Gdx.audio.newSound(Gdx.files.internal("sounds//Roaring Lion-SoundBible.com-527774719.mp3")))
  )


  override def create(): Unit = {
    println("Tiger's Story")

    spriteBatch = new SpriteBatch()
    tigerTexture = new Texture(Gdx.files.internal("tiger-small.jpg"))
    defaultTile = new GameTile("default", new Texture(Gdx.files.internal("grass.jpg")))
    inputTimestamp = DateTime.now()

    val tileMap = buildTileMap

    populateGrid(tileMap, defaultTile)

    // tiger inits
    tigerPlayer.setTigerTexture(tigerTexture)

    val soundMap = loadSounds
    soundMap.getOrElse("nature",null).loop(1.0f)

  }

  override def render(): Unit = {

    // Player Input - limit window to .125 seconds so we aren't zooming around the place
    if (DateTime.now().getMillis > (inputTimestamp.getMillis + 125)) {
      if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
        tigerPlayer.move(GAME_ROWS, GAME_COLS, "north")
        inputTimestamp = DateTime.now()
      }
      if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
        tigerPlayer.move(GAME_ROWS, GAME_COLS, "south")
        inputTimestamp = DateTime.now()
      }
      if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        tigerPlayer.move(GAME_ROWS, GAME_COLS, "east")
        inputTimestamp = DateTime.now()
      }
      if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
        tigerPlayer.move(GAME_ROWS, GAME_COLS, "west")
        inputTimestamp = DateTime.now()
      }
    }

    // Drawing code

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) // Apparently this clears the screen; wtf gdx
    spriteBatch.begin

    // Drawing tiles
    for{
      i <- 0 until GAME_ROWS
      j <- 0 until GAME_COLS
    } spriteBatch.draw(gridMcGridFace(i)(j).tileTexture, i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE)

    // Draw Tiger
    tigerPlayer.drawTiger(spriteBatch, TILE_SIZE)
    tigerPlayer.drawStats(spriteBatch)

    spriteBatch.end
  }

  def populateGrid(tileMap: Map[String, GameTile], defaultTexture: GameTile): Unit = {
    gridMcGridFace(0)(0) = tileMap.getOrElse("cave", defaultTexture)
    gridMcGridFace(0)(1) = tileMap.getOrElse("boar", defaultTexture)
    gridMcGridFace(0)(2) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(0)(3) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(0)(4) = tileMap.getOrElse("grass", defaultTexture)

    gridMcGridFace(1)(0) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(1)(1) = tileMap.getOrElse("boar", defaultTexture)
    gridMcGridFace(1)(2) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(1)(3) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(1)(4) = tileMap.getOrElse("grass", defaultTexture)

    gridMcGridFace(2)(0) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(2)(1) = tileMap.getOrElse("deer", defaultTexture)
    gridMcGridFace(2)(2) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(2)(3) = tileMap.getOrElse("mate", defaultTexture)
    gridMcGridFace(2)(4) = tileMap.getOrElse("grass", defaultTexture)

    gridMcGridFace(3)(1) = tileMap.getOrElse("rabbit", defaultTexture)
    gridMcGridFace(3)(2) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(3)(3) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(3)(4) = tileMap.getOrElse("deer", defaultTexture)
    gridMcGridFace(3)(0) = tileMap.getOrElse("grass", defaultTexture)

    gridMcGridFace(4)(0) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(4)(1) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(4)(2) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(4)(3) = tileMap.getOrElse("grass", defaultTexture)
    gridMcGridFace(4)(4) = tileMap.getOrElse("deer", defaultTexture)
  }
}