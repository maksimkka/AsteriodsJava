package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class StarshipInputProcessor implements InputProcessor {
    private StarshipMovementListener listener;
    private final StarShip starship;
    private final Vector2 mousePosition;

    public StarshipInputProcessor(StarShip starship) {
        this.starship = starship;
        mousePosition = new Vector2();
    }

//    public StarshipInputProcessor(StarshipMovementListener listener) {
//        this.listener = listener;
//    }

    //    @Override
//    public boolean keyDown (int keycode) {
//        return false;
//    }
    @Override
    public boolean keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.W:
//                starship.accelerate();
//                break;
//            case Input.Keys.A:
//                starship.moveSideLeft();
//                break;
//            case Input.Keys.D:
//                starship.moveSideRight();
//                break;
//        }
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
//        switch (keycode) {
//            case Input.Keys.W:
//                starship.decelerate();
//                break;
//            case Input.Keys.A:
//                if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
//                    starship.stopRotation();
//                }
//                break;
//            case Input.Keys.D:
//                if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
//                    starship.stopRotation();
//                }
//                break;
//        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mousePosition.set(screenX, screenY);
        starship.rotateTowards(mousePosition);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
