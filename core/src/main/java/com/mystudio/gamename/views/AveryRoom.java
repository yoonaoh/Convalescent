package com.mystudio.gamename.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mystudio.gamename.items.MinigameTrigger;
import com.mystudio.gamename.mazepuzzle.MazePuzzleGame;
import com.mystudio.gamename.tilepuzzle.TilePuzzle;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.geom.Polygon;

public class AveryRoom extends View {

    public AveryRoom(MainAdapter mainAdapter) {
        super(mainAdapter);
        background = new Texture(Gdx.files.internal("views/avery_room.png"));
        //floorspace = new Polygon();
        includesAvery = true;

        // Add tile puzzle
        //MiniGame tilePuzzle = new TilePuzzle();
        //MinigameTrigger pictureFrame = new MinigameTrigger("", new CollisionBox(), tilePuzzle, mainAdapter);
        //actors.addActor(pictureFrame);

        // Add door to hallway

    }
}
