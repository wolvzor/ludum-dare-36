package com.wolviegames.tigerstory.world

import com.badlogic.gdx.graphics.Texture

trait Tile {
  val tileType: String // enum this laters
  val tileTexture: Texture
}

class GameTile(`type`: String, texture: Texture) extends Tile {
  val tileType: String = `type`
  val tileTexture: Texture = texture
}
