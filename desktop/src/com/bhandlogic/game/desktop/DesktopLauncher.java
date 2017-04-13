package com.bhandlogic.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bhandlogic.game.PoopingGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PoopingGame.WIDTH;
		config.height= PoopingGame.HEIGHT;
		config.title =PoopingGame.TITLE;
		new LwjglApplication(new PoopingGame(), config);
	}
}
