package com.mystudio.gamename.matchpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mystudio.gamename.utils.MainAdapter;
import com.mystudio.gamename.windows.MiniGame;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class MatchPuzzle extends MiniGame {
    MainAdapter mainAdapter;
    Camera camera;

    public BitmapFont font24;

    Skin skin;
    private float progress;

    // Info label
    private Label labelInfo;

    private Tile[][] layer1;
    private Tile[][] layer2;
    private Tile[][] layer3;

    private int current_i = -1;
    private int current_j = -1;
    private Tile[][] current_layer = null;

    public MatchPuzzle(MainAdapter mainAdapter) {
        super("gearpuzzle/bunny_background.png", mainAdapter);
        this.mainAdapter = mainAdapter;

        camera = mainAdapter.getViewPort().getCamera();
        font24 = mainAdapter.getManager().getFont();
        this.progress = 0f;
        this.skin = mainAdapter.getManager().getSkin();

        init_layers();
        init_tiles();
    }

    private void init_tiles() {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                add_tile(i, j, layer1);
            }
        }
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                add_tile(i, j, layer2);
            }
        }
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                add_tile(i, j, layer3);
            }
        }
    }

    private void add_tile(final int i, final int j, final Tile[][] layer) {
        if (layer[i][j] != null) {
            layer[i][j].setPosition(120 + (70 * j),
                        (float) (500 - (80 * i) + (40 * (i-3))));
            layer[i][j].setSize(100, 100);
            layer[i][j].addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(current_i == -1 && current_j == -1 && current_layer == null) {
                        current_i = i;
                        current_j = j;
                        current_layer = layer;
                    } else {
                        if (current_layer[current_i][current_j].compare(layer[i][j])) {
                            layer[i][j].remove();
                            layer[i][j] = null;
                            current_layer[current_i][current_j].remove();
                            current_layer[current_i][current_j] = null;
                            current_layer = null;
                            current_j = -1;
                            current_i = -1;
                        } else {
                            current_layer = null;
                            current_j = -1;
                            current_i = -1;
                        }
                    }
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                };
            });
            addActor(layer[i][j]);
        }
    }

    private void init_layers() {
        layer1 = new Tile[7][6];
        layer2 = new Tile[7][6];
        layer3 = new Tile[7][6];

        for(int i = 0; i < 7 ; i++) {
            for(int j = 0; j < 6 ; j++) {
                layer1[i][j] = null;
                layer2[i][j] = null;
                layer3[i][j] = null;
            }
        }

        layer1[0][4] = new Tile(3, tile_texture(3), skin, tile_down_texture(3));
        layer1[2][1] = new Tile(3, tile_texture(3), skin, tile_down_texture(3));
        layer1[2][4] = new Tile(2, tile_texture(2), skin, tile_down_texture(2));
        layer1[3][0] = new Tile(1, tile_texture(1), skin, tile_down_texture(1));
        layer1[3][2] = new Tile(0, tile_texture(0), skin, tile_down_texture(0));
        layer1[3][3] = new Tile(1, tile_texture(1), skin, tile_down_texture(1));
        layer1[3][5] = new Tile(3, tile_texture(3), skin, tile_down_texture(3));
        layer1[4][1] = new Tile(2, tile_texture(2), skin, tile_down_texture(2));
        layer1[4][4] = new Tile(4, tile_texture(4), skin, tile_down_texture(4));
        layer1[6][4] = new Tile(2, tile_texture(2), skin, tile_down_texture(2));

        layer2[3][0] = new Tile(0, tile_texture(0), skin, tile_down_texture(0));
        layer2[3][1] = new Tile(1, tile_texture(1), skin, tile_down_texture(1));
        layer2[3][2] = new Tile(2, tile_texture(2), skin, tile_down_texture(2));
        layer2[3][3] = new Tile(4, tile_texture(4), skin, tile_down_texture(4));
        layer2[3][4] = new Tile(0, tile_texture(0), skin, tile_down_texture(0));
        layer2[3][5] = new Tile(4, tile_texture(4), skin, tile_down_texture(4));
        layer2[1][4] = new Tile(1, tile_texture(1), skin, tile_down_texture(1));
        layer2[5][4] = new Tile(3, tile_texture(3), skin, tile_down_texture(3));

        layer3[3][1] = new Tile(0, tile_texture(0), skin, tile_down_texture(0));
        layer3[3][5] = new Tile(4, tile_texture(4), skin, tile_down_texture(4));


    }

    private boolean check_free() {
        return false;
    }

    private boolean check_match() {
        return false;
    }

    // Initialize the info label
    private void initInfoLabel() {
        labelInfo = new Label("Welcome! Click any tile to begin!", skin, "default");
        labelInfo.setPosition(200, 50);
        labelInfo.setAlignment(Align.center);
        labelInfo.addAction(sequence(alpha(0f), delay(.5f), fadeIn(.5f)));
        addActor(labelInfo);
    }

    private boolean solutionFound() {
        return getActions().size == 0;
    }

    private TextureRegionDrawable tile_texture(int id) {
        Texture texture = null;
        switch (id) {
            case 0:
                texture = mainAdapter.getManager().getTexture("matchpuzzle/Bamboo-2.png");
                break;
            case 1:
                texture = mainAdapter.getManager().getTexture("matchpuzzle/Bamboo-3.png");
                break;
            case 2:
                texture = mainAdapter.getManager().getTexture("matchpuzzle/Bamboo-4.png");
                break;
            case 3:
                texture = mainAdapter.getManager().getTexture("matchpuzzle/Bamboo-5.png");
                break;
            case 4:
                texture = mainAdapter.getManager().getTexture("matchpuzzle/Bamboo-6.png");
                break;
            default:
                break;
        }
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    private TextureRegionDrawable tile_down_texture(int id) {
//        TextureRegionDrawable ret = tile_texture(id);
        TextureRegionDrawable ret = new TextureRegionDrawable(new TextureRegion(mainAdapter.getManager().getTexture("matchpuzzle/TileSelected.png")));
        ret.tint(Color.CYAN);
        return ret;
    }


}
