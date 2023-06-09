package com.mygdx.game.projectile;

import com.badlogic.gdx.utils.Array;

public class ProjectilePool {
    private final Array<Projectile> projectilesPool = new Array<>();

    public ProjectilePool() {
        createPool();
    }
    public Projectile getPoolObject() {
        return projectilesPool.size > 0 ? projectilesPool.removeIndex(0) : new Projectile();
    }

    public void returnToPool(Projectile projectile) {
        projectilesPool.add(projectile);
    }

    public void dispose() {
        for (Projectile projectile : projectilesPool) {
            projectile.dispose();
        }
    }

    private void createPool() {
        int poolSize = 20;
        for (int i = 0; i < poolSize; i++) {
            projectilesPool.add(new Projectile());
        }
    }
}
