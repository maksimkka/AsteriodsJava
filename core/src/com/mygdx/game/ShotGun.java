package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class ShotGun {
    private final Array<Projectile> projectilesPool;
    private final Array<Projectile> activeProjectiles;
    //private final Array<Polygon> polygons;

    public ShotGun() {
        projectilesPool = new Array<>();
        activeProjectiles = new Array<>();
        //polygons = new Array<>();

        for (int i = 0; i < 10; i++) {
            projectilesPool.add(new Projectile());
        }
    }

//    public Array<Polygon> getPolygons() {
//        return polygons;
//    }

    public Array<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }
    public void render(SpriteBatch batch) {
        for (Projectile projectile : activeProjectiles) {
            projectile.getProjectile().draw(batch);
        }
    }

    public void renderShapeRenderer() {
        for (Projectile projectile : activeProjectiles) {
            projectile.renderShapeRenderer();
        }
    }

    public void shoot(float x, float y, float playerShipRotation) {
        Projectile projectile = projectilesPool.size > 0 ? projectilesPool.removeIndex(0) : new Projectile();
        System.out.println("PIY" + x + " " + y);
        projectile.setPosition(x, y, playerShipRotation);

        activeProjectiles.add(projectile);
        //polygons.add(projectile.getPolygon());
    }

    public void update(float deltaTime) {
        for (int i = activeProjectiles.size - 1; i >= 0; i--) {
            Projectile projectile = activeProjectiles.get(i);

            projectile.update(deltaTime, 100);

            if (projectile.collidesWithEnemy() || projectile.isOutOfBounds()) {
                activeProjectiles.removeIndex(i);

                projectilesPool.add(projectile);
            }
        }
    }
}
