package com.yojplex.cardswipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yojplex.cardswipe.screens.GameScreen;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
	private SpriteBatch batch;
	public static float masterScale;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		masterScale=(float)Gdx.graphics.getWidth()/1440f;

		gameScreen=new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose(){
		batch.dispose();
		gameScreen.dispose();
	}
}
