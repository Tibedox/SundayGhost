package ru.itschool.sundayghost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static float screenWidth = 1280, screenHeight = 720;
    private SpriteBatch batch;
    private Texture image;
    Ghost[] ghost = new Ghost[33];

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("ghost.png");
        for(int i = 0; i < ghost.length; i++) {
            ghost[i] = new Ghost();
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < ghost.length; i++) {
            ghost[i].move();
        }
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        for (int i = 0; i < ghost.length; i++) {
            batch.draw(image, ghost[i].x, ghost[i].y, ghost[i].width, ghost[i].height);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
