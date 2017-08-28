package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class BookScreen_Combine extends GenericBookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	int page;
	
	double page_start_time;
	
	public BookScreen_Combine(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
	}

	@Override
	public void render(float delta) {
		probgame_render(delta);
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("triangle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		if (page==1){
			
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}