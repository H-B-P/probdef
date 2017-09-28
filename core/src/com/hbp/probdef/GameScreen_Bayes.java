package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This is the root of Bayesian gameplay.
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
	
	Vane vane_one;
	Vane vane_two;
	
	Vane currently_active_vane;
	EnemyShip currently_selected_enemyship;
	
	int shipwave;
	int total_shipwaves;
	
	public GameScreen_Bayes(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
		current_status="waiting";
		
		attention_button_trim_t=green_button_trim_t;
		
		shields=20;
		
		vane_setup();
	}
	
	void vane_setup(){
		vane_one=new Vane();
		vane_two=new Vane();
		
		vane_one.current_energy=level_specific_forward_energy_cycle(vane_one.current_energy);
		vane_two.current_energy=level_specific_forward_energy_cycle(vane_two.current_energy);

		
		vane_one.rect.x=55;
		vane_one.rect.y=25;
		vane_two.rect.x=215;
		vane_two.rect.y=25;
		
		vanes.add(vane_one);
		vanes.add(vane_two);
	}
	
	//---level_specific_yadda_yadda()---
	
	String level_specific_forward_energy_cycle(String original){
		if (original.equals("none")){
			return "circle";
		}
		if (original.equals("circle")){
			return "triangle";
		}
		if (original.equals("triangle")){
			return "square";
		}
		if (original.equals("square")){
			return "pentagon";
		}
		if (original.equals("pentagon")){
			return "hexagon";
		}
		if (original.equals("hexagon")){
			return "circle";
		}
		return "circle";
	}
	
	void level_specific_backward_energy_cycle(){
		
	}
	
	void level_specific_timeline(){
		   
	   }
	
	void level_specific_events(){
		if (seconds==2){
			   spawn_enemy_ship(-2, "pentagon");
			   spawn_enemy_ship(0, "triangle");
			   spawn_enemy_ship(2, "triangle");
			
			//spawn_enemy_ship(-1, "square");
			//spawn_enemy_ship(1, "circle");
			
			
		   }
	}
	
	void level_specific_waves(){
		
	}
	
	void level_specific_de_sheild(String dot_type){
		
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
	   
	   boolean should_firing_button_be_lit_up(){
		   if (current_status.equals("targeting") && currently_active_vane==null){
			   return true;
		   }
		   return false;
	   }
	
	@Override
	
	void draw_turrets_standard(){
		   for(Turret_Standard turret_standard: turrets_standard) {
				if (turret_standard.does_it_work){
					batch.draw(turret_standard.current_t, turret_standard.rect.x, turret_standard.rect.y, 40, 40, 0, 0, 40, 40, false, true);
				}
		   }
	   }
	
	@Override
	void draw_turret_overlays(){
		   if (current_status.equals("firing")){
				for(Turret turret: turrets) {
					if (turret.does_it_work){
						batch.draw(turret.overlay_t, turret.rect.x, turret.rect.y, 40, 40, 0, 0, 40, 40, false, true);
						
			       }
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
	
	
	EnemyShip pick_a_ship(){
		for(EnemyShip enemyship: enemyships) {
		   if (enemyship.rect.contains(tp_x, tp_y) && tp_y<440){
			   return enemyship;
		   }
		}
		return null;
	}
	
	boolean any_interesting_ships(){
		   for (EnemyShip enemyship : enemyships){
			   if (enemyship.rect.overlaps(screen_proper)){
				   return true;
			   }
		   }
		   return false;
	   }
	
	void skip_through_vanes(){
		if (currently_active_vane.targeted){
			
			if (currently_active_vane==vanes.peek()){
			   currently_active_vane=null;
			}
			else{
				currently_active_vane=vanes.get(vanes.indexOf(currently_active_vane, true)+1);
				skip_through_vanes();
			}
		}
	}
	
	//---Do things---
	
	void hand_over_to_targeting(){
		currently_active_vane=vanes.first();
		current_status="targeting";
	}
	
	void hand_over_to_zapping(){
		//currently_active_vane=vanes.first();
		current_status="zapping";
		set_up_zapping_times();
	}
	
	void hand_over_to_firing(){
		for (Turret turret: turrets){
			turret.targeted=true;
		}
		set_up_firing_times();
		current_status="firing";
	}
	
	
	void do_firing_things(){
		for(Turret turret: turrets) {
			if (turret.targeted){
			   if (turret.turret_type.equals("standard")){
				   Turret_Standard T=(Turret_Standard)turret;
				   if (T.targeted && (T.firing_time+0.01f*T.shotsmade)<total_time){
					   if (T.shotsmade==0){fire.play(0.3f);}
					   T.shotsmade+=1;
					   RT_Dot dot=new RT_Dot(turret.rect, 160, 0, 3000, turret.determine_output());
					   dots.add(dot);
				   }
				   
				   if (T.shotsmade>=T.turret_level){
					   T.targeted=false;
				   }
				   
			   }
					   
			   
		   }
			if (turret.firing_time<total_time && (turret.firing_time+0.1f)>total_time){
			   turret.current_t=turret.firing_t;
		   }
		   else{
				turret.current_t=turret.normal_t;
		   }
		}
		if (total_time>volley_ending_time){
			current_status="waiting";
			status_effects();
	    }
	}
	
	
	
	void do_targeting_things(){
		if (currently_active_vane!=null){
			if (Gdx.input.justTouched() && pick_a_ship()!=null){
			   currently_active_vane.targeted=true;
			   currently_active_vane.target_ship=pick_a_ship();
			}
			if (Gdx.input.justTouched() && currently_active_vane.rect.contains(tp_x,tp_y)){
				currently_active_vane.current_energy=level_specific_forward_energy_cycle(currently_active_vane.current_energy);
				shock.play(0.4f);
			}
			skip_through_vanes();
		}
	   
	   
	   if (Gdx.input.justTouched()){
			if (fire_button_r.contains(tp_x, tp_y)){
				if (current_status.equals("targeting")){
					hand_over_to_zapping();
				}
			}
			for (Vane vane:vanes){
				if (vane.rect.contains(tp_x, tp_y)){
					vane.targeted=false;
					currently_active_vane=vane;
				}
			}
		}
	}
	
	void do_zapping_things(){
		for(Vane vane: vanes) {
			if (vane.targeted && vane.target_ship!=null){	
				if (vane.firing_time<total_time){
					vane.targeted=false;
					laser.play(0.4f);
					if (vane.current_energy.equals(vane.target_ship.turret.ident)){
					   spawnBigExplosion(vane.target_ship.turret.rect.x, vane.target_ship.turret.rect.y);
					   minesplode.play();
					   enemyships.removeValue(vane.target_ship, true);
					   turrets.removeValue(vane.target_ship.turret, true);
					   vane.target_ship.actually_there=false;
					   if (vane.target_ship.turret.turret_type.equals("standard")){
						   turrets_standard.removeValue((Turret_Standard)vane.target_ship.turret, true);
					   }
					   vane.target_ship.turret.dispose();
					}
					else{
						mistaken.play(0.7f);
					}
				}
			}
			else{
				vane.targeted=false;
			}
		}   
		if (total_time>zappy_ending_time){
			current_status="waiting";
			status_effects();
	    }
	}
	
	@Override
	
	void handle_seconds(){
	   if (seconds<Math.floor(total_time)){
			seconds+=1;
			
			if (seconds%4==0 && any_interesting_ships() && !suppress_freezes){
				hand_over_to_targeting();
			}
			
			if (seconds%4==2 && any_interesting_ships()){
				hand_over_to_firing();
			}
			
			System.out.println(seconds+" s");
			level_specific_events();
		}
	}
	
	//---Draw Interface---
	
	void draw_energy_things(){
		for (Vane vane: vanes){
			if (vane.current_energy.equals("circle")){
				batch.draw(vane_energy_circle_t, vane.rect.x+5, vane.rect.y+5);
			}
			if (vane.current_energy.equals("triangle")){
				batch.draw(vane_energy_triangle_t, vane.rect.x+5, vane.rect.y+5);
			}
			if (vane.current_energy.equals("square")){
				batch.draw(vane_energy_square_t, vane.rect.x+5, vane.rect.y+5);
			}
			if (vane.current_energy.equals("pentagon")){
				batch.draw(vane_energy_pentagon_t, vane.rect.x+5, vane.rect.y+5);
			}
			if (vane.current_energy.equals("hexagon")){
				batch.draw(vane_energy_hexagon_t, vane.rect.x+5, vane.rect.y+5);
			}
		}
	}
	
	void draw_targeting_things(){
		for (Vane vane:vanes){
			if (vane==currently_active_vane){
			   batch.draw(vane_trim_t, vane.rect.x, vane.rect.y);
			}
			if (vane.targeted){
				if (vane.current_energy.equals("circle")){
					batch.draw(vane_crosshairs_circle_t, vane.target_ship.rect.x, vane.target_ship.rect.y);
				}
				if (vane.current_energy.equals("triangle")){
					batch.draw(vane_crosshairs_triangle_t, vane.target_ship.rect.x, vane.target_ship.rect.y);
				}
				if (vane.current_energy.equals("square")){
					batch.draw(vane_crosshairs_square_t, vane.target_ship.rect.x, vane.target_ship.rect.y);
				}
				if (vane.current_energy.equals("pentagon")){
					batch.draw(vane_crosshairs_pentagon_t, vane.target_ship.rect.x, vane.target_ship.rect.y);
				}
				if (vane.current_energy.equals("hexagon")){
					batch.draw(vane_crosshairs_hexagon_t, vane.target_ship.rect.x, vane.target_ship.rect.y);
				}
			}
		}
	}
	//---More collisions---
	
	void check_for_dot_shipshield_collisions(){
		for (RT_Dot dot: dots){
			if (dot.rect.overlaps(shield_r)){
				dots.removeValue(dot, true);
				if (dot.type.equals("destroy")|| dot.type.equals("capture")){
					deshield.play();
					if (dot.type.equals("destroy")){
						shields-=1;
					}
					if (dot.type.equals("capture")){
						shields-=4;
					}
				}
			}
		}
	}
	
	//---The render---
	
	public void render(float delta){
		gamey_render_predraw(delta);
		
		
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		gamey_render_draw_objects();
		
		draw_energy_things();
		
		draw_targeting_things();
		
		gamey_render_draw_interface();
		
		batch.end();
		
		//Do appropriate things!
		
		if (current_status.equals("firing")){
			do_firing_things();
		}
		
		if (current_status.equals("zapping")){
			do_zapping_things();
		}
		
		if (current_status.equals("targeting")){
			//check_for_keypresses();
			do_targeting_things();
		}
		
		//check for collisions!
		
		check_for_dot_mineshield_collisions();
		
		check_for_dot_mine_collisions();
		
		check_for_dot_shipshield_collisions();
		
		//check_for_mine_enemyshipshield_collisions();
		
		gamey_render_postdraw();
		
		if(Gdx.input.justTouched()){
			if (menu_button_r.contains(tp_x, tp_y)){
				game.setScreen(new TitleScreen(game, true));
				  dispose();
			}
		}
	}
	
}