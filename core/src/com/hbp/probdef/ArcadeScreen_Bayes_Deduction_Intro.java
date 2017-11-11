package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes_Deduction_Intro extends ArcadeScreen_Bayes {
	
	final ProbDef game;
	
	float specific_start_time;
	
	public ArcadeScreen_Bayes_Deduction_Intro(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
		if (CAMPAIGN){
			minecount=prefs.getInteger("one_captured")+prefs.getInteger("two_captured")+prefs.getInteger("three_captured")+prefs.getInteger("four_captured")+prefs.getInteger("five_captured");
			minecount=Math.max(minecount, 0);
		}
		else{
			minecount=30;
		}
		original_minecount=minecount;
		
		specific_start_time=9000000000000000000000f;
		
	}
	
	@Override
	
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (!prefs.getBoolean("seven_done")){
				prefs.putBoolean("seven_done",true);
				prefs.putInteger("seven_spent", (original_minecount-minecount));
			}
			else if (prefs.getInteger("seven_spent")>(original_minecount-minecount)){
				prefs.putInteger("seven_spent", (original_minecount-minecount));
			}
			
			prefs.flush();
		}
		else{
			if (score>old_score){
				prefs.putInteger(score_name,score);
				prefs.flush();
			}
		}
		
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Deduction_Intro";
	}
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='a';
		ship_one_front='a';
		ship_one_back='a';
		
		ship_two_engines='b';
		ship_two_front='c';
		ship_two_back='a';
		
		ship_three_engines='c';
		ship_three_front='a';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_probability_display(){
		if (!suppress_autocalc){
			for (EnemyShip enemyship:enemyships){
				if (enemyship.obscured){
					acalc_greenfont.draw(batch, "T: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_two*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
				}
			}
		}
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="triangle";
		turret_type_two="pentagon";
		turret_type_three="triangle";
		
		ship_one_percentfreq_one=50;
		ship_one_percentfreq_two=50;
		ship_one_percentfreq_three=0;
		
		ship_two_percentfreq_one=70;
		ship_two_percentfreq_two=30;
		ship_two_percentfreq_three=0;
		
		ship_three_percentfreq_one=30;
		ship_three_percentfreq_two=70;
		ship_three_percentfreq_three=0;
		
	}
	
	@Override
	
	String level_specific_forward_energy_cycle(String original){
		if (original.equals(turret_type_one)){
			return turret_type_two;
		}
		if (original.equals(turret_type_two)){
			return turret_type_one;
		}
		return turret_type_two;
	}
	
	@Override
	
	String level_specific_backward_energy_cycle(String original){
		if (original.equals(turret_type_one)){
			return turret_type_two;
		}
		if (original.equals(turret_type_two)){
			return turret_type_one;
		}
		return turret_type_two;
	}
	
	@Override
	
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
	
	@Override
	
	void level_specific_HUD(){
		if (CAMPAIGN){
			font.draw(batch, "TRI/PENT", 90, 473, 140, 1, true);
			font.draw(batch, "WAVE: "+Math.min(shipwave, total_shipwaves)+"/"+total_shipwaves, 90, 455, 140, 1, true);
			font.draw(batch, "SHIELDS: "+shields, 90, 437, 140, 1, true);
			font.draw(batch, "MINES: "+ minecount, 90, 419, 140, 1, true);
		}
		else{
			font.draw(batch, "TRI/PENT", 90, 473, 140, 1, true);
			font.draw(batch, "WAVE: "+Math.min(shipwave, total_shipwaves)+"/"+total_shipwaves, 90, 455, 140, 1, true);
			font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
			font.draw(batch, "SCORE: "+ score, 90, 419, 140, 1, true);
		}
		
	}
	
	@Override
	
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN A12", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN A19", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN A14", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
	}
	
	@Override
	
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
			ship_one_spawn_enemy_ship(2, "pentagon", false);
			suppress_phasing=true;
		}
		if (shipwave==3){
			ship_one_spawn_random(0,true);
			suppress_phasing=true;
			suppress_autocalc=true;
		}
		if (shipwave==4){
			ship_one_spawn_random(-2, true);
			ship_one_spawn_random(0, true);
			ship_one_spawn_random(2, true);
			suppress_phasing=true;
			suppress_autocalc=true;
		}
		if (shipwave==5){
			ship_one_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
			specific_start_time=total_time;
			suppress_autocalc=true;
		}
		if (shipwave==6){
			ship_one_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
		if (shipwave==7){
			ship_one_spawn_random(-2, true);
			ship_two_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		if (shipwave==8){
			ship_two_spawn_random(-1, true);
			ship_three_spawn_random(1, true);
		}
		if (shipwave==9){
			ship_three_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
		if (shipwave==10){
			ship_two_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
		
		if (shipwave>total_shipwaves){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		purpletext=false;
		
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="You've destroyed things with turrets; now let's destroy things-with-turrets. Click the leftmost ship to target it.";
				if (vane_one.targeted){
					the_text="The right shocker has a pentagon zap, so it won't work on ships with triangle turrets; click it to cycle zaps.";
					if (vane_two.current_energy.equals("triangle")){
						the_text="Now click on one of the triangle ships, to target it with that shocker.";
					}
				}
				if (vane_one.targeted&&vane_two.targeted){
					the_text="As with turrets, you can click the fire button to fire, or click on a shocker to retarget it before firing.";
				}
			}
			if ((round==1 && (current_status.equals("firing") || current_status.equals("zapping") || current_status.equals("waiting")))||(round==2 &&current_status.equals("targeting"))){
				show_the_text=true;
				if (CAMPAIGN){
					the_text="On the enemy turn, the remaining ships shoot back. All successful attacks cost you one shield.";
				}
				else{
					the_text="On the enemy turn, the remaining ships shoot back. All successful attacks cost you one point.";
				}
				
			}
			if ((round==2 &&current_status.equals("targeting")) && vane_one.targeted){
				show_the_text=true;
				purpletext=true;
				the_text="(btw you can hover your mouse over a ship if you want a reminder of which turrets do what)";
			}
		}
		if (shipwave==2){
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
		if (shipwave==3){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="This ship has obscured itself. Target it with a triangle zap and a pentagon zap to make sure it's removed from play.";
			}
		}
		if (shipwave==4){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Ships of this type are 50% pentagon-turreted and 50% triangle-turreted. At this point, it's a matter of luck.";
			}
			if (round==2 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="If a ship survives a triangle zap, that proves it's not a triangle, so it must be a pentagon. Ditto the reverse.";
			}
		}
		if (shipwave==5){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				if (minecount==original_minecount){
//					if (total_time<specific_start_time+8){
//						the_text="That last wave was rough. Let's make this easier: your ship is now intangible at the start of each wave.";
//					}
//					else{
//						the_text="Click on an enemy ship, or cycle to it with arrow keys and press spacebar. This will launch a mine towards it.";
//					}
					the_text="That last wave was rough. Let's make this easier. Click on an enemy ship to launch a mine toward it.";
				}
				if (minecount==(original_minecount-1)){
					the_text="The more shots you see them take, the better you'll be able to guess which ships have which turrets.";
				}
				if (minecount<(original_minecount-1)){
					the_text="When you're ready to start the wave proper, click your ship. Alternatively, press space with no ship selected.";
				}
				if (minecount<(original_minecount-4)){
					purpletext=true;
					the_text="(good thing we captured so many mines in the other levels, huh?)";
				}
				if (minecount<(original_minecount-9)){
					purpletext=true;
					the_text="(ok thats too many mines you need to leave some for later)";
				}
				
			}
		}
		if (shipwave==6){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				the_text="This reasoning can be done by the autocalc. When ships fire, probabilities update. Throw more mines to see it in action.";
			}
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="The autocalc also updates probabilities when zaps fail.";
			}
		}
		if (shipwave==7){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				the_text="Not all ships are of the same type. The autocalc knows which shiptypes carry what turrets with what probability.";
			}
		}
		if (shipwave==8){
			if (round==0 && current_status.equals("bowling")){
				show_the_text=true;
				purpletext=true;
				the_text="(oh and jsyk you can also cycle between ships with arrowkeys and launch mines with spacebar if you prefer)";
			}
		}
		
	}
	
}