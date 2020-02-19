//package com.mystudio.gamename;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.scenes.scene2d.*;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.mystudio.gamename.gearpuzzlegame.GearPuzzleGame;
//import com.mystudio.gamename.items.InventoryItem;
//import com.mystudio.gamename.items.Item;
//import com.mystudio.gamename.items.TriggerItem;
//import com.mystudio.gamename.views.View;
//import org.mini2Dx.core.engine.geom.CollisionBox;
//import org.mini2Dx.core.engine.geom.CollisionCircle;
//import org.mini2Dx.core.game.BasicGame;
//import org.mini2Dx.core.graphics.Graphics;
//
//import java.util.ArrayList;
//
///**
// * Main game class
// */
//public class Convalescent extends BasicGame {
//
//    /**
//     * For identification
//     */
//    public static final String GAME_IDENTIFIER = "org.mini2Dx.convalescent";
//
//    /**
//     * Current game state
//     */
//    private GameState state = GameState.DARK_ATTIC;
//
//    /**
//     * Orthographic camera for perspective
//     */
//    private Camera camera;
//
//    /**
//     * Stage for the game
//     */
//    private Stage stage;
//
//    /**
//     * SpriteBatch containing all the sprites
//     */
//    private SpriteBatch batch;
//
//    private ShapeRenderer shapeRenderer;
//
//    /**
//     * Current view, only one at a time
//     */
//    private View view;
//
//    /**
//     * Avery sprite
//     */
//    private Avery avery;
//
//    Actor actor;
//
//    @Override
//    public void initialise() {
//
//        batch = new SpriteBatch();
//        shapeRenderer = new ShapeRenderer();
//
//        // Set screen size
//        camera = new OrthographicCamera();
//        camera.position.set(640, 360, 0);
//        stage = new Stage(new FitViewport(1280, 720, camera), batch);
//
//        // Background and persistent sprites.
//        view = new View(stage, state);
//        view.update(state);
//        avery = new Avery();
//
//
//        stage.addListener(new ClickListener(){
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("Stage Event!!!!");
//                if (view.isAvery()) {
//                    avery.update(x, y, new ArrayList<Actor>(), view.getFloorspace());
//                }
//                return true;
//            }
//        });
//
//
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void update(float delta) {
//        camera.update();
//        state = view.getGameState();
//        avery.move(new ArrayList<Item>(), view.getFloorspace());
//    }
//
//    @Override
//    public void interpolate(float alpha) {
//
//    }
//
//    @Override
//    public void render(Graphics g) {
//        float delta = Gdx.graphics.getDeltaTime();
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act(delta);
//        stage.draw();
//
//        batch.setProjectionMatrix(camera.combined);
//        shapeRenderer.setProjectionMatrix(camera.combined);
//
//        batch.begin();
//        view.render(batch);
//        if (view.isAvery()) {
//            avery.render(batch, state);
//        }
//        batch.end();
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.CYAN);
//        shapeRenderer.rect(1035, 250, 150, 270);
//
//        shapeRenderer.rect(840, 380, 150, 160);
//
//        shapeRenderer.rect(0, 0, 250, 720);
//        shapeRenderer.end();
//    }
//
//    public void dispose () {
//        stage.dispose();
//    }
//
//    /**
//     * Resizes the viewport to the given width and height
//     *
//     * @param width  - desired width
//     * @param height - desired height
//     */
//    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
//    }
//
//}
