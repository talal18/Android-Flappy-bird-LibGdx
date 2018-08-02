package com.talal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.talal.game.states.GameStateManager;
import com.talal.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {

	public static final int WIDTH = 480; // width of the screen
	public static final int HEIGHT = 800; // height of the screen
	public static final String TITLE = "Flappy Bird"; // title of the screen
	private GameStateManager gsm;
	private SpriteBatch batch; // it's better to have one SpriteBatch in a game because it's a very heavy file
	private Music music; // Music runs from the disk
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")); // choose the music file
		music.setLooping(true); // to repeat the music
		music.setVolume(0.1f); // the music volume is 10%
		music.play(); // play the music

		Gdx.gl.glClearColor(1, 0, 0, 1); // the screen color when cleared.
		gsm.push(new MenuState(gsm)); // pushes the Menu state
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen and draw everything fresh (created automatically)
		gsm.update(Gdx.graphics.getDeltaTime()); // give us the difference between the render times
		gsm.render(batch);

	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose(); // to dispose the music when we don't need it.
	}
}
