package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class CollisionHandler {

    private final StarShip starShip;
    private final AsteroidsSpawner asteroidsSpawner;
    public CollisionHandler(StarShip player, AsteroidsSpawner asteroidsSpawner) {
        this.starShip = player;
        this.asteroidsSpawner = asteroidsSpawner;
    }

    public void checkCollisions() {
        Rectangle playerBounds = starShip.getRectangle();
        ArrayList<Asteroid> asteroids = asteroidsSpawner.getAsteroids();
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Rectangle asteroidBounds = asteroids.get(i).getRectangle();

            if (playerBounds.overlaps(asteroidBounds)) {

                //System.out.println("STOLKNULIS");
                asteroids.remove(i);
                asteroidsSpawner.spawnNewAsteroid();
                starShip.handleCollision(asteroidsSpawner);
            }
        }
    }
}
