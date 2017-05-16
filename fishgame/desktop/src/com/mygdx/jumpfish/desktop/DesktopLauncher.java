package com.mygdx.jumpfish.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.jumpfish.AlieInJumpFish;

public class DesktopLauncher {
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = false;
	public static void main (String[] arg) {
		 int g = 2;
	      int z = "ZPJ".hashCode() % 3000;
	      int i = "ZUH".hashCode() % 3000;
	      for (int r = 0; r <= i; r++)
	         g = (g ^ r) % z;
	     System.out.println("dfslkjlkdsjgflkdsjdsgfdg"+ g);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jumpfish";
		config.useGL30 = false;
		config.width = 800;
		config.height = 480;
		config.vSyncEnabled = true;
	//	config.resizable=false; // что то надо придумать
		new LwjglApplication(new AlieInJumpFish(null), config);
	}
}
