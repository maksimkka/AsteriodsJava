package com.mygdx.game.Starhip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.projectile.ActiveProjectileController;
import com.mygdx.game.asteroid.AsteroidsSpawner;
import com.mygdx.game.BorderCrossingLimiter;
import com.mygdx.game.Score;

public class StarShip {
    private static final float SPEED = 100f;
    private static final float TURN_ANGLE_RADIUS = 90f;
    private static final int MAX_HP = 3;
    private final Texture texture = new Texture("PNG/playerShip1_green.png");
    private final Sprite sprite = new Sprite(texture);
    private final Polygon starShipCollider = new Polygon(new float[]{
            0,
            0,
            sprite.getWidth(),
            0,
            sprite.getWidth(),
            sprite.getHeight(),
            0,
            sprite.getHeight()
    });
    private final BorderCrossingLimiter borderCrossingLimiter = new BorderCrossingLimiter();
    private final Vector2 position = new Vector2();
    private final Vector2 originPosition = new Vector2();
    private final ShotGun shotGun;
    private final Score score;
    private int currentHP = MAX_HP;

    private final StarshipRotateListener rotateListener = this::rotateTowards;

    public StarShip(ShotGun shotGun, Score score) {
        this.shotGun = shotGun;
        this.score = score;
        float halfWidth = (float) Gdx.graphics.getWidth() / 2;
        float halfHeight = (float) Gdx.graphics.getHeight() / 2;
        sprite.setPosition(halfWidth, halfHeight);
        position.set(halfWidth, halfHeight);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        initCollider();
    }

    public Polygon getRectangle() {
        return starShipCollider;
    }

    public StarshipRotateListener getRotateListener() {
        return rotateListener;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            moveForward(Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            moveSideLeft(Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            moveSideRight(Gdx.graphics.getDeltaTime());
        }

        restrictCrossBorder();

        shotGun.update( this);
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void handleCollision(AsteroidsSpawner spawner, ActiveProjectileController activeProjectileController) {
        currentHP--;
        if (currentHP <= 0) {
            activeProjectileController.resetActiveProjectiles();
            reset();
            spawner.spawnAsteroids();
        }
    }

    public void dispose() {
        texture.dispose();
    }

    private void rotateTowards(Vector2 mousePosition) {
        mousePosition.set(mousePosition.x, Gdx.graphics.getHeight() - mousePosition.y);
        float angle = mousePosition.sub(position).angleDeg() - TURN_ANGLE_RADIUS;
        sprite.setRotation(angle);
    }

    private void initCollider() {
        starShipCollider.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private void moveForward(float deltaTime) {
        float radAngle = MathUtils.degreesToRadians * getRotationAngle();
        float deltaX = MathUtils.cos(radAngle) * SPEED * deltaTime;
        float deltaY = MathUtils.sin(radAngle) * SPEED * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private void moveSideLeft(float deltaTime) {
        float radAngle = MathUtils.degreesToRadians * getRotationAngle();
        float deltaX = -MathUtils.sin(radAngle) * SPEED * deltaTime;
        float deltaY = MathUtils.cos(radAngle) * SPEED * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private float getRotationAngle() {
        return sprite.getRotation() + TURN_ANGLE_RADIUS;
    }

    private void moveSideRight(float deltaTime) {
        float radAngle = MathUtils.degreesToRadians * getRotationAngle();
        float deltaX = MathUtils.sin(radAngle) * SPEED * deltaTime;
        float deltaY = -MathUtils.cos(radAngle) * SPEED * deltaTime;
        position.x += deltaX;
        position.y += deltaY;

        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private void restrictCrossBorder() {
        borderCrossingLimiter.restrictCrossBorder(position);
    }

    private void reset() {
        sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        position.set((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(position.x, position.y);
        initCollider();
        currentHP = MAX_HP;
        score.resetScore();
    }
}