package ru.itschool.sundayghost;

import static ru.itschool.sundayghost.Main.SCREEN_HEIGHT;
import static ru.itschool.sundayghost.Main.SCREEN_WIDTH;

import com.badlogic.gdx.math.MathUtils;

public class WhiteGhost extends Ghost{
    public WhiteGhost(){
        setWidth(MathUtils.random(50f, 100f));
        setHeight(getWidth()*1.5f);
        x = SCREEN_WIDTH /2-getWidth()/2;
        y = SCREEN_HEIGHT /2-getHeight()/2;
        stepX = MathUtils.random(-5f, 5f);
        stepY = MathUtils.random(-5f, 5f);
    }

    @Override
    void move() {
        x += stepX;
        y += stepY;
        outOfBounds1();
    }
}
