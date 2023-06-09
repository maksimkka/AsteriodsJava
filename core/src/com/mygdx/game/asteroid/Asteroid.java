package com.mygdx.game.asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.BorderCrossingLimiter;

import java.util.Random;

public class Asteroid {
    private static final float SPEED = 100f;
    private final Texture texture = createTexture();
    private final Sprite asteroid = new Sprite(texture);
    private final BorderCrossingLimiter borderCrossingLimiter = new BorderCrossingLimiter();
    private final Polygon asteroidCollider = new Polygon(new float[]{
            0,
            0,
            asteroid.getWidth(),
            0,
            asteroid.getWidth(),
            asteroid.getHeight(),
            0,
            asteroid.getHeight()
    });

    private final Vector2 position = new Vector2();
    private Vector2 direction;
    private final int screenWidth = Gdx.graphics.getWidth();
    ;
    private final int screenHeight = Gdx.graphics.getHeight();

    public Asteroid() {
        initCollider();
        setStartPosition();
        setRandomDirection();
    }

    public void render(SpriteBatch batch) {
        asteroid.draw(batch);
    }

    public void update(float deltaTime) {
        position.x += direction.x * SPEED * deltaTime;
        position.y += direction.y * SPEED * deltaTime;

        asteroid.setOriginBasedPosition(position.x, position.y);
        asteroidCollider.setPosition(asteroid.getX(), asteroid.getY());
    }

    public void setRandomDirection() {
        direction = new Vector2();
        Random random = new Random();
        direction.x = random.nextFloat() * 2 - 1;
        direction.y = random.nextFloat() * 2 - 1;
        direction.nor();
    }

    public void restrictCrossBorder() {
        borderCrossingLimiter.restrictCrossBorder(position);
    }

    public Polygon getRectangle() {
        return asteroidCollider;
    }

    public void dispose() {
        texture.dispose();
    }

    private void initCollider() {
        asteroidCollider.setOrigin(asteroid.getWidth() / 2, asteroid.getHeight() / 2);
    }

    private void setStartPosition() {
        int randomX = new Random().nextInt(screenWidth);
        int randomY = new Random().nextInt(screenHeight);
        position.set(randomX, randomY);
    }

    private Texture createTexture() {
        String[] fileNames = {"meteorBrown_med3.png", "meteorBrown_small1.png", "meteorGrey_med2.png", "meteorGrey_small2.png"};
        int randomIndex = new Random().nextInt(fileNames.length);
        String filePath = "PNG/Meteors/" + fileNames[randomIndex];
        return new Texture(filePath);
    }
}