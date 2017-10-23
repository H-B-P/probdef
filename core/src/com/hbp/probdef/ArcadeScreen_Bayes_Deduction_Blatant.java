package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes_Deduction_Blatant extends ArcadeScreen_Bayes {
	
	final ProbDef game;
	
	public ArcadeScreen_Bayes_Deduction_Blatant(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		minecount=30;
		
		score=30;
	}
	
	void set_score_name(){
		score_name="Score_Deduction_Blatant";
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		purpletext=false;
		
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Sometimes, evidence is blatant and definitive, so you only need to perform a few tests.";
				if (vane_one.targeted||vane_two.targeted){
					the_text="Ships in this area have circle, square and hexagon turrets. These all behave very differently.";
				}
			}
		}
		if (shipwave==2){
			if (round==1 && current_status.equals("bowling")){
				the_text="When an obscured ship fires, it either proves it's not a hexagon or proves it's not a circle.";
			}
		}
	}
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='b';
		ship_one_front='a';
		ship_one_back='b';
		
		ship_two_engines='c';
		ship_two_front='a';
		ship_two_back='b';
		
		ship_three_engines='a';
		ship_three_front='a';
		ship_three_back='b';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="circle";
		turret_type_two="square";
		turret_type_three="hexagon";
		
		ship_one_percentfreq_one=30;
		ship_one_percentfreq_two=40;
		ship_one_percentfreq_three=30;
		
		ship_two_percentfreq_one=20;
		ship_two_percentfreq_two=60;
		ship_two_percentfreq_three=20;
		
		ship_three_percentfreq_one=40;
		ship_three_percentfreq_two=20;
		ship_three_percentfreq_three=40;
		
	}
	
	@Override
	
	void level_specific_bayesian_update(String dot_type, EnemyShip enemyship){
		if (dot_type.equals("destroy")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.6f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*1f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.2f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.2f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0f;
		}
		normalize(enemyship);
	}
	
	@Override
	
	void level_specific_probability_display(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship.obscured){
				acalc_greenfont.draw(batch, "C: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nS: "+present_float(enemyship.assignedprob_two*100.0f)+"%\nH: "+present_float(enemyship.assignedprob_three*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
			}
		}
	}
	
	@Override
	
	void level_specific_HUD(){
		font.draw(batch, "CIRC/SQ/HEX", 90, 473, 140, 1, true);
		font.draw(batch, "WAVE: "+shipwave+"/"+total_shipwaves, 90, 455, 140, 1, true);
		font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+ score, 90, 419, 140, 1, true);
	}
	
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN B22", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "HEXAGON: "+ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);

	}
	
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN B53", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "HEXAGON: "+ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN B86", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "HEXAGON: "+ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
}