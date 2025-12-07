package ru.itschool.sundayghost;

import static ru.itschool.sundayghost.Main.*;

public abstract class Ghost {
    float x, y;
    float width, height;
    float stepX, stepY;
    boolean isLive = true;

    abstract void move();

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
