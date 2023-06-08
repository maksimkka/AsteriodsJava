package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BorderCrossingLimiter {

    private final int screenWidth = Gdx.graphics.getWidth();
    private final int screenHeight = Gdx.graphics.getHeight();

    public void restrictCrossBorder(Vector2 position) {
        restrictX(position);
        restrictY(position);
    }

    private void restrictX(Vector2 position) {
        if (position.x < 0) {
            position.x = screenWidth;
        } else if (position.x > screenWidth) {
            position.x = 0;
        }
    }

    private void restrictY(Vector2 position) {
        if (position.y < 0) {
            position.y = screenHeight;
        } else if (position.y > screenHeight) {
            position.y = 0;
        }
    }
}
