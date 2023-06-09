package com.mygdx.game.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public class Projectile {
    private static final float SPEED = 300f;
    private Polygon polygon;
    private final Texture texture = new Texture("PNG/Lasers/laserBlue01.png");
    private final Sprite projectile = new Sprite(texture);
    private float directionX;
    private float directionY;

    public Projectile() {
        initCollider();
    }

    public Sprite getProjectile() {
        return projectile;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPosition(float x, float y, float rotation) {
        projectile.setPosition(x, y);
        projectile.setRotation(rotation);
        polygon.setRotation(rotation);
        directionX = MathUtils.cosDeg(rotation + 90);
        directionY = MathUtils.sinDeg(rotation + 90);
    }

    public void update(float deltaTime) {
        projectile.translate(directionX * SPEED * deltaTime, directionY * SPEED * deltaTime);
        polygon.setPosition(projectile.getX(), projectile.getY());
    }

    public boolean isOutOfBounds() {
        if (projectile.getX() < 0 || projectile.getX() > Gdx.graphics.getWidth()) {
            return true;
        }

        return projectile.getY() < 0 || projectile.getY() > Gdx.graphics.getHeight();
    }

    public void dispose() {
        texture.dispose();
    }

    private void initCollider() {
        polygon = new Polygon(new float[]{0, 0, projectile.getWidth(), 0, projectile.getWidth(), projectile.getHeight(), 0, projectile.getHeight()});
        polygon.setOrigin(projectile.getWidth() / 2, projectile.getHeight() / 2);
    }
}
