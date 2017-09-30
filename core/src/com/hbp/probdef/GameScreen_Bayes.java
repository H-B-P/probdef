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
import com.badlogic.gdx.math.MathUtils;
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
	int round;
	
	String shiptype_one;
	String shiptype_two;
	String shiptype_three;
	
	int percentfreq_one;
	int percentfreq_two;
	int percentfreq_three;
	
	int assumedfreq_one;
	int assumedfreq_two;
	int assumedfreq_three;
	
	boolean suppress_phasing;
	
	public GameScreen_Bayes(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
		current_status="waiting";
		
		attention_button_trim_t=green_button_trim_t;
		
		shields=20;
		
		total_shipwaves=10;
		
		minecount=20;
		
		vane_setup();
		
		level_specific_environment_setup();
		
		suppress_phasing=false;
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
	
	void display_probabilities(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship.obscured){
				acalc_greenfont.draw(batch, "T: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_two*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
			}
		}
	}
	
	void level_specific_environment_setup(){
		shiptype_one="triangle";
		shiptype_two="pentagon";
		shiptype_three="triangle";
		
		percentfreq_one=50;
		percentfreq_two=50;
		percentfreq_three=0;
		
		assumedfreq_one=50;
		assumedfreq_two=50;
		assumedfreq_three=0;
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
		
	}
	
	void level_specific_waves(){
		if (shipwave==1){
			//spawn_enemy_ship(-1, "triangle", false);
			//spawn_enemy_ship(1, "pentagon", false);
			spawn_random_obscured(-1);
			spawn_random_obscured(1);
		}
		if (shipwave==2){
			//spawn_enemy_ship(-1, "pentagon", false);
			//spawn_enemy_ship(1, "triangle", false);
			spawn_random_obscured(-2);
			spawn_random_obscured(0);
			spawn_random_obscured(2);
		}
		if (shipwave==3){
			
			suppress_phasing=true;
			spawn_enemy_ship(-2, "triangle", false);
			spawn_enemy_ship(0, "pentagon", false);
			spawn_enemy_ship(2, "triangle", false);
		}
		if (shipwave==4){
			spawn_enemy_ship(-2, "pentagon", false);
			spawn_enemy_ship(0, "triangle", false);
			spawn_enemy_ship(2, "pentagon", false);
		}
		if (shipwave==5){
			spawn_random_obscured(0);
		}
		if (shipwave==6){
			spawn_random_obscured(-1);
			spawn_random_obscured(1);
		}
		if (shipwave==7){
			spawn_random_obscured(-2);
			spawn_enemy_ship(0,"pentagon",false);
			spawn_random_obscured(2);
		}
		if (shipwave>total_shipwaves){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
	}
	
	void level_specific_de_sheild(String dot_type){
		
	}
	
	void level_specific_bayesian_update(String dot_type, EnemyShip enemyship){
		if (dot_type.equals("destroy")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.4f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.8f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.1f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.1f;
		}
		normalize(enemyship);
	}
	
	//---Spawning---
	
	void spawn_random_obscured(int xposn){
		int k=MathUtils.random(1,100);
		if (k<percentfreq_one){
			spawn_enemy_ship(xposn, shiptype_one, true);
		}
		else if (k<(percentfreq_one+percentfreq_two)){
			spawn_enemy_ship(xposn, shiptype_two, true);
		}
		else{
			spawn_enemy_ship(xposn, shiptype_three, true);
		}
	}
	
	void spawn_enemy_ship(int xposn, String turret_id, boolean obsc){
		EnemyShip enemyship=new EnemyShip(xposn, turret_id, obsc);
		
		enemyship.assignedprob_one=(float)assumedfreq_one/100f;
		enemyship.assignedprob_two=(float)assumedfreq_two/100f;
		enemyship.assignedprob_three=(float)assumedfreq_three/100f;
		
		enemyships.add(enemyship);
		turrets.add(enemyship.turret);
		turrets_standard.add((Turret_Standard)enemyship.turret);
		
		enemyship.turret.rect.x=enemyship.rect.x+10;
		enemyship.turret.rect.y=enemyship.rect.y+10;
	}
	
	void spawnMine(EnemyShip enemyship) {
		   
		   Mine mine = new Mine(enemyship);
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
		font.draw(batch, "TRI/PENT", 90, 473, 140, 1, true);
		font.draw(batch, "WAVE: "+shipwave+"/"+total_shipwaves, 90, 455, 140, 1, true);
		font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
		font.draw(batch, "SHIELDS: "+ shields, 90, 419, 140, 1, true);
	}
	
	//---Useful functions---
	
	void normalize(EnemyShip enemyship){
		float total=enemyship.assignedprob_one+enemyship.assignedprob_two+enemyship.assignedprob_three;
		if (total>0){
			enemyship.assignedprob_one=enemyship.assignedprob_one/total;
			enemyship.assignedprob_two=enemyship.assignedprob_two/total;
			enemyship.assignedprob_three=enemyship.assignedprob_three/total;
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
	
	boolean is_this_ship_being_attacked(EnemyShip enemyship){
		for (Mine mine: mines){
			if (mine.target_enemy_ship==enemyship){
				return true;
			}
		}
		return false;
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
	
	void hand_over_to_bowling(){
		current_status="bowling";
		status_effects();
	}
	
	void hand_over_to_targeting(){
		currently_active_vane=vanes.first();
		current_status="targeting";
	}
	
	void hand_over_to_zapping(){
		current_status="zapping";
		currently_active_vane=null;
		set_up_zapping_times();
	}
	
	void hand_over_to_firing(){
		for (Turret turret: turrets){
			turret.targeted=true;
		}
		set_up_firing_times();
		current_status="firing";
	}
	
	void do_bowling_things(){
		if (Gdx.input.justTouched() && pick_a_ship()!=null){
			EnemyShip enemyship=pick_a_ship();
			if (!is_this_ship_being_attacked(enemyship)){
				spawnMine(enemyship);
				enemyship.turret.targeted=true;
			}
		}
		for (Mine mine:mines){
			if (mine.actually_there && mine.target_enemy_ship!=null){
				if (mine.rect.y>160){
					if (mine.target_enemy_ship.turret.targeted){
					   if (mine.target_enemy_ship.turret.turret_type.equals("standard")){
						   Turret_Standard T=(Turret_Standard)mine.target_enemy_ship.turret;
						   if (T.targeted && (T.firing_time+0.01f*T.shotsmade)<total_time){
							   if (T.shotsmade==0){fire.play(0.3f);}
							   T.shotsmade+=1;
							   String output=mine.target_enemy_ship.turret.determine_output();
							   
							   level_specific_bayesian_update(output, mine.target_enemy_ship);
							   RT_Dot dot=new RT_Dot(mine.target_enemy_ship.turret.rect, mine, 3000, output);
							   dots.add(dot);
						   }
						   
						   if (T.shotsmade>=T.turret_level){
							   T.targeted=false;
						   }
						   
					   }
					}
				}
			}
		}
		if (Gdx.input.justTouched()&& tp_y<80 && mines.size==0){
			current_status="waiting";
			shock.play();
		}
	}
	
	void do_firing_things(){
		for(EnemyShip enemyship:enemyships){
			if (enemyship.turret.targeted){
			   if (enemyship.turret.turret_type.equals("standard")){
				   Turret_Standard T=(Turret_Standard)enemyship.turret;
				   if (T.targeted && (T.firing_time+0.01f*T.shotsmade)<total_time){
					   if (T.shotsmade==0){fire.play(0.3f);}
					   T.shotsmade+=1;
					   String output=enemyship.turret.determine_output();
					   
					   level_specific_bayesian_update(output, enemyship);
					   RT_Dot dot=new RT_Dot(enemyship.turret.rect, 160, 0, 3000, output);
					   dots.add(dot);
				   }
				   
				   if (T.shotsmade>=T.turret_level){
					   T.targeted=false;
				   }
				   
			   }
					   
			   
		   }
			if (enemyship.turret.firing_time<total_time && (enemyship.turret.firing_time+0.1f)>total_time){
			   enemyship.turret.current_t=enemyship.turret.firing_t;
		   }
		   else{
				enemyship.turret.current_t=enemyship.turret.normal_t;
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
				//shock.play(0.4f);
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
						if (vane.current_energy.equals(shiptype_one)){
							vane.target_ship.assignedprob_one=0;
						}
						if (vane.current_energy.equals(shiptype_two)){
							vane.target_ship.assignedprob_two=0;
						}
						if (vane.current_energy.equals(shiptype_three)){
							vane.target_ship.assignedprob_three=0;
						}
						normalize(vane.target_ship);
					}
				}
			}
			else{
				vane.targeted=false;
			}
		}   
		if (total_time>zappy_ending_time){
			
			hand_over_to_firing();
			//current_status="waiting";
			//status_effects();
	    }
	}
	
	@Override
	
	void handle_seconds(){
	   if (seconds<Math.floor(total_time)){
			seconds+=1;
			
			if (seconds%2==0 && !current_status.equals("bowling")){
				if(any_interesting_ships()){
					hand_over_to_targeting();
				}
				else{
					shipwave+=1;
					level_specific_waves();
					if (!suppress_phasing){
						hand_over_to_bowling();
						shock.play();
					}
				}
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
			   if (vane.current_energy.equals("circle")){
					batch.draw(vane_crosshairs_circle_t, tp_x-30, tp_y-30);
				}
				if (vane.current_energy.equals("triangle")){
					batch.draw(vane_crosshairs_triangle_t, tp_x-30, tp_y-30);
				}
				if (vane.current_energy.equals("square")){
					batch.draw(vane_crosshairs_square_t, tp_x-30, tp_y-30);
				}
				if (vane.current_energy.equals("pentagon")){
					batch.draw(vane_crosshairs_pentagon_t, tp_x-30, tp_y-30);
				}
				if (vane.current_energy.equals("hexagon")){
					batch.draw(vane_crosshairs_hexagon_t, tp_x-30, tp_y-30);
				}
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
	
	void check_for_mine_enemyshipshield_collisions(){
			   for (Mine mine: mines){
				   for (EnemyShip enemyship:enemyships){
					if(mine.rect.overlaps(enemyship.shield_r)) {
						minecount-=1;
						if (!mine.shootproof){
					     	spawnExplosion(mine.rect.x,mine.rect.y);
					        minehitshield.play(0.1f);
					        minesplode.play();
						}
						mines.removeValue(mine,true);
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
		
		if (current_status.equals("targeting")){
			draw_energy_things();
		}
		
		
		
		display_probabilities();
		
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
		
		if (current_status.equals("bowling")){
			do_bowling_things();
		}
		
		if (current_status.equals("targeting")){
			//check_for_keypresses();
			do_targeting_things();
		}
		
		//check for collisions!
		
		check_for_dot_mineshield_collisions();
		
		check_for_dot_mine_collisions();
		
		check_for_dot_shipshield_collisions();
		
		check_for_mine_enemyshipshield_collisions();
		
		gamey_render_postdraw();
		
		if(Gdx.input.justTouched()){
			if (menu_button_r.contains(tp_x, tp_y)){
				game.setScreen(new TitleScreen(game, true));
				  dispose();
			}
		}
	}
	
}