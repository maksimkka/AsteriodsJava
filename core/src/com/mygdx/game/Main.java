package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private StarShip starShip;
    private ShotGun shotGun;
    private AsteroidsSpawner asteroidsSpawner;
    private CollisionHandler collisionHandler;
    private StarshipInputProcessor inputProcessor;
    private Background background;
    private boolean isButtonPressed;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        shotGun = new ShotGun();
        batch = new SpriteBatch();
        starShip = new StarShip();
        background = new Background();
        isButtonPressed = false;
        asteroidsSpawner = new AsteroidsSpawner();

        collisionHandler = new CollisionHandler(starShip, asteroidsSpawner);

        inputProcessor = new StarshipInputProcessor(starShip);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        background.render();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        asteroidsSpawner.renderAsteroids(batch);
        starShip.render(batch);
        shotGun.render(batch);
        batch.end();

        asteroidsSpawner.renderShapeRendererAsteroids();
        starShip.renderShapeRenderer();
        shotGun.renderShapeRenderer();

        asteroidsSpawner.moveAsteroids();
        asteroidsSpawner.restrictCrossBorderAsteroids();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            starShip.moveForward(Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            starShip.moveSideLeft(Gdx.graphics.getDeltaTime());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            starShip.moveSideRight(Gdx.graphics.getDeltaTime());
        }

        boolean isButtonCurrentlyPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);

        if (!isButtonCurrentlyPressed) {
            isButtonPressed = false;
        }

        if (isButtonCurrentlyPressed && !isButtonPressed) {
            isButtonPressed = true;
            shotGun.shoot(starShip.getPosition().x, starShip.getPosition().y, starShip.getRotation());
        }

        shotGun.update(Gdx.graphics.getDeltaTime());


        starShip.restrictCrossBorder();
        collisionHandler.checkCollisions();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}