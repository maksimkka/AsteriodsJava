package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class StarshipInputProcessor implements InputProcessor {
    private final StarshipRotateListener listener;
    private final Vector2 mousePosition = new Vector2();

    public StarshipInputProcessor(StarshipRotateListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        listener.rotate(mousePosition.set(screenX, screenY));
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
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
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
