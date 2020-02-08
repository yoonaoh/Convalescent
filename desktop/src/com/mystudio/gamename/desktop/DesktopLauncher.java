package com.mystudio.gamename.desktop;

import com.mystudio.gamename.Drop;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.mystudio.gamename.Convalescent;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Convalescent.GAME_IDENTIFIER);
		cfg.title = "Convalescent";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.vSyncEnabled = true;
		new DesktopMini2DxGame(new Convalescent(), cfg);
	}
}