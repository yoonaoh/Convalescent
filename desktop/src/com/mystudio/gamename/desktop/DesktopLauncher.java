package com.mystudio.gamename.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;
import com.mystudio.gamename.windows.Main;
import org.mini2Dx.desktop.DesktopMini2DxConfig;

public class DesktopLauncher {
	public static void main(String[] arg) {
		DesktopMini2DxConfig cfg = new DesktopMini2DxConfig(Main.GAME_IDENTIFIER);
		cfg.addIcon("UI/icon128.png", Files.FileType.Internal);
		cfg.addIcon("UI/icon32.png", Files.FileType.Internal);
		cfg.addIcon("UI/icon16.png", Files.FileType.Internal);
		cfg.forceExit = false;
		cfg.title = "Convalescent";
		cfg.width = 1280;
		cfg.height = 720;
		cfg.vSyncEnabled = true;
		new DesktopMini2DxGame(new Main(false), cfg);
	}
}