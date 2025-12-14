package ru.itschool.sundayghost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

    Texture imgGhost;
    Texture imgDementor;
    Texture imgBackGround;
    Sound[] sound = new Sound[15];
    Music music;

    Ghost[] ghost = new Ghost[25];
    NecroObject moon, tomb;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        touch = new Vector3();

        imgGhost = new Texture("ghost.png");
        imgDementor = new Texture("dementor.png");
        imgBackGround = new Texture("necropolis.png");
        for (int i = 0; i < sound.length; i++) {
            sound[i] = Gdx.audio.newSound(Gdx.files.internal("snd/man_death_"+i/10+i%10+".ogg"));
        }
        music = Gdx.audio.newMusic(Gdx.files.internal("snd/potter.mp3"));

        for(int i = 0; i < ghost.length; i++) {
            if(i%2 == 0) {
                ghost[i] = new WhiteGhost();
            } else {
                ghost[i] = new Dementor();
            }
        }

        moon = new NecroObject(650, 620, 25);
        tomb = new NecroObject(45, 110, 175, 310);
    }

    @Override
    public void render() {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println("x="+touch.x+" y="+touch.y);
            if(moon.isTouched(touch.x, touch.y)) {
                music.play();
            }
            if(tomb.isTouched(touch.x, touch.y)) {
                music.stop();
            }
            for (int i = 0; i < ghost.length; i++) {
                if(ghost[i].isLive && ghost[i].hit(touch.x, touch.y)){
                    sound[MathUtils.random(0, 14)].play();
                }
            }
        }

        // игровые события
        for (Ghost d: ghost) {
            d.move();
        }

        // отрисовка
        //ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int i = 0; i < ghost.length; i++) {
            if(ghost[i].isLive) {
                if(i%2 == 0) {
                    batch.draw(imgGhost, ghost[i].x, ghost[i].y, ghost[i].getWidth(), ghost[i].getHeight());
                }
                else {
                    batch.draw(imgDementor, ghost[i].x, ghost[i].y, ghost[i].getWidth(), ghost[i].getHeight());
                }
            }
        }
        /*for (Dementor d: dementor) {
            if(d.isLive){
                batch.draw(imgDementor, d.x, d.y, d.width, d.height);
            }
        }*/
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgGhost.dispose();
        imgDementor.dispose();
        imgBackGround.dispose();
        for (Sound s:sound) {
            s.dispose();
        }
        music.dispose();
    }
}
