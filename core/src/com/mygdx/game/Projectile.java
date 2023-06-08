package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public class Projectile {

    private Polygon polygon;
    private final Sprite projectile = new Sprite(new Texture("PNG/Lasers/laserBlue01.png"));
    private float directionX;
    private float directionY;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    public Projectile() {
        initCollider();
    }

    public Sprite getProjectile() {
        return projectile;
    }

    public Polygon getPolygon() {
        return polygon;
    }

//    public Array<Polygon> getPolygons() {
//        return polygons;
//    }

    public void setPosition(float x, float y, float rotation) {
        projectile.setPosition(x, y);
        projectile.setRotation(rotation);
        polygon.setRotation(rotation);
        directionX = MathUtils.cosDeg(rotation + 90);
        directionY = MathUtils.sinDeg(rotation + 90);
    }

    public void renderShapeRenderer() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.polygon(polygon.getTransformedVertices());
        shapeRenderer.end();
    }

    public void update(float deltaTime, float speed) {
        projectile.translate(directionX * speed * deltaTime, directionY * speed * deltaTime);
        polygon.setPosition(projectile.getX(), projectile.getY());
    }

    public boolean collidesWithEnemy() {
        return false;
        //  тут будет проверка на столкновение с астероидом
    }

    public boolean isOutOfBounds() {
        return false;
        // тут будет проверка находится ли снаряд за границами экрана и если да, он будет умирать
    }

    private void initCollider() {
        polygon = new Polygon(new float[]{0,0,projectile.getWidth(),0, projectile.getWidth(), projectile.getHeight(), 0, projectile.getHeight()});
        polygon.setOrigin(projectile.getWidth() / 2, projectile.getHeight() / 2);
    }
}
