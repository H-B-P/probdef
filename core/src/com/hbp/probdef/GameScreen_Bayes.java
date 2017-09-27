package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This is the root of gameplay that's based on standard turrets (i.e. not Bayes). Unmodified, it doubles as the tutorial.
 */

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.hbp.probdef.RT_Kaboom;
import com.hbp.probdef.Mine;
import com.hbp.probdef.Turret;
import com.badlogic.gdx.audio.Sound;


import com.hbp.probdef.ProbDef;

public class GameScreen_Bayes extends GameScreen {
	
	final ProbDef game;
	
	SpriteBatch batch;
	
	
	public GameScreen_Bayes(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
		batch=new SpriteBatch();
		
	}
	
	//---level_specific_yadda_yadda()---
	
	void level_specific_timeline(){
		   if (seconds==2){
			   spawn_enemy_ship(0, "triangle");
		   }
	   }
	
	void level_specific_events(){
		
	}
	
	//---Spawning---
	
	void spawn_enemy_ship(int xposn, String turret_id){
		EnemyShip eship=new EnemyShip(xposn, turret_id);
		enemyships.add(eship);
		turrets.add(eship.turret);
		turrets_standard.add((Turret_Standard)eship.turret);
		eship.turret.rect.x=eship.rect.x+10;
		eship.turret.rect.y=eship.rect.y+5;
	}
	
	//---Useful functions---
	
	
	
	
	//---Do things---
	
	void do_firing_things(){
		
	}
	
	void do_targeting_things(){
		
	}
	
	void do_zapping_things(){
		
	}
	
	//---The render---
	
	public void render(float delta){
		gamey_render_predraw(delta);
		
		gamey_render_draw_objects();
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		//batch.draw(enemyship_t, 20, 300);
		
		//batch.draw(enemyship_t, 120, 300);
		
		//batch.draw(enemyship_t, 220, 300);
		
		batch.end();
		
		gamey_render_draw_interface();
		
		check_for_dot_mineshield_collisions();
		
		check_for_dot_mine_collisions();
		
		//check_for_mine_enemyshipshield_collisions();
		
		//check_for_dot_shipshield_collisions();
		
		gamey_render_postdraw();
		
		if(Gdx.input.justTouched()){
			if (menu_button_r.contains(tp_x, tp_y)){
				game.setScreen(new TitleScreen(game, true));
				  dispose();
			}
		}
	}
	
}