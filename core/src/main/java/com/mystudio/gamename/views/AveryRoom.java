package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class AveryRoom extends View {

    public AveryRoom(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_room.png"));
        //floorspace = new Polygon();
        includesAvery = false;

        MiniGame tileGame = new TilePuzzleGame(mainAdapter);
        MinigameTrigger frame = new MinigameTrigger("items/frame.png", new CollisionBox(300, 100, 100, 100), tileGame, mainAdapter);
        actors.addActor(frame);

        // Add tile puzzle
        //MiniGame tilePuzzle = new TilePuzzle();
        //MinigameTrigger pictureFrame = new MinigameTrigger("", new CollisionBox(), tilePuzzle, mainAdapter);
        //actors.addActor(pictureFrame);

        // Add door to hallway

    }
}
