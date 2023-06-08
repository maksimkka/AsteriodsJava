package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Asteroid {
    private final Sprite asteroid;
    private final BorderCrossingLimiter borderCrossingLimiter;
   // private Rectangle asteroidCollider;
    private Polygon asteroidCollider;
    private static final float speed = 100f;
    private final Vector2 position;
    private final ShapeRenderer shapeRenderer;
    private Vector2 direction;
    private final int screenWidth;
    private final int screenHeight;

    public Asteroid () {
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        String[] fileNames = {"meteorBrown_med3.png", "meteorBrown_small1.png", "meteorGrey_med2.png", "meteorGrey_small2.png"};
        int randomIndex = new Random().nextInt(fileNames.length);
        String filePath = "PNG/Meteors/"+fileNames[randomIndex];
        Texture texture = new Texture(filePath);
        borderCrossingLimiter = new BorderCrossingLimiter();
        asteroid = new Sprite(texture);
        shapeRenderer = new ShapeRenderer();
        position = new Vector2();
        initCollider();
        setStartPosition();
        setRandomDirection();
    }

    public void render(SpriteBatch batch) {
        asteroid.draw(batch);
    }

    public void renderShapeRenderer() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.polygon(asteroidCollider.getTransformedVertices());
        //Rectangle rectangle = asteroidCollider;
        //shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
    }

    public void update(float deltaTime) {
        position.x += direction.x * speed * deltaTime;
        position.y += direction.y * speed * deltaTime;

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

    private void initCollider() {
        asteroidCollider = new Polygon(new float[]{0,0,asteroid.getWidth(),0, asteroid.getWidth(), asteroid.getHeight(), 0, asteroid.getHeight()});
        asteroidCollider.setOrigin(asteroid.getWidth() / 2, asteroid.getHeight() / 2);
//        asteroidCollider = new Rectangle();
//        asteroidCollider.height = asteroid.getHeight();
//        asteroidCollider.width = asteroid.getWidth();
    }

    private void setStartPosition() {
        float randomX = new Random().nextInt(0, screenWidth);
        float randomY = new Random().nextInt(0, screenHeight);
        position.set(randomX, randomY);
    }

    public void restrictCrossBorder() {
        borderCrossingLimiter.restrictCrossBorder(position);
    }

    public Polygon getRectangle() {
        return asteroidCollider;
    }
}
