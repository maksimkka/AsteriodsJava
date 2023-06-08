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
import com.badlogic.gdx.math.Rectangle;

public class StarShip {
    private final Sprite starShip;
    //private final Rectangle starShipCollider;
    private Polygon starShipCollider;

    private final BorderCrossingLimiter borderCrossingLimiter;
    private static final float speed = 100f;
    private final Vector2 position;
    private final Vector2 originPosition;
    private final ShapeRenderer shapeRenderer;
    private int currentHP;

    public StarShip() {
        Texture texture = new Texture("PNG/playerShip1_green.png");
        starShip = new Sprite(texture);
        shapeRenderer = new ShapeRenderer();
        position = new Vector2();
        originPosition = new Vector2();
        //starShipCollider = new Rectangle();
        //starShipCollider = new Polygon();
        borderCrossingLimiter = new BorderCrossingLimiter();

        starShip.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        position.set((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        starShip.setOriginCenter();
        starShip.setOriginBasedPosition(position.x, position.y);
        originPosition.set(starShip.getX(), starShip.getOriginY());
        currentHP = 3;
        initCollider();
    }

    public void render(SpriteBatch batch) {
        starShip.draw(batch);
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

    public void rotateTowards(Vector2 mousePosition) {
        //System.out.println((int)position.x + " " + screenX + " " + (int) position.y + " " + screenY);
        mousePosition.set(mousePosition.x, Gdx.graphics.getHeight() - mousePosition.y);
        float angle = mousePosition.sub(position).angleDeg() - 90;
        starShip.setRotation(angle);
    }

    public float getRotation() {
        return starShip.getRotation();
    }

    public Vector2 getPosition() {
        return position;
    }

    private void initCollider() {
        starShipCollider = new Polygon(new float[]{0,0,starShip.getWidth(),0, starShip.getWidth(), starShip.getHeight(), 0, starShip.getHeight()});
        starShipCollider.setOrigin(starShip.getWidth() / 2, starShip.getHeight() / 2);
//        starShipCollider.x = starShip.getX();
//        starShipCollider.y = starShip.getY();
//        starShipCollider.height = starShip.getHeight();
//        starShipCollider.width = starShip.getWidth();
    }

    public void moveForward(float deltaTime) {
        float rotation = starShip.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = MathUtils.cos(radAngle) * speed * deltaTime;
        float deltaY = MathUtils.sin(radAngle) * speed * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        starShip.setOriginBasedPosition(position.x, position.y);
        originPosition.set(starShip.getX(), starShip.getOriginY());
        starShipCollider.setPosition(starShip.getX(), starShip.getY());
    }

    public void moveSideLeft(float deltaTime) {
        float rotation = starShip.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = -MathUtils.sin(radAngle) * speed * deltaTime;
        float deltaY = MathUtils.cos(radAngle) * speed * deltaTime;

        position.x += deltaX;
        position.y += deltaY;

        starShip.setOriginBasedPosition(position.x, position.y);
        originPosition.set(starShip.getX(), starShip.getOriginY());
        starShipCollider.setPosition(starShip.getX(), starShip.getY());
    }

    public void moveSideRight(float deltaTime) {
        float rotation = starShip.getRotation() + 90;
        float radAngle = MathUtils.degreesToRadians * rotation;
        float deltaX = MathUtils.sin(radAngle) * speed * deltaTime;
        float deltaY = -MathUtils.cos(radAngle) * speed * deltaTime;
        position.x += deltaX;
        position.y += deltaY;

        starShip.setOriginBasedPosition(position.x, position.y);
        originPosition.set(starShip.getX(), starShip.getOriginY());
        starShipCollider.setPosition(starShip.getX(), starShip.getY());
    }

    public void restrictCrossBorder() {
        borderCrossingLimiter.restrictCrossBorder(position);
    }

    public void handleCollision(AsteroidsSpawner spawner){
        currentHP--;
        if(currentHP <= 0) {
            reset();
            spawner.spawnAsteroids();
        }
    }

    private void reset() {
        starShip.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        position.set((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        starShip.setOriginCenter();
        starShip.setOriginBasedPosition(position.x, position.y);
        initCollider();
        currentHP = 3;
    }

    public Polygon getRectangle() {
        return starShipCollider;
    }
}