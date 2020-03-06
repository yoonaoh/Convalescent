package com.mystudio.gamename.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mystudio.gamename.items.Item;
import com.mystudio.gamename.utils.MainAdapter;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * Abstract class for all MiniGames
 */
public abstract class MiniGame extends Window {

    private MainAdapter mainAdapter;
    public boolean success = false;

    public MiniGame(String image, final MainAdapter mainAdapter) {
        super("", new Window.WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new TextureRegion(new Texture(image)))));

        this.mainAdapter = mainAdapter;
        setPosition(200, 100);
        setSize(800, 500);

        Actor close = new Item("gearpuzzle/close.png", new CollisionBox(30, 450, 30, 30));
        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                mainAdapter.closeWindow();
                return true;
            }
        });
        addActor(close);
    }

    public MainAdapter getMainAdapter() {
        return mainAdapter;
    }
}
// Set to close when clicked outside of window area
//        setModal(true);
//        addListener(new InputListener() {
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
////                if (x < 0 || x > getWidth() || y < 0 || y > getHeight()){
////                    mainAdapter.closeWindow();
////                    return true;
////                }
//                return false;
//            }
//        });


    //        addListener(new InputListener() {
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                System.out.println("clicked!");
//                return true;
//            }
//        });
//    /**
//     * Boolean to determine whether MiniGame has started
//     */
//    private boolean started = false;
//
//    public boolean success = false;
//
//    /**
//     * Opens the game in current state
//     */
//    public void start() {
//        started = true;
//    }
//
//    /**
//     * Closes the game if incomplete
//     */
//    public void end() {
//        started = false;
//    }
//
//    /**
//     * Checks if game is started
//     */
//    public boolean hasStarted() {
//        return started;
//    }
//
//
//    /**
//     * Used as a response to a mouse click.
//     */
//    protected abstract void update(float delta); // (x,y) is where cursor click occurs
//    /**
//     * Used for things that need to be updated with time.
//     */
//    protected abstract void interpolate(float alpha);
//    /**
//     * Used to actually do the drawing on screen.
//     */
//    protected abstract void render(SpriteBatch batch); // Use either but not both! Preferably use batch.
//    /**
//     * Used to dispose objects used to reduce time.
//     */
//    protected abstract void dispose();

