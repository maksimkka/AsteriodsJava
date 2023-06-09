package com.mygdx.game.Starhip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.projectile.ActiveProjectileController;
import com.mygdx.game.projectile.Projectile;
import com.mygdx.game.projectile.ProjectilePool;

public class ShotGun {
    private final ProjectilePool projectilePool;
    private final ActiveProjectileController activeProjectileController;
    private boolean isButtonPressed = false;

    public ShotGun(ProjectilePool projectilePool, ActiveProjectileController activeProjectileController) {
        this.projectilePool = projectilePool;
        this.activeProjectileController = activeProjectileController;
    }

    public void update(StarShip starShip) {
        boolean isButtonCurrentlyPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);

        if (!isButtonCurrentlyPressed) {
            isButtonPressed = false;
        }

        if (isButtonCurrentlyPressed && !isButtonPressed) {
            isButtonPressed = true;
            shoot(starShip.getPosition().x, starShip.getPosition().y, starShip.getRotation());
        }
    }

    private void shoot(float x, float y, float playerShipRotation) {
        Projectile projectile = projectilePool.getPoolObject();
        projectile.setPosition(x, y, playerShipRotation);
        activeProjectileController.addActiveProjectile(projectile);
    }
}
