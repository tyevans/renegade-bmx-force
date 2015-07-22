package com.tyevans.renegadebmxforce.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyevans.renegadebmxforce.Constants;
import com.tyevans.renegadebmxforce.RenegadeBMXForce;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.title = "Renegade BMX Force";
		config.width = Constants.APP_WIDTH;
		config.height = Constants.APP_WIDTH;
		new LwjglApplication(new RenegadeBMXForce(), config);
	}
}
