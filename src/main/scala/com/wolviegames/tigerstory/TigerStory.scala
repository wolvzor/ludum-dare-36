package com.wolviegames.tigerstory

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx, Input}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wolviegames.tigerstory.player.{BasicPlayer, BasicTiger, Player}
import org.joda.time.DateTime

class TigerStory extends Game {

  val TILE_SIZE = 90
  val GAME_ROWS = 5
  val GAME_COLS = 5

  // I will enum/class the SHIT out of this in a bit
  var gridMcGridFace = Array.ofDim[Texture](GAME_ROWS,GAME_COLS)

  // I REALLY HATE THIS, WHY LIBGDX WHY
  var spriteBatch: SpriteBatch = null
  var tigerTexture: Texture = null
  var defaultTexture: Texture = null

  var tigerPlayer: BasicTiger = new BasicTiger()

  var inputTimestamp = DateTime.now()

  def buildTextureMap: Map[String, Texture] = Map(
    ("boarTexture", new Texture(Gdx.files.internal("boar.jpg"))),
    ("caveTexture", new Texture(Gdx.files.internal("cave.jpg"))),
    ("deerTexture", new Texture(Gdx.files.internal("deer.jpg"))),
    ("grassTexture", new Texture(Gdx.files.internal("grass.jpg"))),
    ("mateTexture", new Texture(Gdx.files.internal("mate.jpg"))),
    ("rabbitTexture", new Texture(Gdx.files.internal("rabbit.jpg")))
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
    defaultTexture = new Texture(Gdx.files.internal("grass.jpg"))
    inputTimestamp = DateTime.now()

    val textureMap = buildTextureMap

    populateGrid(textureMap, defaultTexture)

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

    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) // Apparently this clears the screen; wtf gdx
    spriteBatch.begin

    // And then drawing things happen here O_O
    for{
      i <- 0 until GAME_ROWS
      j <- 0 until GAME_COLS
    } spriteBatch.draw(gridMcGridFace(i)(j), i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE)

    // Draw Tiger
    tigerPlayer.drawTiger(spriteBatch, TILE_SIZE)
    tigerPlayer.drawStats(spriteBatch)


    spriteBatch.end
  }

  def populateGrid(textureMap: Map[String, Texture], defaultTexture: Texture): Unit = {
    gridMcGridFace(0)(0) = textureMap.getOrElse("caveTexture", defaultTexture)
    gridMcGridFace(0)(1) = textureMap.getOrElse("boarTexture", defaultTexture)
    gridMcGridFace(0)(2) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(0)(3) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(0)(4) = textureMap.getOrElse("grassTexture", defaultTexture)

    gridMcGridFace(1)(0) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(1)(1) = textureMap.getOrElse("boarTexture", defaultTexture)
    gridMcGridFace(1)(2) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(1)(3) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(1)(4) = textureMap.getOrElse("grassTexture", defaultTexture)

    gridMcGridFace(2)(0) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(2)(1) = textureMap.getOrElse("deerTexture", defaultTexture)
    gridMcGridFace(2)(2) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(2)(3) = textureMap.getOrElse("mateTexture", defaultTexture)
    gridMcGridFace(2)(4) = textureMap.getOrElse("grassTexture", defaultTexture)

    gridMcGridFace(3)(1) = textureMap.getOrElse("rabbitTexture", defaultTexture)
    gridMcGridFace(3)(2) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(3)(3) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(3)(4) = textureMap.getOrElse("deerTexture", defaultTexture)
    gridMcGridFace(3)(0) = textureMap.getOrElse("grassTexture", defaultTexture)

    gridMcGridFace(4)(0) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(4)(1) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(4)(2) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(4)(3) = textureMap.getOrElse("grassTexture", defaultTexture)
    gridMcGridFace(4)(4) = textureMap.getOrElse("deerTexture", defaultTexture)


  }
}