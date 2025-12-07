package ru.itschool.sundayghost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static final float SCREEN_WIDTH = 1280, SCREEN_HEIGHT = 720;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    Texture image;
    Sound[] sound = new Sound[15];
    Ghost[] ghost = new Ghost[33];

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        touch = new Vector3();
        image = new Texture("ghost.png");
        for (int i = 0; i < sound.length; i++) {
            sound[i] = Gdx.audio.newSound(Gdx.files.internal("snd/man_death_"+i/10+i%10+".ogg"));
        }

        for(int i = 0; i < ghost.length; i++) {
            ghost[i] = new Ghost();
        }
    }

    @Override
    public void render() {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            for (int i = 0; i < ghost.length; i++) {
                if(ghost[i].isLive && ghost[i].hit(touch.x, touch.y)){
                    sound[MathUtils.random(0, 14)].play();
                }
            }
        }

        // игровые события
        for (int i = 0; i < ghost.length; i++) {
            ghost[i].move();
        }

        // отрисовка
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < ghost.length; i++) {
            if(ghost[i].isLive) {
                batch.draw(image, ghost[i].x, ghost[i].y, ghost[i].width, ghost[i].height);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
