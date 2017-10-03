package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Bayes_Deduction_Finale extends GameScreen_Bayes {
	
	final ProbDef game;
	
	public ArcadeScreen_Bayes_Deduction_Finale(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		minecount=40;
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		greentext=false;
		
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Sometimes, evidence is less definitive.";
				if (vane_one.targeted||vane_two.targeted){
					the_text="Ships in this area have circle, triangle and pentagon turrets. These act similarly enough that it's impossible to be certain which turret does what.";
				}
			}
		}
	}
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='a';
		ship_one_front='c';
		ship_one_back='c';
		
		ship_two_engines='b';
		ship_two_front='c';
		ship_two_back='c';
		
		ship_three_engines='c';
		ship_three_front='c';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="circle";
		turret_type_two="triangle";
		turret_type_three="pentagon";
		
		ship_one_percentfreq_one=20;
		ship_one_percentfreq_two=40;
		ship_one_percentfreq_three=40;
		
		ship_two_percentfreq_one=40;
		ship_two_percentfreq_two=20;
		ship_two_percentfreq_three=40;
		
		ship_three_percentfreq_one=40;
		ship_three_percentfreq_two=40;
		ship_three_percentfreq_three=20;
		
	}
	
	@Override
	
	void level_specific_bayesian_update(String dot_type, EnemyShip enemyship){
		if (dot_type.equals("destroy")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.4f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.8f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.3f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.3f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		normalize(enemyship);
	}
	
	@Override
	
	void level_specific_probability_display(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship.obscured){
				acalc_greenfont.draw(batch, "C: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nT: "+present_float(enemyship.assignedprob_two*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_three*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
			}
		}
	}
	
	@Override
	
	void level_specific_HUD(){
		font.draw(batch, "CIRC/TRI/PENT", 90, 473, 140, 1, true);
		font.draw(batch, "WAVE: "+shipwave+"/"+total_shipwaves, 90, 455, 140, 1, true);
		font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+ (shields+20), 90, 419, 140, 1, true);
	}
	
}