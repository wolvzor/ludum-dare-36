package com.wolviegames.tigerstory

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class TigerStory extends Game{

  val TILE_SIZE = 90
  val GAME_ROWS = 5
  val GAME_COLS = 5

  // I will enum/class the SHIT out of this in a bit
  var gridMcGridFace = Array.ofDim[Texture](GAME_ROWS,GAME_COLS)

  override def create(): Unit = {
    println("Tiger's Story")
  }

  override def render(): Unit = {

    // THESE DO NOT BELONG HERE
    // YOUR INITIALIZATION IS BAD AND YOU SHOULD FEEL BAD
    // SERIOUSLY THESE NEED TO MOVE SOON
    val spriteBatch: SpriteBatch = new SpriteBatch()

    val textureMap: Map[String, Texture] = Map(
      "tigerTexture",  new Texture(Gdx.files.internal("tiger-small.jpg")),
      "boarTexture", new Texture(Gdx.files.internal("boar.jpg")),
      "caveTexture", new Texture(Gdx.files.internal("cave.jpg")),
      "deerTexture", new Texture(Gdx.files.internal("deer.jpg")),
      "grassTexture", new Texture(Gdx.files.internal("grass.jpg")),
      "mateTexture", new Texture(Gdx.files.internal("mate.jpg")),
      "rabbitTexture", new Texture(Gdx.files.internal("rabbit.jpg")),
    )

    // Populate GridMcGridFace
    populateGrid(textureMap)



    // Where the actual drawing is taking place.
    // Like it should.
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) // Apparently this clears the screen; wtf gdx
    spriteBatch.begin

    // And then drawing things happen here O_O

    // Resizing the 120s to 90s to see if it looks better D:
    spriteBatch.draw(caveTexture, 10, 10, TILE_SIZE, TILE_SIZE) // home base!
    spriteBatch.draw(boarTexture, TILE_SIZE + 10, TILE_SIZE + 10, TILE_SIZE, TILE_SIZE)
    spriteBatch.draw(deerTexture, TILE_SIZE + 10, 10, TILE_SIZE, TILE_SIZE)
    spriteBatch.draw(grassTexture, 10, TILE_SIZE + 10, TILE_SIZE, TILE_SIZE)
    spriteBatch.draw(mateTexture, 2 * TILE_SIZE + 10, TILE_SIZE + 10, TILE_SIZE, TILE_SIZE)
    spriteBatch.draw(rabbitTexture, TILE_SIZE + 10, 2 * TILE_SIZE  + 10, TILE_SIZE, TILE_SIZE)

    // Tiger needs to go last (poor thing)
    spriteBatch.draw(tigerTexture,10,10)

    spriteBatch.end
  }

  def populateGrid(textureMap: Map[String, Texture]): Unit = {
    gridMcGridFace(0)(0) =
  }
}