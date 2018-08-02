package com.talal.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Talal on 2017-05-09.
 */

public abstract class State {

    protected OrthographicCamera cam; // camera to locate the place of the game to view
    protected Vector3 mouse;
    protected GameStateManager gsm; // class to manage the state of the game such as pause state, play state , etc..

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();

    public abstract void update(float delta); // it will return the delta time which is the difference between
                                              // one frame rendered and the next frame rendered

    public abstract void render(SpriteBatch sb); // SpriteBatch is the container of everything we need to render
                                                 // in the screen such as graphics, pictures, etc...

    public abstract void dispose(); // dispose usd to rid of the texture after using it to avoid  memry leak.
}
