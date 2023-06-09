package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Starhip.ShotGun;
import com.mygdx.game.Starhip.StarShip;
import com.mygdx.game.Starhip.StarshipInputProcessor;
import com.mygdx.game.asteroid.AsteroidsSpawner;
import com.mygdx.game.projectile.ActiveProjectileController;
import com.mygdx.game.projectile.ProjectilePool;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private StarShip starShip;
    private AsteroidsSpawner asteroidsSpawner;
    private CollisionHandler collisionHandler;
    private Background background;
    private Score score;
    private ActiveProjectileController activeProjectileController;
    private ProjectilePool projectilePool;

    @Override
    public void create() {
        batch = new SpriteBatch();
        score = new Score();
        projectilePool = new ProjectilePool();
        activeProjectileController = new ActiveProjectileController(projectilePool);
        ShotGun shotGun = new ShotGun(projectilePool, activeProjectileController);
        starShip = new StarShip(shotGun, score);
        background = new Background();
        asteroidsSpawner = new AsteroidsSpawner();
        collisionHandler = new CollisionHandler(starShip, asteroidsSpawner, score, projectilePool, activeProjectileController);

        Gdx.input.setInputProcessor(new StarshipInputProcessor(starShip.getRotateListener()));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        background.render();
        score.render(batch);
        batch.begin();
        activeProjectileController.render(batch);
        asteroidsSpawner.render(batch);
        starShip.render(batch);
        batch.end();
        collisionHandler.checkCollisions();
    }

    @Override
    public void dispose() {
        starShip.dispose();
        batch.dispose();
        projectilePool.dispose();
        background.dispose();
        asteroidsSpawner.dispose();
        Gdx.input.setInputProcessor(null);
    }
}