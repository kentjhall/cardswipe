package com.yojplex.cardswipe;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.yojplex.cardswipe.screens.GameScreen;

/**
 * Created by kenthall on 12/12/15.
 */
public class MyGestureListener implements GestureDetector.GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (GameScreen.getCard().getVel().x==0 && GameScreen.getCard().getVel().y==0 && GameScreen.getDummyCard().getVel().x==0) {
            GameScreen.getCard().setVel(new Vector2(velocityX / 75 * MyGdxGame.masterScale, -velocityY / 75 * MyGdxGame.masterScale));
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
