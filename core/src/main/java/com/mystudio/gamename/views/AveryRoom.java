//package com.mystudio.gamename.views;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.mystudio.gamename.items.MinigameTrigger;
//import com.mystudio.gamename.items.SceneTrigger;
//import com.mystudio.gamename.tilepuzzle.TilePuzzleGame;
//import com.mystudio.gamename.utils.GameState;
//import com.mystudio.gamename.utils.MainAdapter;
//import com.mystudio.gamename.windows.MiniGame;
//import org.mini2Dx.core.engine.geom.CollisionBox;
//import org.mini2Dx.core.geom.Polygon;
//
//public class AveryRoom extends View {
//
//    public AveryRoom(MainAdapter mainAdapter) {
//        super(mainAdapter);
//        background = new Texture(Gdx.files.internal("views/avery_bedroom_sketch.png"));
//        floorspace = new Polygon(new float[] {
//            0,0,
//            46,37,
//            289,34,
//            371,70,
//            634,172,
//            634,192,
//            956,192,
//            956,114,
//            1102,0
//        });
//        includesAvery = true;
//        includesInventory = true;
//
//        // Add tile puzzle frame trigger
//        MiniGame tileGame = new TilePuzzleGame(mainAdapter);
//        MinigameTrigger frame = new MinigameTrigger("items/frame3.png", new CollisionBox(260, 400, 114, 96), tileGame, mainAdapter);
//        actors.addActor(frame);
//
//        // Add door to hallway
//        SceneTrigger door = new SceneTrigger(null, new CollisionBox(672, 190, 172, 336), GameState.CORRIDOR, mainAdapter);
//        actors.addActor(door);
//    }
//}
