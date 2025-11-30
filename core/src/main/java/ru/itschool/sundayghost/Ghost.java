package ru.itschool.sundayghost;

import static ru.itschool.sundayghost.Main.*;

import com.badlogic.gdx.math.MathUtils;

public class Ghost {
    float x, y;
    float width, height;
    private float stepX, stepY;

    public Ghost() {
        width = MathUtils.random(50f, 200f);
        height = width*1.5f;
        x = screenWidth/2-width/2;
        y = screenHeight/2-height/2;
        stepX = MathUtils.random(-10f, 10f);
        stepY = MathUtils.random(-10f, 10f);
    }

    void move() {
        x += stepX;
        y += stepY;
        outOfBounds2();
    }

    void outOfBounds() {
        if (x > screenWidth - width || x < 0) {
            stepX = -stepX;
        }
        if (y > screenHeight - height || y < 0) {
            stepY = -stepY;
        }
    }

    void outOfBounds2(){
        if (x > screenWidth) {
            x = -width;
        }
        if (x < -width) {
            x = screenWidth;
        }
        if (y > screenHeight) {
            y = -height;
        }
        if (y < -height) {
            y = screenHeight;
        }
    }
}
