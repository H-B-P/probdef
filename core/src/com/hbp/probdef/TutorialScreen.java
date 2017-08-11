package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.*;
import com.hbp.probdef.ProbDef;

public class TutorialScreen extends SpaceyScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	
	
	
	
	private SpriteBatch batch;
	
	public TutorialScreen(final ProbDef gam, boolean play_the_sound, boolean is_android_on) {
		
		super(gam, play_the_sound, is_android_on);
		game=gam;
		
		batch= new SpriteBatch();
	}
	
	
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		spacey_render(delta);
		
		batch.begin();
		
		
		
		batch.draw(statusbar_t, 0, 400);
		
		batch.end();
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		spacey_dispose();
	}
}