package ru.itschool.sundayghost;

import static ru.itschool.sundayghost.Main.*;

import com.badlogic.gdx.math.MathUtils;

public class Ghost {
    float x, y;
    float width, height;
    private float stepX, stepY;
    boolean isLive = true;

    public Ghost() {
        width = MathUtils.random(50f, 200f);
        height = width*1.5f;
        x = SCREEN_WIDTH /2-width/2;
        y = SCREEN_HEIGHT /2-height/2;
        stepX = MathUtils.random(-10f, 10f);
        stepY = MathUtils.random(-10f, 10f);
    }

    void move() {
        x += stepX;
        y += stepY;
        outOfBounds2();
    }

    void outOfBounds1() {
        if (x > SCREEN_WIDTH - width || x < 0) {
            stepX = -stepX;
        }
        if (y > SCREEN_HEIGHT - height || y < 0) {
            stepY = -stepY;
        }
    }

    void outOfBounds2(){
        if (x > SCREEN_WIDTH) {
            x = -width;
        }
        if (x < -width) {
            x = SCREEN_WIDTH;
        }
        if (y > SCREEN_HEIGHT) {
            y = -height;
        }
        if (y < -height) {
            y = SCREEN_HEIGHT;
        }
    }

    boolean hit(float tx, float ty){
        if(x < tx && tx < x+width && y < ty && ty < y+height){
            isLive = false;
            return true;
        }
        return false;
    }
}
