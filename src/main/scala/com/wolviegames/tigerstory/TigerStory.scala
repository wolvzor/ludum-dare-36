package com.wolviegames.tigerstory

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class TigerStory extends Game{

  val TILE_SIZE = 90

  override def create(): Unit = {
    println("Tiger's Story")
  }

  override def render(): Unit = {

    // THESE DO NOT BELONG HERE
    // YOUR INITIALIZATION IS BAD AND YOU SHOULD FEEL BAD
    val spriteBatch: SpriteBatch = new SpriteBatch()

    // Different textures for funsies

    // 60x60
    val tigerTexture: Texture = new Texture(Gdx.files.internal("tiger-small.jpg"))

    // 120x120
    val boarTexture: Texture = new Texture(Gdx.files.internal("boar.jpg"))
    val caveTexture: Texture = new Texture(Gdx.files.internal("cave.jpg"))
    val deerTexture: Texture = new Texture(Gdx.files.internal("deer.jpg"))
    val grassTexture: Texture = new Texture(Gdx.files.internal("grass.jpg"))
    val mateTexture: Texture = new Texture(Gdx.files.internal("mate.jpg"))
    val rabbitTexture: Texture = new Texture(Gdx.files.internal("rabbit.jpg"))




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
}