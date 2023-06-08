package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private StarShip starShip;
    private AsteroidsSpawner asteroidsSpawner;
    private CollisionHandler collisionHandler;
    private Background background;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        ShotGun shotGun = new ShotGun();
        starShip = new StarShip(shotGun);
        background = new Background();

        asteroidsSpawner = new AsteroidsSpawner();

        collisionHandler = new CollisionHandler(starShip, asteroidsSpawner, shotGun);

        Gdx.input.setInputProcessor(new StarshipInputProcessor(starShip.getRotateListener()));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        background.render();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        asteroidsSpawner.render(batch);
        starShip.render(batch);
        batch.end();
        collisionHandler.checkCollisions();
    }

    @Override
    public void dispose() {
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }
}