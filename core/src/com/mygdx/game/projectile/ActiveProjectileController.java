package com.mygdx.game.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ActiveProjectileController {
    private final ProjectilePool projectilePool;
    private final Array<Projectile> activeProjectiles = new Array<>();

    public ActiveProjectileController(ProjectilePool projectilePool) {
        this.projectilePool = projectilePool;
    }

    public void render(SpriteBatch batch) {
        for (Projectile projectile : activeProjectiles) {
            projectile.getProjectile().draw(batch);
        }

        update(Gdx.graphics.getDeltaTime());
    }

    public void resetActiveProjectiles() {
        for (Projectile projectile : activeProjectiles) {
            projectilePool.returnToPool(projectile);
        }

        activeProjectiles.clear();
    }


    public void addActiveProjectile(Projectile projectile) {
        activeProjectiles.add(projectile);
    }

    public Array<Projectile> getActiveProjectiles() {
        return activeProjectiles;
    }

    private void update(float deltaTime) {
        for (int i = activeProjectiles.size - 1; i >= 0; i--) {
            Projectile projectile = activeProjectiles.get(i);

            projectile.update(deltaTime);

            if (projectile.isOutOfBounds()) {
                projectilePool.returnToPool(projectile);
                activeProjectiles.removeIndex(i);
            }
        }
    }
}