package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BorderCrossingLimiter {

    private final int screenWidth;
    private final int screenHeight;

    public BorderCrossingLimiter() {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    public void restrictCrossBorder(Vector2 position) {

        if (position.x < 0) {
            position.x = screenWidth;
        }

        else if (position.x > screenWidth) {
            position.x = 0;
        }

        if (position.y < 0) {
            position.y = screenHeight;
        }

        else if (position.y > screenHeight) {
            position.y = 0;
        }
    }
}
