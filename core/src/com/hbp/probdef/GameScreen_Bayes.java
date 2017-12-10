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
	
	 Texture indicate_ships_t;
	 Texture indicate_shockers_t;
	
	Vane vane_one;
	Vane vane_two;
	
	Vane currently_active_vane;
	EnemyShip the_selected_enemyship;
	
	int shipwave;
	int total_shipwaves;
	int round;
	
	int number_of_turret_types;
	
	String turret_type_one;
	String turret_type_two;
	String turret_type_three;
	String turret_type_four;
	String turret_type_five;
	
	char ship_one_engines;
	char ship_one_front;
	char ship_one_back;
	
	char ship_two_engines;
	char ship_two_front;
	char ship_two_back;
	
	char ship_three_engines;
	char ship_three_front;
	char ship_three_back;
	
	int ship_one_percentfreq_one=0;
	int ship_one_percentfreq_two=0;
	int ship_one_percentfreq_three=0;
	int ship_one_percentfreq_four=0;
	int ship_one_percentfreq_five=0;
	
	int ship_two_percentfreq_one=0;
	int ship_two_percentfreq_two=0;
	int ship_two_percentfreq_three=0;
	int ship_two_percentfreq_four=0;
	int ship_two_percentfreq_five=0;
	
	int ship_three_percentfreq_one=0;
	int ship_three_percentfreq_two=0;
	int ship_three_percentfreq_three=0;
	int ship_three_percentfreq_four=0;
	int ship_three_percentfreq_five=0;
	
	int ship_one_assumedfreq_one=0;
	int ship_one_assumedfreq_two=0;
	int ship_one_assumedfreq_three=0;
	int ship_one_assumedfreq_four=0;
	int ship_one_assumedfreq_five=0;
	
	int ship_two_assumedfreq_one=0;
	int ship_two_assumedfreq_two=0;
	int ship_two_assumedfreq_three=0;
	int ship_two_assumedfreq_four=0;
	int ship_two_assumedfreq_five=0;
	
	int ship_three_assumedfreq_one=0;
	int ship_three_assumedfreq_two=0;
	int ship_three_assumedfreq_three=0;
	int ship_three_assumedfreq_four=0;
	int ship_three_assumedfreq_five=0;
	
	boolean suppress_phasing;
	boolean suppress_autocalc;
	boolean suppress_exits;
	
	float specific_start_time;
	
	public GameScreen_Bayes(final ProbDef gam) {
		
		super(gam);
		game=gam;
		
		indicate_shockers_t=new Texture(Gdx.files.internal("indicate_shockers.png"));
		//indicate_ships_t=new Texture(Gdx.files.internal("indicate_ships.png"));
		
		bayesian=true;
		
		current_status="waiting";
		
		attention_button_trim_t=green_button_trim_t;
		
		shields=30;
		
		total_shipwaves=10;
		
		minecount=40;
		
		original_minecount=40;
		
		level_specific_environment_setup();
		
		level_specific_assumption_setup();
		
		level_specific_ship_aesthetics();
		
		vane_setup();
		
		suppress_phasing=false;
		suppress_autocalc=false;
		suppress_exits=false;
		
		timeouting=true;
	}
	
	void vane_setup(){
		vane_one=new Vane();
		vane_two=new Vane();
		
		vane_one.current_energy=level_specific_forward_energy_cycle("");
		vane_two.current_energy=level_specific_forward_energy_cycle("");

		
		vane_one.rect.x=55;
		vane_one.rect.y=25;
		vane_two.rect.x=215;
		vane_two.rect.y=25;
		
		vanes.add(vane_one);
		vanes.add(vane_two);
	}
	
	
	
	
	
	
	
	//---level_specific_yadda_yadda()---
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='a';
		ship_one_front='a';
		ship_one_back='a';
		
		ship_two_engines='b';
		ship_two_front='a';
		ship_two_back='a';
		
		ship_three_engines='c';
		ship_three_front='a';
		ship_three_back='a';
		
	}
	
	void level_specific_environment_setup(){
		
		number_of_turret_types=2;
		
		turret_type_one="triangle";
		turret_type_two="pentagon";
		
		ship_one_percentfreq_one=50;
		ship_one_percentfreq_two=50;
		
		ship_two_percentfreq_one=70;
		ship_two_percentfreq_two=30;
		
		ship_three_percentfreq_one=30;
		ship_three_percentfreq_two=70;
		
	}
	
	void level_specific_probability_display(){
		if (!suppress_autocalc){
			for (EnemyShip enemyship:enemyships){
				if (enemyship.obscured){
					acalc_greenfont.draw(batch, "T: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_two*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
				}
			}
		}
	}
	
	String level_specific_forward_energy_cycle(String original){
		if (number_of_turret_types==2){
			if (original.equals(turret_type_one)){
				return turret_type_two;
			}
			if (original.equals(turret_type_two)){
				return turret_type_one;
			}
			return turret_type_two;
		}
		else if (number_of_turret_types==3){
			if (original.equals(turret_type_one)){
				return turret_type_two;
			}
			if (original.equals(turret_type_two)){
				return turret_type_three;
			}
			if (original.equals(turret_type_three)){
				return turret_type_one;
			}
			return turret_type_three;
		}
		else if (number_of_turret_types==4){
			if (original.equals(turret_type_one)){
				return turret_type_two;
			}
			if (original.equals(turret_type_two)){
				return turret_type_three;
			}
			if (original.equals(turret_type_three)){
				return turret_type_four;
			}
			if (original.equals(turret_type_four)){
				return turret_type_one;
			}
			return turret_type_four;
		}
		else if (number_of_turret_types==5){
			if (original.equals(turret_type_one)){
				return turret_type_two;
			}
			if (original.equals(turret_type_two)){
				return turret_type_three;
			}
			if (original.equals(turret_type_three)){
				return turret_type_four;
			}
			if (original.equals(turret_type_four)){
				return turret_type_five;
			}
			if (original.equals(turret_type_five)){
				return turret_type_one;
			}
			return turret_type_five;
		}
		return turret_type_one;
	}
	
	String level_specific_backward_energy_cycle(String original){
		
		
		if (number_of_turret_types==2){
			if (original.equals(turret_type_one)){
				return turret_type_two;
			}
			if (original.equals(turret_type_two)){
				return turret_type_one;
			}
			return turret_type_two;
		}
		else if (number_of_turret_types==3){
			if (original.equals(turret_type_two)){
				return turret_type_one;
			}
			if (original.equals(turret_type_three)){
				return turret_type_two;
			}
			if (original.equals(turret_type_one)){
				return turret_type_three;
			}
			return turret_type_three;
		}
		else if (number_of_turret_types==4){
			if (original.equals(turret_type_two)){
				return turret_type_one;
			}
			if (original.equals(turret_type_three)){
				return turret_type_two;
			}
			if (original.equals(turret_type_four)){
				return turret_type_three;
			}
			if (original.equals(turret_type_one)){
				return turret_type_four;
			}
			return turret_type_four;
		}
		else if (number_of_turret_types==5){
			if (original.equals(turret_type_two)){
				return turret_type_one;
			}
			if (original.equals(turret_type_three)){
				return turret_type_two;
			}
			if (original.equals(turret_type_four)){
				return turret_type_three;
			}
			if (original.equals(turret_type_five)){
				return turret_type_four;
			}
			if (original.equals(turret_type_one)){
				return turret_type_five;
			}
			return turret_type_five;
		}
		return turret_type_one;
	}
	
void level_specific_waves(){
		
		suppress_phasing=false;
		suppress_autocalc=false;
		
		if (shipwave==1){
			ship_one_spawn_enemy_ship(-2, "pentagon", false);
			ship_one_spawn_enemy_ship(0, "triangle", false);
			ship_one_spawn_enemy_ship(2, "triangle", false);
			suppress_phasing=true;
		}
		if (shipwave==2){
			ship_one_spawn_enemy_ship(-2, "triangle", false);
			ship_one_spawn_enemy_ship(0, "pentagon", false);
			ship_one_spawn_enemy_ship(2, "triangle", false);
			suppress_phasing=true;
		}
		if (shipwave==3){
			ship_one_spawn_enemy_ship(-1, "triangle", false);
			ship_one_spawn_enemy_ship(1, "pentagon", false);
			suppress_phasing=true;
		}
		if (shipwave==4){
			ship_one_spawn_random(0,true);
			suppress_phasing=true;
			suppress_autocalc=true;
		}
		if (shipwave==5){
			ship_one_spawn_random(-2, true);
			ship_one_spawn_random(0, true);
			ship_one_spawn_random(2, true);
			suppress_phasing=true;
			suppress_autocalc=true;
		}
		if (shipwave==6){
			ship_one_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
			specific_start_time=total_time;
			suppress_autocalc=true;
		}
		if (shipwave==7){
			ship_one_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
		if (shipwave==8){
			ship_one_spawn_random(-2, true);
			ship_two_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		if (shipwave==9){
			ship_two_spawn_random(-1, true);
			ship_three_spawn_random(1, true);
		}
		if (shipwave==10){
			ship_three_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		purpletext=false;
		indicate=false;
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				indicate=true;
				the_text="Enemy ships threaten yours. Click the leftmost ship to target it with one of your shockers.";
				if (vane_one.targeted){
					the_text="The right shocker has a pentagon zap, so it won't work on ships with triangle turrets; click on it to cycle zaps.";
					if (vane_two.current_energy.equals("triangle")){
						the_text="Now click on one of the triangle ships, to target it with that shocker.";
					}
				}
				if (vane_one.targeted&&vane_two.targeted){
					if (vane_two.current_energy.equals("triangle")){
						the_text="Once you're done targeting, click the fire button at the top of the screen to zap the targeted ships.";
					}
					else{
						the_text="The shapes don't match. Click the right shocker - at the bottom right corner of your screen - to retarget it.";
					}
				}
			}
			if ((round==1 && (current_status.equals("firing") || current_status.equals("zapping") || current_status.equals("waiting")))||(round==2)){
				show_the_text=true;
				the_text="On the enemy turn, the remaining ships shoot back. All successful attacks cost you one shield.";
			}
		}
		if (shipwave==2){
			show_the_text=true;
			if (round==1 && current_status.equals("targeting")){
				the_text="Turrets can fire red/square/destroying shots, blue/circular/capturing shots, or fail to fire at all.";
				if (vane_one.targeted || vane_two.targeted){
					the_text="Triangle Turrets fail 30% of the time, capture 30% of the time, and destroy 40% of the time.";
				   }
				if (vane_one.targeted && vane_two.targeted){
					the_text="Pentagon Turrets fail 10% of the time, capture 10% of the time, and destroy 80% of the time.";
				}
			}
			if (round==2){
				purpletext=true;
				the_text="(btw you can hover your mouse over a ship if you want a reminder of which turrets do what)";
			}
		}
		if (shipwave==3){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="If you want, you can use hotkeys. Up/down cycles zaps, left/right cycles ships, spacebar selects.";
				if (vane_one.targeted||vane_two.targeted){
					infuriatingly_specific_bool=true;
				}
				if (infuriatingly_specific_bool){
					the_text="You can also use 1 and 2 to select shockers. When all shockers are targeted, you can press space again to fire.";
				}
			}
		}
		if (shipwave==4){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="This ship has obscured itself. Target it with a triangle zap and a pentagon zap to make sure it's removed from play.";
			}
		}
		if (shipwave==5){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Ships of this type are 50% pentagon-turreted and 50% triangle-turreted. At this point, it's a matter of luck.";
			}
			if (round==2 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="If a ship survives a triangle zap, that proves it's not a triangle, so it must be a pentagon. Ditto the reverse.";
			}
		}
		if (shipwave==6){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				if (minecount==original_minecount){
					the_text="That last wave was rough. Let's make this easier. Click on an enemy ship to launch a mine toward it.";
				}
				if (minecount==(original_minecount-1) || minecount==(original_minecount-2)){
					the_text="The more shots you see them take, the better you'll be able to guess which ships have which turrets.";
				}
				if (minecount<(original_minecount-2)){
					the_text="When you're ready to start the wave proper, click your ship. Alternatively, press space with no ship selected.";
				}
				if (minecount<(original_minecount-9)){
					purpletext=true;
					the_text="(ok thats too many mines you need to leave some for later)";
				}
				
			}
		}
		if (shipwave==7){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				the_text="This reasoning can be done by the autocalc. When ships fire, probabilities update. Throw more mines to see it in action.";
			}
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="The autocalc also updates probabilities when zaps fail.";
			}
		}
		if (shipwave==8){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				the_text="Not all ships are of the same type. The autocalc knows which shiptypes carry what turrets with what probability.";
			}
		}
		if (shipwave==9){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				purpletext=true;
				the_text="(oh and jsyk you can also cycle between ships with arrowkeys and launch mines with spacebar if you prefer)";
			}
		}
		
	}
	
	
	
	void level_specific_deshield(RT_Dot dot){
		if (dot.type.equals("destroy")){
			shields-=1;
		}
		if (dot.type.equals("capture")){
			shields-=1;
		}
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
	
	void level_specific_assumption_setup(){
		ship_one_assumedfreq_one=ship_one_percentfreq_one;
		ship_one_assumedfreq_two=ship_one_percentfreq_two;
		ship_one_assumedfreq_three=ship_one_percentfreq_three;
		
		ship_two_assumedfreq_one=ship_two_percentfreq_one;
		ship_two_assumedfreq_two=ship_two_percentfreq_two;
		ship_two_assumedfreq_three=ship_two_percentfreq_three;
		
		ship_three_assumedfreq_one=ship_three_percentfreq_one;
		ship_three_assumedfreq_two=ship_three_percentfreq_two;
		ship_three_assumedfreq_three=ship_three_percentfreq_three;
	}
	
	@Override
	
	void draw_the_HUD(){
		boolean torrit=false;
	      
	      for (EnemyShip enemyship: enemyships){
	    	  if (enemyship.rect.contains(tp_x, tp_y)){
	    		  torrit=true;
	    		  if(!enemyship.obscured){		  
		    		  if (enemyship.turret.lines_no==4){
		    			font.draw(batch, enemyship.turret.line_one, 90, 472, 140,1, true);
		  				font.draw(batch, enemyship.turret.line_two, 90, 455, 140, 1, true);
		  				font.draw(batch, enemyship.turret.line_three, 90, 437, 140, 1, true);
		  				font.draw(batch, enemyship.turret.line_four, 90, 420, 140, 1, true);
		    		  }
		    		  if (enemyship.turret.lines_no==3){
			  			font.draw(batch, enemyship.turret.line_one, 90, 465, 140, 1, true);
			  			font.draw(batch, enemyship.turret.line_two, 90, 448, 140, 1, true);
			  			font.draw(batch, enemyship.turret.line_three, 90, 431, 140, 1, true);
		    		  }
	    		  }
	    		  else{
	    			  if (enemyship.shiptype==1){
	    				  level_specific_ST_ONE_HUD();
	    			  }
	    			  if (enemyship.shiptype==2){
	    				  level_specific_ST_TWO_HUD();
	    			  }
	    			  if (enemyship.shiptype==3){
	    				  level_specific_ST_THREE_HUD();
	    			  }
	    		  }
	    	  }
	      }
	      
	      if (!torrit){
	    	level_specific_HUD();
	      }
	}
	
	@Override
	
	void level_specific_HUD(){
		font.draw(batch, "TRI/PENT", 90, 473, 140, 1, true);
		font.draw(batch, "WAVE: "+Math.min(shipwave, total_shipwaves)+"/"+total_shipwaves, 90, 455, 140, 1, true);
		font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
		font.draw(batch, "SHIELDS: "+ shields, 90, 419, 140, 1, true);
	}


	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN A34", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN A19", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN A22", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	//---Spawning---
	
	void ship_one_spawn_random(int xposn, boolean obsc){
		int k=MathUtils.random(1,100);
		if (k<=ship_one_percentfreq_one){
			ship_one_spawn_enemy_ship(xposn, turret_type_one, obsc);
		}
		else if (k<=(ship_one_percentfreq_one+ship_one_percentfreq_two)){
			ship_one_spawn_enemy_ship(xposn, turret_type_two, obsc);
		}
		else{
			ship_one_spawn_enemy_ship(xposn, turret_type_three, obsc);
		}
	}
	
	void ship_two_spawn_random(int xposn, boolean obsc){
		int k=MathUtils.random(1,100);
		if (k<ship_two_percentfreq_one){
			ship_two_spawn_enemy_ship(xposn, turret_type_one, obsc);
		}
		else if (k<=(ship_two_percentfreq_one+ship_two_percentfreq_two)){
			ship_two_spawn_enemy_ship(xposn, turret_type_two, obsc);
		}
		else{
			ship_two_spawn_enemy_ship(xposn, turret_type_three, obsc);
		}
	}
	
	void ship_three_spawn_random(int xposn, boolean obsc){
		int k=MathUtils.random(1,100);
		if (k<ship_three_percentfreq_one){
			ship_three_spawn_enemy_ship(xposn, turret_type_one, obsc);
		}
		else if (k<=(ship_three_percentfreq_one+ship_three_percentfreq_two)){
			ship_three_spawn_enemy_ship(xposn, turret_type_two, obsc);
		}
		else{
			ship_three_spawn_enemy_ship(xposn, turret_type_three, obsc);
		}
	}
	
	void ship_one_spawn_enemy_ship(int xposn, String turret_id, boolean obsc){
		EnemyShip enemyship=new EnemyShip(xposn, turret_id, obsc, ship_one_front, ship_one_back, ship_one_engines);
		
		enemyship.assignedprob_one=(float)ship_one_assumedfreq_one/100f;
		enemyship.assignedprob_two=(float)ship_one_assumedfreq_two/100f;
		enemyship.assignedprob_three=(float)ship_one_assumedfreq_three/100f;
		
		enemyship.shiptype=1;
		
		enemyships.add(enemyship);
		turrets.add(enemyship.turret);
		turrets_standard.add((Turret_Standard)enemyship.turret);
		
		enemyship.turret.rect.x=enemyship.rect.x+10;
		enemyship.turret.rect.y=enemyship.rect.y+10;
	}
	
	void ship_two_spawn_enemy_ship(int xposn, String turret_id, boolean obsc){
		EnemyShip enemyship=new EnemyShip(xposn, turret_id, obsc, ship_two_front, ship_two_back, ship_two_engines);
		
		enemyship.assignedprob_one=(float)ship_two_assumedfreq_one/100f;
		enemyship.assignedprob_two=(float)ship_two_assumedfreq_two/100f;
		enemyship.assignedprob_three=(float)ship_two_assumedfreq_three/100f;
		
		enemyship.shiptype=2;
		
		enemyships.add(enemyship);
		turrets.add(enemyship.turret);
		turrets_standard.add((Turret_Standard)enemyship.turret);
		
		enemyship.turret.rect.x=enemyship.rect.x+10;
		enemyship.turret.rect.y=enemyship.rect.y+10;
		
	}
	
	void ship_three_spawn_enemy_ship(int xposn, String turret_id, boolean obsc){
		EnemyShip enemyship=new EnemyShip(xposn, turret_id, obsc, ship_three_front, ship_three_back, ship_three_engines);
		
		enemyship.assignedprob_one=(float)ship_three_assumedfreq_one/100f;
		enemyship.assignedprob_two=(float)ship_three_assumedfreq_two/100f;
		enemyship.assignedprob_three=(float)ship_three_assumedfreq_three/100f;
		
		enemyship.shiptype=3;
		
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
	
	
	
	//---Useful functions---
	
	void normalize(EnemyShip enemyship){
		float total=enemyship.assignedprob_one+enemyship.assignedprob_two+enemyship.assignedprob_three+enemyship.assignedprob_four+enemyship.assignedprob_five;
		if (total>0){
			enemyship.assignedprob_one=enemyship.assignedprob_one/total;
			enemyship.assignedprob_two=enemyship.assignedprob_two/total;
			enemyship.assignedprob_three=enemyship.assignedprob_three/total;
			enemyship.assignedprob_four=enemyship.assignedprob_four/total;
			enemyship.assignedprob_five=enemyship.assignedprob_five/total;
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
		if (currently_active_vane!=null){
			if (currently_active_vane.targeted || !currently_active_vane.does_it_work){
				
				
				if (currently_active_vane==vanes.peek()){
				   currently_active_vane=null;
				   the_selected_enemyship=null;
				}
				else{
					currently_active_vane=vanes.get(vanes.indexOf(currently_active_vane, true)+1);
					skip_through_vanes();
				}
			}
		}
	}
	
	void vanejump(){
		if (currently_active_vane==vanes.peek()){
		   currently_active_vane=null;
		   the_selected_enemyship=null;
		}
		else{
			currently_active_vane=vanes.get(vanes.indexOf(currently_active_vane, true)+1);
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
		the_selected_enemyship=null;
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
		if (Gdx.input.justTouched() && pick_a_ship()!=null && (minecount>mines.size)){
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
							   if (T.shotsmade==0){fire.play(option_sfx_volume*0.3f);}
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
		for (EnemyShip enemyship:enemyships){
			if (enemyship.turret.firing_time<total_time && (enemyship.turret.firing_time+0.1f)>total_time){
				enemyship.turret.current_t=enemyship.turret.firing_t;
		   }
		   else{
				enemyship.turret.current_t=enemyship.turret.normal_t;
		   }
		}
		if (Gdx.input.justTouched()&& tp_y<80 && mines.size==0){
			current_status="waiting";
			shock.play(option_sfx_volume);
		}
	}
	
	void do_firing_things(){
		for(EnemyShip enemyship:enemyships){
			if (enemyship.turret.targeted){
			   if (enemyship.turret.turret_type.equals("standard")){
				   Turret_Standard T=(Turret_Standard)enemyship.turret;
				   if (T.targeted && (T.firing_time+0.01f*T.shotsmade)<total_time){
					   if (T.shotsmade==0){fire.play(option_sfx_volume*0.3f);}
					   T.shotsmade+=1;
					   String output=enemyship.turret.determine_output();
					   
					   level_specific_bayesian_update(output, enemyship);
					   RT_Dot dot=new RT_Dot(enemyship.turret.rect, 160, 0, 3000, output);
					   dots.add(dot);
					   //status_effects();
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
					laser.play(option_sfx_volume*0.4f);
					if (vane.current_energy.equals(vane.target_ship.turret.ident)){
					   spawnBigExplosion(vane.target_ship.turret.rect.x, vane.target_ship.turret.rect.y);
					   minesplode.play(option_sfx_volume);
					   enemyships.removeValue(vane.target_ship, true);
					   turrets.removeValue(vane.target_ship.turret, true);
					   vane.target_ship.actually_there=false;
					   if (vane.target_ship.turret.turret_type.equals("standard")){
						   turrets_standard.removeValue((Turret_Standard)vane.target_ship.turret, true);
					   }
					   vane.target_ship.turret.dispose();
					}
					else{
						mistaken.play(option_sfx_volume*0.7f);
						if (vane.current_energy.equals(turret_type_one)){
							vane.target_ship.assignedprob_one=0;
						}
						if (vane.current_energy.equals(turret_type_two)){
							vane.target_ship.assignedprob_two=0;
						}
						if (vane.current_energy.equals(turret_type_three)){
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
					round+=1;
					hand_over_to_targeting();
				}
				else{
					shipwave+=1;
					round=0;
					level_specific_waves();
					if (!suppress_phasing){
						hand_over_to_bowling();
						if (shipwave<total_shipwaves){
							shock.play(option_sfx_volume);
						}
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
			if (vane.does_it_work){
				if (vane.targeted || currently_active_vane==vane){
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
		}
	}
	
	void draw_targeting_things(){
		for (Vane vane:vanes){
			if (vane==currently_active_vane){
			   batch.draw(vane_trim_t, vane.rect.x, vane.rect.y);
				draw_green_dotted_line(vane.rect.x+vane.rect.width/2, vane.rect.y+vane.rect.height/2, tp_x,tp_y,10);
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
				draw_green_dotted_line(vane.rect.x+vane.rect.width/2, vane.rect.y+vane.rect.height/2, vane.target_ship.rect.x+vane.target_ship.rect.width/2,vane.target_ship.rect.y+vane.target_ship.rect.height/2,10);
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
	
	private void draw_green_dotted_line(float start_x, float start_y, float finish_x, float finish_y, int number_of_divs){
		   for (int q=1; q<number_of_divs; q++){
			   float centre_x=start_x+((float)q/(float)number_of_divs)*(finish_x-start_x);
			   float centre_y=start_y+((float)q/(float)number_of_divs)*(finish_y-start_y);
			   batch.draw(green_dot_t, centre_x-1, centre_y-1);
		   }
	   }
	//---More collisions---
	
	void check_for_dot_shipshield_collisions(){
		for (RT_Dot dot: dots){
			if (dot.target_mine==null && (dot.rect.overlaps(shield_r)|| dot.rect.y<shield_r.y)){
				dots.removeValue(dot, true);
				if (dot.type.equals("destroy")|| dot.type.equals("capture")){
					deshield.play(option_sfx_volume);
					level_specific_deshield(dot);
					if (option_flicker){
						shipshield_t=shipshield_flicker_t;
					}
				}
			}
		}
	}
	
	void check_for_mine_enemyshipshield_collisions(){
			   for (Mine mine: mines){
				   for (EnemyShip enemyship:enemyships){
					if(mine.rect.overlaps(enemyship.shield_r) || mine.rect.y>enemyship.shield_r.y) {
						enemyship.flicker=true;
						minecount-=1;
						if (!mine.shootproof){
					     	spawnExplosion(mine.rect.x,mine.rect.y);
					        minehitshield.play(option_sfx_volume*0.1f);
					        minesplode.play(option_sfx_volume);
						}
						mines.removeValue(mine,true);
				     }
				   }
				}
	}
	
	//HOTKEYS
	
	private void check_for_keypresses_bowling(){
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			   if(the_selected_enemyship==null && mines.size==0){
				   	current_status="waiting";
					shock.play(option_sfx_volume);
			   }
			   else{
				   if (the_selected_enemyship!=null && !is_this_ship_being_attacked(the_selected_enemyship) && (minecount>mines.size)){
						spawnMine(the_selected_enemyship);
						the_selected_enemyship.turret.targeted=true;
					}
			   }
		   }
		
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
		   cycle_ships_forward();
	   }
	   if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
		   cycle_ships_back();
	   }
	}
	
	private void check_for_keypresses_targeting(){
		   
		   //handle the final firing.
		   if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			   if(currently_active_vane==null){
				   hand_over_to_zapping();
			   }
			   else{
				   
				   if (the_selected_enemyship!=null){
					   currently_active_vane.targeted=true;
					   currently_active_vane.target_ship=the_selected_enemyship;
				   }
				   vanejump();
				   skip_through_vanes();
			   }
		   }
		   
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_1) || Gdx.input.isKeyJustPressed(Keys.A)){
			   currently_active_vane=vane_one;
			   vane_one.targeted=false;
			   vane_one.target_ship=null;
		   }
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_2) || Gdx.input.isKeyJustPressed(Keys.S)){
			   currently_active_vane=vane_two;
			   vane_two.targeted=false;
			   vane_two.target_ship=null;
		   }
		   if (currently_active_vane!=null){
			   if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
				   cycle_ships_forward();
			   }
			   if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
				   cycle_ships_back();
			   }
			   
			   if (Gdx.input.isKeyJustPressed(Keys.UP)){ //|| Gdx.input.getInputProcessor().scrolled(1)){
					currently_active_vane.current_energy=level_specific_forward_energy_cycle(currently_active_vane.current_energy);
			   }
			   if (Gdx.input.isKeyJustPressed(Keys.DOWN)){//|| Gdx.input.getInputProcessor().scrolled(-1)){
					currently_active_vane.current_energy=level_specific_backward_energy_cycle(currently_active_vane.current_energy);
			   }
		   }
	}
	
	
	private void cycle_ships_forward(){
		if (the_selected_enemyship==null){
			   the_selected_enemyship=enemyships.first();
			   if (!screen_proper.overlaps(the_selected_enemyship.rect)){
				   cycle_ships_forward();
			   }
		   }
		   else if (the_selected_enemyship==enemyships.peek()){
			   the_selected_enemyship=null;
		   }
		   else{
			   int q=enemyships.indexOf(the_selected_enemyship, true);
			   int j=(q+1)%enemyships.size;
			   the_selected_enemyship=enemyships.get(j);
			   if (!screen_proper.overlaps(the_selected_enemyship.rect)){
				   cycle_ships_forward();
			   }
		   }
	}
	
	private void cycle_ships_back(){
		if (the_selected_enemyship==null){
			   the_selected_enemyship=enemyships.peek();
			   if (!screen_proper.overlaps(the_selected_enemyship.rect)){
				   cycle_ships_back();
			   }
		   }
		   else if (the_selected_enemyship==enemyships.first()){
			   the_selected_enemyship=null;
		   }
		   else{
			   int q=enemyships.indexOf(the_selected_enemyship, true);
			   int j=(q+enemyships.size-1)%enemyships.size;
			   the_selected_enemyship=enemyships.get(j);
			   if (!screen_proper.overlaps(the_selected_enemyship.rect)){
				   cycle_ships_back();
			   }
		   }
	}
	
	private void show_the_glow(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship==the_selected_enemyship){
				//color the front
				if (enemyship.front=='a'){
				   batch.draw(enemyship_glowy_front_a_t, enemyship.rect.x, enemyship.rect.y);
			   }
			   else if (enemyship.front=='b'){
				   batch.draw(enemyship_glowy_front_b_t, enemyship.rect.x, enemyship.rect.y);
			   }
			   else if (enemyship.front=='c'){
				   batch.draw(enemyship_glowy_front_c_t, enemyship.rect.x, enemyship.rect.y);
			   }
				//color the back
				if (enemyship.back=='a'){
				   batch.draw(enemyship_glowy_front_a_t, enemyship.rect.x, enemyship.rect.y+30, 60, 30, 0, 0, 60, 30, false, true);
			   }
			   else if (enemyship.back=='b'){
				   batch.draw(enemyship_glowy_front_b_t, enemyship.rect.x, enemyship.rect.y+30, 60, 30, 0, 0, 60, 30, false, true);
			   }
			   else if (enemyship.back=='c'){
				   batch.draw(enemyship_glowy_front_c_t, enemyship.rect.x, enemyship.rect.y+30, 60, 30, 0, 0, 60, 30, false, true);			  
			   }
			}
		}
	}
	
	//---The render---
	
	public void bayesgame_render(float delta){
		gamey_render_predraw(delta);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		gamey_render_draw_objects();
		
		if (current_status.equals("targeting")||current_status.equals("zapping")){
			draw_energy_things();
		}
		
		if (current_status.equals("targeting")|| current_status.equals("bowling")){
			show_the_glow();
		}
		
		
		if (!option_acalc.equals("Off")){
			level_specific_probability_display();
		}
		
		draw_targeting_things();
		
		gamey_render_draw_interface();
		
		if (timeouting_rn){
			if (shields>0){
				batch.draw(complete_box_t, 80, 200);
			}
			else{
				batch.draw(failed_box_t, 80, 200);
			}
		}
		
		if (indicate){
			   batch.draw(indicate_shockers_t, 105, 40);
			   //batch.draw(indicate_ships_t, 100, 375);
		}
		
		if (writing_symbol_visible){
			   batch.draw(writey_symbol_t, 10, 330);
		}
		
		batch.end();
		
		//Do appropriate things!
		
		if (current_status.equals("firing")){
			do_firing_things();
		}
		
		if (current_status.equals("zapping")){
			do_zapping_things();
		}
		
		if (current_status.equals("targeting")){
			check_for_keypresses_targeting();
			do_targeting_things();
		}
		
		if (current_status.equals("bowling")){
			check_for_keypresses_bowling();
			do_bowling_things();
			status_effects();
		}
		
		shipshield_t=shipshield_normal_t; // If the shield is flickering, we want it to flicker for only one frame; we reset it here.
		
		for (EnemyShip enemyship: enemyships){
			enemyship.flicker=false;
		}
		
		//check for collisions!
		
		check_for_dot_mineshield_collisions();
		
		check_for_dot_mine_collisions();
		
		check_for_dot_shipshield_collisions();
		
		check_for_mine_enemyshipshield_collisions();
		
		//
		
		gamey_render_postdraw();
		
		//handle exits
		
		if (timeouting && !timeouting_rn && shipwave>=total_shipwaves && enemyships.size==0 && explosions.size==0 && !suppress_exits){
			timeouting_rn=true;
			timeout_time=player_time+3;
		}
		
		if (shields<=0 && !timeouting_rn){
			timeouting_rn=true;
			timeout_time=player_time+3;
		}
		
		//---Handle all the exits!---
		
		//if (shields<=0 && exit_on_shieldfail){
		//	exit_level();
		//}
		if (timeouting_rn && player_time>timeout_time){
			if (shields>0){
				update_score_on_exit();
			}
			exit_level();
		}
		else if(Gdx.input.justTouched()){
			if (menu_button_r.contains(tp_x, tp_y)){
				if (timeouting && timeouting_rn){
					update_score_on_exit();
				}
				exit_level();
			}
		}
	}
	
	public void render(float delta){
		bayesgame_render(delta);
		
	}
	
}