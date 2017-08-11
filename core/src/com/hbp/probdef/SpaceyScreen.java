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

public class SpaceyScreen extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	
	public Texture starry_background_layer_one_t;
	public Texture starry_background_layer_two_t;
	
	public Texture statusbar_t;

	public Texture ship_t;
	public Texture shield_t;
	public Texture shield_normal_t;
	public Texture shield_flicker_t;
	
	public Rectangle shield_r;
	
	public BitmapFont font;
	
	public float total_time;
	public int seconds;
	public int ship_posn;
	
	public float tp_x;
	public float tp_y;
	
	public int starspeed_one;
	public int starspeed_two;
	
	boolean are_instructions_visible;
	
	
	private SpriteBatch batch;
	
	public SpaceyScreen(final ProbDef gam, boolean play_the_sound, boolean is_android_on) {
		
		super(gam, play_the_sound, is_android_on);
		
		System.out.print(camera);
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Chillectro.mp3"));
		bgm.setLooping(true);
		bgm.play();
		
		game = gam;
		
		batch= new SpriteBatch();
		
		font = new BitmapFont();
		
		total_time=0;
		
		starry_background_layer_one_t=new Texture(Gdx.files.internal("BG1.png"));
	    starry_background_layer_two_t=new Texture(Gdx.files.internal("BG2.png"));
		
	    ship_t=new Texture(Gdx.files.internal("fullship_GREEN.png"));
	    shield_normal_t=new Texture(Gdx.files.internal("shield_GREEN.png"));
	    shield_flicker_t=new Texture(Gdx.files.internal("shield_flicker.png"));
	    shield_t=shield_normal_t;
	    
	    statusbar_t=new Texture(Gdx.files.internal("statusbar.png"));
	    
		ship_posn=-420;
		
		starspeed_one=4;
		starspeed_two=17;
		
		shield_r=new Rectangle();
		shield_r.x=20;
	    shield_r.y = ship_posn+420+75;
	    shield_r.width = 280;
	    shield_r.height = 3;
	}
	
	public void spacey_render(float delta){
		
		meta_render();
		
		total_time+=delta;
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Vector3 scr_vec= new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
		Vector3 irl_vec=camera.unproject(scr_vec);
		tp_x=irl_vec.x;
		tp_y=irl_vec.y;
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
	      
	      batch.draw(starry_background_layer_one_t, 0f, 800-(float)((total_time*starspeed_one)%1600));
	      batch.draw(starry_background_layer_one_t, 0f, 800-(float)((total_time*starspeed_one+800)%1600));
	      batch.draw(starry_background_layer_two_t, 0f, 1100-(float)((total_time*starspeed_two)%2200));
	      batch.draw(starry_background_layer_two_t, 0f, 1100-(float)((total_time*starspeed_two+1100)%2200));
	      batch.draw(ship_t, 0, ship_posn);
	      batch.draw(shield_t, shield_r.x, shield_r.y);
		
		batch.end();
	}
	
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		spacey_render(delta);
		
		batch.begin();
		
		batch.draw(statusbar_t, 0, 400);//This is obscene. HOW THE HELL
		
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
	
	public void spacey_dispose(){
		bgm.stop();
		bgm.dispose();
		
		starry_background_layer_one_t.dispose();
		starry_background_layer_two_t.dispose();
		statusbar_t.dispose();
		ship_t.dispose();
		
		batch.dispose();
	}
}