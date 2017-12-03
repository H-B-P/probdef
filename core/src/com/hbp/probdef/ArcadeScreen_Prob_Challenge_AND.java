package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Challenge_AND extends ArcadeScreen_Prob_Challenge {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Challenge_AND(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=30;
	    
	    wave_number_total=5;
	    
	    //if (CAMPAIGN){shields=30;}
	}
	
	void set_score_name(){
		score_name="Score_Challenge_AND";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon",2);
		   turret_two=new Turret_Standard("square", 3);
		   turret_three=new Turret_Standard("triangle",1);
		   turret_four=new Turret_Standard("circle",2);
		   
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
		
		challenge_AND_set(2);
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
			   purpletext=true;
			   show_the_text=true;
			   the_text="(you know, nobody said you couldn't have shields and titanium in the same mine)";
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}