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

import com.badlogic.gdx.utils.viewport.*;
import com.hbp.probdef.ProbDef;

public class MetaScreen implements Screen {
	
	final ProbDef game;
	OrthographicCamera camera;
	
	boolean ANDROID;
	
	private Sound hellosound;
	
	private ScreenViewport viewport;
	
	public Texture poncho_t;
	
	public MetaScreen(final ProbDef gam, boolean play_the_sound, boolean is_android_on) {
		
		ANDROID=is_android_on;
		game=gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		viewport=new ScreenViewport(camera);
		
		hellosound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/341250__jeremysykes__select01.wav"));
		if (play_the_sound){
			hellosound.play();
		}
		poncho_t = new Texture(Gdx.files.internal("blackbar_poncho.png"));
	}
	
	public void meta_render() {
		
		//if (!ANDROID){Gdx.graphics.setWindowedMode(320, 480);}
		
		camera.update();
		
		
	}
	
	@Override
	public void render(float delta){
		meta_render();
	}

	@Override
	public void resize(int width, int height) {
		float scale=0f;
		if (width>=160 && height>=240){
			scale=0.5f;
		}
		if (width>=320 && height>=480){
			scale=1f;
		}
		while (width>=(320*(scale+1)) && height>=(480*(scale+1))){
			scale+=1f;
		}
		System.out.println("Target scale is: "+ scale);
		//System.out.println("width: "+ width);
		//System.out.println("height: "+ height);
		camera.setToOrtho(false, (float)width/(float)scale, (float)height/(float)scale);
		camera.translate(-((float)width/(float)scale-320)/2f, -((float)height/(float)scale-480)/2f);
		//camera.update();
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
	
	public void meta_dispose(){
		hellosound.dispose();
	}
	@Override
	public void dispose() {
		meta_dispose();
	}
}