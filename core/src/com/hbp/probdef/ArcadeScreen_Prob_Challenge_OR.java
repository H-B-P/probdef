package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Challenge_OR extends ArcadeScreen_Prob_Challenge {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Challenge_OR(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=70;
	    
	    wave_number_total=7;
	    
	    if (CAMPAIGN){shields=25;}
	}
	
	void set_score_name(){
		score_name="Score_Challenge_OR";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle",3);
		   turret_two=new Turret_Standard("pentagon", 2);
		   turret_three=new Turret_Standard("circle",2);
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("seven_done",true);
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
			challenge_OR_set(2, false);
		}
		else{
			challenge_OR_set(2, true);
		}
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}