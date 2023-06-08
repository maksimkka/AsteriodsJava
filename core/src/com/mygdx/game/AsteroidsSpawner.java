package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class AsteroidsSpawner {
    private final ArrayList<Asteroid> asteroids;

    public AsteroidsSpawner() {
        asteroids = new ArrayList<>(5);
        spawnAsteroids();
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void render(SpriteBatch batch) {
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch);
        }
        renderShapeRendererAsteroids();
        moveAsteroids();
        restrictCrossBorderAsteroids();
    }

    public void spawnAsteroids() {
        asteroids.clear();
        for (int i = 0; i < 5; i++) {
            asteroids.add(new Asteroid());
        }
    }

    public void spawnNewAsteroid() {
        asteroids.add(new Asteroid());
    }

    private void renderShapeRendererAsteroids() {
        for (Asteroid asteroid : asteroids) {
            asteroid.renderShapeRenderer();
        }
    }

    private void restrictCrossBorderAsteroids() {
        for (Asteroid asteroid : asteroids) {
            asteroid.restrictCrossBorder();
        }
    }

    private void moveAsteroids() {
        for (Asteroid asteroid : asteroids) {
            asteroid.update((Gdx.graphics.getDeltaTime()));
        }
    }
}
