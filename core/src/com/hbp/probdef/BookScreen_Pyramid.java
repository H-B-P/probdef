package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Pyramid extends GenericBookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_Pyramid(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		maxpages=4;
		
		batch=new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("pentagon");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			if (seconds==2){
				spawnShieldMine(-2,95,1);
				spawnShieldMine(0,95,2);
				spawnShieldMine(2,95,3);
			}
			if (seconds==4){
				spawnMine(0,95);
			}
			if (seconds==6){
				spawnMine(0,95);
			}
		}
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   greentext=false;
		
		if (page==1){
			
			if (page_time>10){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			if (page_time>10){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==4){
			if (page_time>6){
				time_to_move_on=true;
			}
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}