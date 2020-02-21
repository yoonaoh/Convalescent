package com.mystudio.gamename.desktop;

import com.mystudio.gamename.windows.Main;
import org.mini2Dx.desktop.DesktopMini2DxConfig;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Main.GAME_IDENTIFIER);
		cfg.title = "Main";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.vSyncEnabled = true;
		new DesktopMini2DxGame(new Main(), cfg);
	}
}