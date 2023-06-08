package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    private final SpriteBatch batch;
    Texture backgroundTexture;
    private final int screenWidth;
    private final int screenHeight;

    public Background() {
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        backgroundTexture = new Texture(Gdx.files.internal("Backgrounds/blue.png"));
    }

    public void render() {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, screenWidth, screenHeight);
        batch.end();
    }
}