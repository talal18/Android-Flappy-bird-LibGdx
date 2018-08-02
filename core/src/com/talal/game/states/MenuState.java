package com.talal.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.talal.game.FlappyDemo;

/**
 * Created by Talal on 2017-05-09.
 */

public class MenuState extends State {

    private Texture background;
    private Texture startBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2); //We copied this from the PlayState class. setToOrtho is responsible for deciding which part of the game world the player see.
        background = new Texture("backgr.png");
        startBtn = new Texture("play2.png");

    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()){  // if the user touched or clicked the on the screen
            gsm.set(new PlayState(gsm));  // return the play state
          // we removed the dispose from here because we already disposed the menu from the pop() method in the GameStateManager class.
        }
    }

    @Override
    public void update(float delta) {

        handleInput(); // to check the player input
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

      // sb.draw(background, 0 , 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT); // draw the background with postion 0,0 which means top left.
        sb.draw(background, 0,0); // we did this instead of the previous line for Android ( the phone will decide tha background size.

      // sb.draw(startBtn, (FlappyDemo.WIDTH / 2) - (startBtn.getWidth() / 2), FlappyDemo.HEIGHT / 2);// draw the start button with position: -Width background divided by 2 - Hieght background divided by 2.
        sb.draw(startBtn, cam.position.x - startBtn.getWidth() / 2, cam.position.y );// we did this instead of the previous line for Android

        sb.end(); // to close the sb

    }

    @Override
    public void dispose() {

        background.dispose();
        startBtn.dispose();
    }
}
