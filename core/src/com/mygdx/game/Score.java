package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
    private final BitmapFont FONT = new BitmapFont();
    private int score;

    public Score() {
        FONT.setColor(Color.WHITE);
        FONT.getData().setScale(2);
    }

    public void increaseScore() {
        int increaseValue = 10;
        score += increaseValue;
    }

    public void resetScore() {
        score = 0;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        int posScoreX = 20;
        int posScoreY = 450;
        FONT.draw(batch, "Score: " + score, posScoreX, posScoreY);
        batch.end();
    }
}
