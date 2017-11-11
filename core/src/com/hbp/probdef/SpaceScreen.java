package com.hbp.probdef;

/*
 * ~SUMMARY~
 * 
 * This is the foundation of every level which involves actual gameplay.
 * 
 * It sets up the looping backgrounds, the ship, the shield, and miscellaneous time-based variables.
 * 
 * I intend to use this wholesale for other SomethingDef games, even non-turnbased ones.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class SpaceScreen extends MetaScreen {
	
	final ProbDef game;
		
	public Texture starry_background_layer_one_t;
	public Texture starry_background_layer_two_t;
	
	public Texture pretty_background_t;
	
	int pretty_background_height;
	
	public Texture statusbar_t;
	
	Texture ship_normal_t;
	Texture ship_invisible_t;
	Texture ship_t;
	
	Texture shipshield_t;
	Texture shipshield_normal_t;
	Texture shipshield_flicker_t;
	
	public Rectangle shield_r;
	
	public BitmapFont font;
	
	public float total_time;
	public int seconds;
	public int ship_posn;
	
	public int starspeed_one;
	public int starspeed_two;
	
	public int pretty_speed;
	
	public double TIMESPEED;
	
	boolean are_instructions_visible;
	
	public String current_status;
	
	private SpriteBatch spacey_batch;
	
	public SpaceScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		
		game = gam;
		
		spacey_batch= new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
		font.setColor(Color.BLACK);
		
		total_time=0;
		
		starry_background_layer_one_t=new Texture(Gdx.files.internal("BG1.png"));
	    starry_background_layer_two_t=new Texture(Gdx.files.internal("BG2.png"));
		
	    pretty_background_t=new Texture(Gdx.files.internal("Pretty_Background.png"));
	    
	    pretty_background_height=pretty_background_t.getHeight();
	    
	    ship_normal_t=new Texture(Gdx.files.internal("fullship_GREEN.png"));
	    ship_invisible_t=new Texture(Gdx.files.internal("fullship_INVISIBLE.png"));
	    ship_t=ship_normal_t;
	    
	    shipshield_normal_t=new Texture(Gdx.files.internal("shield_GREEN.png"));
	    shipshield_flicker_t=new Texture(Gdx.files.internal("shield_flicker.png"));
	    shipshield_t=shipshield_normal_t;
	    
	    statusbar_t=new Texture(Gdx.files.internal("statusbar.png"));
	    
		ship_posn=-390;
		
		starspeed_one=3;
		starspeed_two=8;
		
		pretty_speed=10;
		
		
		
		shield_r=new Rectangle();
		shield_r.x=20;
	    shield_r.y = ship_posn+420+75;
	    shield_r.width = 280;
	    shield_r.height = 3;
	    
	    TIMESPEED=1; //Set time to pass at one second per second.
	    //(i.e. the game isn't paused, slowed, in fast-forward etc.)
	    
	    current_status="waiting";//By default, we're not shooting or targeting, we're just watching the stars go by.
	    //(I promise this isn't some kind of philosophical statement)
	}
	
	public void spacey_render(float delta){
		
		meta_render(); //Call MetaScreen's rendering function before anything.
		
		total_time+=delta*option_gamespeed*TIMESPEED; //Increment time. This is time in-game, not time for the player.
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f); //Make the background at the base of everything black.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Prevent objects from sticking around between frames.
		
		spacey_batch.begin();
		
		spacey_batch.setProjectionMatrix(camera.combined);
		
		if (option_background.equals("Crude")){
	      draw_spacey_background(); //draws starry backgrounds which loop at different speeds to give the illusion of parallax.
		}
		if (option_background.equals("Pretty")){
			draw_pretty_background();
		}
	      spacey_batch.draw(ship_t, 0, ship_posn); //draw the ship!
	      
		spacey_batch.end();
	}
	
	void draw_spacey_background(){
		spacey_batch.draw(starry_background_layer_one_t, 0f, 800-(float)((total_time*starspeed_one)%1600));
	      spacey_batch.draw(starry_background_layer_one_t, 0f, 800-(float)((total_time*starspeed_one+800)%1600));
	      spacey_batch.draw(starry_background_layer_two_t, 0f, 1100-(float)((total_time*starspeed_two)%2200));
	      spacey_batch.draw(starry_background_layer_two_t, 0f, 1100-(float)((total_time*starspeed_two+1100)%2200));
	}
	void draw_pretty_background(){
		spacey_batch.draw(pretty_background_t, 0f, pretty_background_height-(float)((total_time*pretty_speed)%(pretty_background_height*2)));
		spacey_batch.draw(pretty_background_t, 0f, pretty_background_height-(float)((total_time*pretty_speed+pretty_background_height)%(pretty_background_height*2)));
		spacey_batch.draw(pretty_background_t, 0f, pretty_background_height-(float)((total_time*pretty_speed+pretty_background_height*2)%(pretty_background_height*2)));

	}
	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		spacey_render(delta);
		
		spacey_batch.begin();
		spacey_batch.setProjectionMatrix(camera.combined);
		spacey_batch.draw(statusbar_t, 0, 400);
		
		spacey_batch.end();
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
		
		meta_dispose();
		
		starry_background_layer_one_t.dispose();
		starry_background_layer_two_t.dispose();
		statusbar_t.dispose();
		
		ship_t.dispose();
		shipshield_t.dispose();
		shipshield_normal_t.dispose();
		shipshield_flicker_t.dispose();
		
		spacey_batch.dispose();
	}
}