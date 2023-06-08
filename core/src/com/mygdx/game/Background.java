package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private final SpriteBatch batch = new SpriteBatch();
    private final Texture background = new Texture(Gdx.files.internal("Backgrounds/blue.png"));
    private final int screenWidth = Gdx.graphics.getWidth();
    private final int screenHeight = Gdx.graphics.getHeight();

    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.end();
    }
}