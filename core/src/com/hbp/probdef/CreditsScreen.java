package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.utils.Array;

public class CreditsScreen extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	private Texture creds_t;
	
	private Texture TITLE_t;
	
	private Texture dull_trim_t;
	
	Rectangle menu_r;
	Texture menu_t;
	
	private SpriteBatch batch;
	
	public CreditsScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(option_music_volume);
		bgm.play();
				
		TITLE_t=new Texture(Gdx.files.internal("TITLE_CREDITS.png"));
		
		creds_t=new Texture(Gdx.files.internal("creds3.png"));
		
		menu_r = new Rectangle();
		menu_r.x=170;
		menu_r.y=400;
		menu_r.height=60;
		menu_r.width=140;
		menu_t = new Texture(Gdx.files.internal("abutton_menu.png"));
		
		
		dull_trim_t=new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
		
		game = gam;
		
		batch= new SpriteBatch();
		
		
	}
	
	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
	    batch.draw(TITLE_t, 0,400);
	    
		
	    
	    batch.draw(menu_t, menu_r.x, menu_r.y);
	    if (menu_r.contains(tp_x,tp_y)){
			batch.draw(dull_trim_t, menu_r.x, menu_r.y);
		}
	    
		batch.draw(creds_t, 10, 10);
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {			
			if (menu_r.contains(tp_x,tp_y)){
				game.setScreen(new TitleScreen(game, true));
	            dispose();
			}
		}
		
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
		bgm.stop();
		bgm.dispose();
		batch.dispose();
	}
}