package com.mygdx.game;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Starhip.StarShip;
import com.mygdx.game.asteroid.Asteroid;
import com.mygdx.game.asteroid.AsteroidsSpawner;
import com.mygdx.game.projectile.ActiveProjectileController;
import com.mygdx.game.projectile.Projectile;
import com.mygdx.game.projectile.ProjectilePool;

import java.util.ArrayList;

public class CollisionHandler {
    private final StarShip starShip;
    private final ProjectilePool projectilePool;
    private final ActiveProjectileController activeProjectileController;
    private final AsteroidsSpawner asteroidsSpawner;
    private final Score score;

    public CollisionHandler(StarShip player, AsteroidsSpawner asteroidsSpawner, Score score,
                            ProjectilePool projectilePool, ActiveProjectileController activeProjectileController) {
        this.projectilePool = projectilePool;
        this.activeProjectileController = activeProjectileController;
        this.starShip = player;
        this.asteroidsSpawner = asteroidsSpawner;
        this.score = score;
    }

    public void checkCollisions() {
        Polygon playerBounds = starShip.getRectangle();
        ArrayList<Asteroid> asteroids = asteroidsSpawner.getAsteroids();
        Array<Projectile> projectiles = activeProjectileController.getActiveProjectiles();

        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Polygon asteroidBounds = asteroids.get(i).getRectangle();

            if (Intersector.overlapConvexPolygons(playerBounds, asteroidBounds)) {
                asteroids.get(i).dispose();
                asteroids.remove(i);
                asteroidsSpawner.spawnNewAsteroid();
                starShip.handleCollision(asteroidsSpawner, activeProjectileController);

            }

            for (int j = projectiles.size - 1; j >= 0; j--) {
                Polygon projectilePolygon = projectiles.get(j).getPolygon();
                if(Intersector.overlapConvexPolygons(projectilePolygon, asteroidBounds)) {
                    asteroids.remove(i);
                    projectilePool.returnToPool(projectiles.get(j));
                    projectiles.removeIndex(j);
                    asteroidsSpawner.spawnNewAsteroid();
                    score.increaseScore();
                }
            }
        }
    }
}
