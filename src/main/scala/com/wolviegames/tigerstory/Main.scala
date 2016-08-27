package com.wolviegames.tigerstory

import com.badlogic.gdx.backends.lwjgl._

object Main extends App {
  val cfg = new LwjglApplicationConfiguration
  cfg.title = "A Tiger's Story"
  cfg.height = 480
  cfg.width = 800
  cfg.forceExit = false
  new LwjglApplication(new TigerStory, cfg)
}