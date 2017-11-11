package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes_Deduction_Trio extends ArcadeScreen_Bayes {
	
	final ProbDef game;
	
	
	boolean succeeded_yet;
	float success_time;
	
	public ArcadeScreen_Bayes_Deduction_Trio(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
		if (CAMPAIGN){
			minecount=prefs.getInteger("one_captured")+prefs.getInteger("two_captured")+prefs.getInteger("four_captured")+prefs.getInteger("five_captured")-prefs.getInteger("seven_spent");
			minecount=Math.max(minecount, 0);
		}
		else{
			minecount=40;
		}
		
		original_minecount=minecount;
		
		if (CAMPAIGN){
			timeouting=false;
		}
		succeeded_yet=false;
		
		success_time=0;
		success_time=90000000000000000000000000000000f;
		
	}
	
	void set_score_name(){
		score_name="Score_Deduction_Finale";
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		purpletext=false;
		
		if (shipwave==1){
			if (round==1 && current_status.equals("targeting")){
				show_the_text=true;
				the_text="Sometimes, evidence can be definitive; other times, it cannot.";
				if (minecount<15){
					purpletext=true;
					the_text="(hey jsyk you should probably replay some earlier levels and get a bigger stash of mines before trying this one)";
				}
				else if (CAMPAIGN){
					the_text="This is the final level of the campaign. Good luck.";
				}
				if (vane_one.targeted||vane_two.targeted){
					the_text="Ships in this area have circle, triangle and pentagon turrets.";
					if (minecount<15){
						purpletext=true;
						the_text="(or you can keep going if you want just don't say I didn't warn you)";
					}
					else if (CAMPAIGN){
						purpletext=true;
						the_text="(hope you brought enough mines!)";
					}
				}
				if (vane_one.targeted && vane_two.targeted){
					the_text="A destroyshot can prove a turret isn't a circle, but no behavior reliably seperates pentagons from triangles.";
					if (minecount<15){
						purpletext=true;
						the_text="(or you can keep going if you want just don't say I didn't warn you)";
					}
					else if (CAMPAIGN){
						purpletext=true;
						the_text="(hope you brought enough mines!)";
					}
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
	
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (!prefs.getBoolean("nine_done")){
				prefs.putBoolean("nine_done",true);
				prefs.putInteger("nine_spent", (original_minecount-minecount));
			}
			else if (prefs.getInteger("nine_spent")>(original_minecount-minecount)){
				prefs.putInteger("nine_spent", (original_minecount-minecount));
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
		if (CAMPAIGN){
			font.draw(batch, "CIRC/TRI/PENT", 90, 473, 140, 1, true);
			font.draw(batch, "WAVE: "+Math.min(shipwave, total_shipwaves)+"/"+total_shipwaves, 90, 455, 140, 1, true);
			font.draw(batch, "SHIELDS: "+shields, 90, 437, 140, 1, true);
			font.draw(batch, "MINES: "+ minecount, 90, 419, 140, 1, true);
		}
		else{
			font.draw(batch, "CIRC/TRI/PENT", 90, 473, 140, 1, true);
			font.draw(batch, "WAVE: "+Math.min(shipwave, total_shipwaves)+"/"+total_shipwaves, 90, 455, 140, 1, true);
			font.draw(batch, "MINES: "+minecount, 90, 437, 140, 1, true);
			font.draw(batch, "SCORE: "+ score, 90, 419, 140, 1, true);
		}
	}
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN C55", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);

	}
	
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN C56", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN C57", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	
	public void render(float delta){
		bayesgame_render(delta);
		
		if (CAMPAIGN){
			if (!succeeded_yet && shipwave>=total_shipwaves && enemyships.size==0 && explosions.size==0){
				succeeded_yet=true;
				success_time=total_time;
				update_score_on_exit();
			}
			
			batch.begin();
			
			if (total_time>success_time+2){
				batch.draw(textbox_one_t, 20, 300);
				blackfont.draw(batch, "Congratulations, you finished the Campaign! Now, look through the Library to learn the math behind what you did.", 30, 373, 260, 1, true);
			}
			
			if (total_time>success_time+8){
				batch.draw(textbox_one_t, 20, 200);
				purplefont.draw(batch, "(or head over to the arcade and check out the levels that were too weird to be part of the campaign)", 30, 273, 260, 1, true);
			}
			
			if (total_time>success_time+14){
				batch.draw(textbox_one_t, 20, 100);
				purplefont.draw(batch, "(or replay the campaign if you want, or turn the computer off and go for a walk, idk it's your life)", 30, 173, 260, 1, true);
			}
			
			if (total_time>success_time+20){
				if (option_flicker){
					if ((seconds/2)%2==1){
						batch.draw(green_button_trim_t, menu_button_r.x, menu_button_r.y);
					}
				}
				else{
						batch.draw(green_button_trim_t, menu_button_r.x, menu_button_r.y);
				}
			}
			
			batch.end();
		}
		
	}
}