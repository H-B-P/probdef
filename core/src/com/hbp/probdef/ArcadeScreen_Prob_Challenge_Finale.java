package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Challenge_Finale extends ArcadeScreen_Prob_Challenge {
	
	final ProbDef game;
	
	boolean succeeded_yet;
	float success_time;
	
	public ArcadeScreen_Prob_Challenge_Finale(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=100;
	    
	    wave_number_total=8;
	    
	    
	    
	    succeeded_yet=false;
	    
	    if (CAMPAIGN){
	    	//shields=40;
	    	timeouting=false;
	    }
	    
	    success_time=999999999999999999999999f;
	}
	
	void set_score_name(){
		score_name="Score_Challenge_Finale";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon",2);
		   turret_two=new Turret_Standard("triangle", 2);
		   turret_three=new Turret_Standard("square", 3);
		   turret_four=new Turret_Standard("circle",2);
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("eight_done",true);
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
	
	void level_specific_events(){
		
		if (CAMPAIGN){
			finale_set(2,2);
		}
		else{
			finale_set(2,3);
		}
		
	}
	
@Override
	
	public void render(float delta){
		probgame_render(delta);
		
		
		if (CAMPAIGN){
			if (!succeeded_yet && minecount==0 && explosions.size==0){
				succeeded_yet=true;
				System.out.println("WE DID IT");
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
				purplefont.draw(batch, "(or head over to the arcade and see how differently these levels play when you care how many mines you capture)", 30, 273, 260, 1, true);
			}
			
			if (total_time>success_time+14){
				batch.draw(textbox_one_t, 20, 100);
				purplefont.draw(batch, "(or replay the campaign if you want, or turn the computer off and go rock climbing,\nidk it's your life)", 30, 173, 260, 1, true);
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
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
			   if (CAMPAIGN){
				   show_the_text=true;
					the_text="This is the final level.\nGood luck.";
				}
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}