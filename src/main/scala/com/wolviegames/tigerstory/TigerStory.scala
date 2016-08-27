package com.wolviegames.tigerstory

import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class TigerStory extends Game{

  override def create(): Unit = {
    println("Tiger's Story")
  }

  override def render(): Unit = {

    // THESE DO NOT BELONG HERE
    // YOUR INITIALIZATION IS BAD AND YOU SHOULD FEEL BAD
    val spriteBatch: SpriteBatch = new SpriteBatch()
    val tigerTexture: Texture = new Texture(Gdx.files.internal("tiger.jpg"))


    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) // Apparently this clears the screen; wtf gdx
    spriteBatch.begin

    // And then drawing things happen here O_O
    spriteBatch.draw(tigerTexture,10,10)

    spriteBatch.end
  }
}