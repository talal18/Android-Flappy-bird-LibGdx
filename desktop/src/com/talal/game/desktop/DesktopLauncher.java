package com.talal.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.talal.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDemo.WIDTH; // apply the width
		config.height = FlappyDemo.HEIGHT; // apply the height
		config.title = FlappyDemo.TITLE; // apply the title
		new LwjglApplication(new FlappyDemo(), config);
	}
}
