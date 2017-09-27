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
	
	
	public GameScreen_Bayes(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
	}
	
	//---level_specific_yadda_yadda()---
	
	void level_specific_timeline(){
		   
	   }
	
	void level_specific_events(){
		if (seconds==2){
			   //spawn_enemy_ship(-2, "pentagon");
			   //spawn_enemy_ship(0, "triangle");
			   //spawn_enemy_ship(2, "triangle");
			
			spawn_enemy_ship(-1, "square");
			spawn_enemy_ship(1, "circle");
			
			
		   }
	}
	
	EnemyShip pick_a_ship(){
		for(EnemyShip enemyship: enemyships) {
		   if (enemyship.rect.contains(tp_x, tp_y) && tp_y<440){
			   return enemyship;
		   }
		}
		return null;
	}
	
	//---Spawning---
	
	void spawn_enemy_ship(int xposn, String turret_id){
		EnemyShip enemyship=new EnemyShip(xposn, turret_id);
		enemyships.add(enemyship);
		turrets.add(enemyship.turret);
		turrets_standard.add((Turret_Standard)enemyship.turret);
		
		enemyship.turret.rect.x=enemyship.rect.x+10;
		enemyship.turret.rect.y=enemyship.rect.y+10;
	}
	
	void spawnMine(int xposn, EnemyShip enemyship) {
		   
		   Mine mine = new Mine(xposn, 20);
		   mines.add(mine);
		         
	   }
	
	//---Overrides---
	
	@Override
	
	void draw_turrets_standard(){
		   for(Turret_Standard turret_standard: turrets_standard) {
				if (turret_standard.does_it_work){
					//batch.draw(turret_standard.current_t, turret_standard.rect.x, turret_standard.rect.y);
					batch.draw(turret_standard.current_t, turret_standard.rect.x, turret_standard.rect.y, 40, 40, 0, 0, 40, 40, false, true);
				}
		   }
	   }
	
	@Override
	
	void level_specific_HUD(){
		   font.draw(batch, "WAVE: "+"?/?", 90, 464, 140, 1, true);
		   font.draw(batch, "MINES: "+minecount, 90, 446, 140, 1, true);
		   font.draw(batch, "SHIELDS: "+ shields, 90, 428, 140, 1, true);
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
		
		
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		gamey_render_draw_objects();
		
		//batch.draw(enemyship_t, 20, 300);
		
		//batch.draw(enemyship_t, 120, 300);
		
		//batch.draw(enemyship_t, 220, 300);
		
		gamey_render_draw_interface();
		
		batch.end();
		
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