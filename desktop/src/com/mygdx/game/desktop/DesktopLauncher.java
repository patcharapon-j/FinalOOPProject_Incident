package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Incident;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1366;
		config.height = 768;
		config.samples = 2;
		config.vSyncEnabled = true;
		config.resizable = false;
		new LwjglApplication(new Incident(), config);
	}
}
