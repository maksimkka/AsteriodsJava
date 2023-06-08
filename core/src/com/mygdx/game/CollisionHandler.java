package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class CollisionHandler {
    private final StarShip starShip;
    private final ShotGun shotGun;
    private final AsteroidsSpawner asteroidsSpawner;
    public CollisionHandler(StarShip player, AsteroidsSpawner asteroidsSpawner, ShotGun shotGun) {
        this.starShip = player;
        this.asteroidsSpawner = asteroidsSpawner;
        this.shotGun = shotGun;
    }

    public void checkCollisions() {
        Polygon playerBounds = starShip.getRectangle();
        ArrayList<Asteroid> asteroids = asteroidsSpawner.getAsteroids();
        Array<Projectile> projectiles = shotGun.getActiveProjectiles();
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Polygon asteroidBounds = asteroids.get(i).getRectangle();

            //if (playerBounds.overlaps(asteroidBounds)) {
            if (Intersector.overlapConvexPolygons(playerBounds, asteroidBounds)) {
                asteroids.remove(i);
                asteroidsSpawner.spawnNewAsteroid();
                starShip.handleCollision(asteroidsSpawner);
            }

            for (int j = projectiles.size - 1; j >= 0; j--) {
                Polygon laserPolygon = projectiles.get(j).getPolygon();
                if(Intersector.overlapConvexPolygons(laserPolygon, asteroidBounds)) {
                    asteroids.remove(i);
                    projectiles.removeIndex(j);
                    asteroidsSpawner.spawnNewAsteroid();
                }
            }
        }
    }
}
