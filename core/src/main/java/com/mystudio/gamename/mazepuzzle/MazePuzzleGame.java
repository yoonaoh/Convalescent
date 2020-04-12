//package com.mystudio.gamename.mazepuzzle;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.mystudio.gamename.items.Item;
//import org.mini2Dx.core.engine.geom.CollisionBox;
//import org.mini2Dx.core.game.BasicGame;
//import org.mini2Dx.core.graphics.Graphics;
//
//import java.util.ArrayList;
//
//public class MazePuzzleGame extends BasicGame {
//
//    /**
//     * For identification
//     */
//    public static final String GAME_IDENTIFIER = "org.mini2Dx.mazepuzzle";
//    private Stage stage;
//    private ShapeRenderer shapeRenderer;
//    private SpriteBatch batch;
//    Camera camera;
//    private int numBugs;
//    private ArrayList<Wall> walls;
//    private Gecko gecko;
//    private boolean success;
//    private Item background;
//    private Item bug;
//    private Item goalSq;
//
//
//    @Override
//    public void initialise() {
//        batch = new SpriteBatch();
//        shapeRenderer = new ShapeRenderer();
//
//        // Set screen size
//        camera = new OrthographicCamera();
//        camera.position.set(640, 360, 0);
//        stage = new Stage(new FitViewport(1280, 720, camera), batch);
//        Gdx.input.setInputProcessor(stage);
//
//        // Set up the actors
//        // Background
//        background = new Item("mazepuzzle/maze_background.jpg", new CollisionBox(0, 0, 1280, 720));
//        background.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                moveGecko(x, y);
//            }
//
//        });
//        stage.addActor(background);
//
//        // Bug
//        bug = new Item("mazepuzzle/bug.png", new CollisionBox(0, 80 * 8, 80, 80));
//        stage.addActor(bug);
//
//        // Godzilla
//        gecko = new Gecko("mazepuzzle/gecko.png", new CollisionBox(80 * 15, 80 * 6, 80, 80));
//        gecko.textureRegion.setFlip(true, false);
//        stage.addActor(gecko);
//
//        // Walls
//        walls = new ArrayList<Wall>() {
//            {
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 0, 80 * 7, 80 * 16, 80 * 1))); // 1
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 1, 80 * 4, 80 * 3, 80 * 2))); // 2
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 0, 80 * 1, 80 * 4, 80 * 2)));
//
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 5, 80 * 2, 80 * 2, 80 * 4)));
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 7, 80 * 4, 80 * 3, 80 * 2)));
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 5, 80 * 0, 80 * 3, 80 * 2)));
//
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 8, 80 * 0, 80 * 3, 80 * 3)));
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 11, 80 * 1, 80 * 4, 80 * 2)));
//                add(new Wall("mazepuzzle/wall.jpg", new CollisionBox(80 * 11, 80 * 4, 80 * 4, 80 * 3)));
//            }
//        };
//        for (Wall wall : walls) {
//            stage.addActor(wall);
//        }
//
//        // Reset button
//        Texture resetTexture = new Texture(Gdx.files.internal("mazepuzzle/reset.png"));
//        Drawable drawable = new TextureRegionDrawable(new TextureRegion(resetTexture));
//        ImageButton resetBtn = new ImageButton(drawable);
//        resetBtn.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                reset();
//            }
//        });
//        resetBtn.setBounds(80 * 15, 80 * 8, 80, 80);
//        stage.addActor(resetBtn);
//
//        // Bug count
//        numBugs = 6;
//        Texture bugTexture = new Texture(Gdx.files.internal("mazepuzzle/bug.png"));
//
//        // Goal square
//        goalSq = new Item("mazepuzzle/wall.png", new CollisionBox(0, 0, 80, 80));
//        goalSq.addListener(new ClickListener() {
//
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                moveGecko(x, y);
//                success = true;
//            }
//
//        });
//        stage.addActor(goalSq);
//
//        // Set up success boolean
//        success = false;
//    }
//
//
//    public void reset() {
//        // Put gecko back at the starting point
//        gecko.setBounds(80 * 15, 80 * 6, 80, 80);
//        // Reset the numBugs count
//        numBugs = 6;
//        System.out.println("Reset the maze");
//    }
//
//    @Override
//    public void update(float delta) {
//
//        stage.act(delta);
//
//        if (isSuccess()) {
//            System.out.println("Congrats you won!");
//            System.exit(0);
//        }
//
//    }
//
//    public void moveGecko(float x, float y) {
//
//        // Tile-based movement
//        int newX = ((int) x / 80) * 80;
//        int newY = ((int) y / 80) * 80;
//
//        // The gecko has to move either left-right or up-down
//        if (newX != gecko.getX() && newY == gecko.getY()) {
//            gecko.getCollisionShape().moveTowards(new CollisionBox(newX, newY, 80, 80), 10);
//            gecko.setBounds(newX, newY, gecko.getWidth(), gecko.getHeight());
//        } else if (newX == gecko.getX() && newY != gecko.getY()) {
//            gecko.setBounds(newX, newY, gecko.getWidth(), gecko.getHeight());
//        }
//    }
//
//    @Override
//    public void interpolate(float alpha) {
//    }
//
//    @Override
//    public void render(Graphics g) {
//        stage.draw();
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//}
