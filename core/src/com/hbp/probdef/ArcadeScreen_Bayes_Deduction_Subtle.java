package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes_Deduction_Subtle extends ArcadeScreen_Bayes {
	
	final ProbDef game;
	
	public ArcadeScreen_Bayes_Deduction_Subtle(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
		minecount=60;
		
	}
	
	void set_score_name(){
		score_name="Score_Deduction_Subtle";
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		purpletext=false;
		
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Sometimes, evidence is weak, so you need to perform many independent tests.";
				if (vane_one.targeted||vane_two.targeted){
					the_text="Ships in this area have triangle, square and pentagon turrets. These all behave very similarly.";
				}
				if (vane_one.targeted&&vane_two.targeted){
					purpletext=true;
					the_text="(so yeah enjoy those extra mines, you'll need them)";
				}
			}
		}
	}
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='b';
		ship_one_front='c';
		ship_one_back='a';
		
		ship_two_engines='d';
		ship_two_front='c';
		ship_two_back='b';
		
		ship_three_engines='b';
		ship_three_front='c';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="triangle";
		turret_type_two="square";
		turret_type_three="pentagon";
		
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
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.4f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.6f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.8f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.2f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.2f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		normalize(enemyship);
	}
	
	@Override
	
	void level_specific_probability_display(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship.obscured){
				acalc_greenfont.draw(batch, "T: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nS: "+present_float(enemyship.assignedprob_two*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_three*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
			}
		}
	}
	
	@Override
	
	void level_specific_HUD(){
		font.draw(batch, "TRI/SQ/PENT", 90, 473, 140, 1, true);
		font.draw(batch, "WAVE: "+shipwave+"/"+total_shipwaves, 90, 455, 140, 1, true);
		font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+ score, 90, 419, 140, 1, true);
	}
	
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN D92", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);

	}
	
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN D94", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN D97", 90, 473, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "SQUARE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
}