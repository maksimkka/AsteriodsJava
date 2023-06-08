package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

public class StarShip {
    private final Sprite sprite = new Sprite(new Texture("PNG/playerShip1_green.png"));
    private Polygon starShipCollider = new Polygon();
    private final BorderCrossingLimiter borderCrossingLimiter = new BorderCrossingLimiter();
    private static final float speed = 100f;
    private final Vector2 position = new Vector2();
    private final Vector2 originPosition = new Vector2();
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final ShotGun shotGun;
    private int currentHP;

    private final StarshipRotateListener rotateListener  = new StarshipRotateListener() {
        @Override
        public void rotate(Vector2 position) {
            rotateTowards(position);
        }
    };

    public StarShip(ShotGun shotGun) {
        this.shotGun = shotGun;
        sprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        position.set((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        sprite.setOriginCenter();
        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        currentHP = 3;
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
        renderShapeRenderer();
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

        shotGun.render(batch, this);
    }

    public void renderShapeRenderer() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        //Rectangle rectangle = starShipCollider;
        //Polygon rectangle = starShipCollider;
        shapeRenderer.polygon(starShipCollider.getTransformedVertices());

        //shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end();
    }

    public float getRotation() {
        return sprite.getRotation();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void handleCollision(AsteroidsSpawner spawner) {
        currentHP--;
        if (currentHP <= 0) {
            reset();
            spawner.spawnAsteroids();
        }
    }

    private void rotateTowards(Vector2 mousePosition) {
        //System.out.println((int)position.x + " " + screenX + " " + (int) position.y + " " + screenY);
        mousePosition.set(mousePosition.x, Gdx.graphics.getHeight() - mousePosition.y);
        float angle = mousePosition.sub(position).angleDeg() - 90;
        sprite.setRotation(angle);
    }

    private void initCollider() {
        starShipCollider = new Polygon(new float[]{0,0,sprite.getWidth(),0, sprite.getWidth(), sprite.getHeight(), 0, sprite.getHeight()});
        starShipCollider.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
//        starShipCollider.x = starShip.getX();
//        starShipCollider.y = starShip.getY();
//        starShipCollider.height = starShip.getHeight();
//        starShipCollider.width = starShip.getWidth();
    }

    private void moveForward(float deltaTime) {
        float rotation = sprite.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = MathUtils.cos(radAngle) * speed * deltaTime;
        float deltaY = MathUtils.sin(radAngle) * speed * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private void moveSideLeft(float deltaTime) {
        float rotation = sprite.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = -MathUtils.sin(radAngle) * speed * deltaTime;
        float deltaY = MathUtils.cos(radAngle) * speed * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        sprite.setOriginBasedPosition(position.x, position.y);
        originPosition.set(sprite.getX(), sprite.getOriginY());
        starShipCollider.setPosition(sprite.getX(), sprite.getY());
    }

    private void moveSideRight(float deltaTime) {
        float rotation = sprite.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = MathUtils.sin(radAngle) * speed * deltaTime;
        float deltaY = -MathUtils.cos(radAngle) * speed * deltaTime;
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
        currentHP = 3;
    }
}