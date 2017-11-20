package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Multiple_Shields extends ArcadeScreen_Prob_Multiple {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Multiple_Shields(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=30;
	    
	    wave_number_total=5;
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Multiple_Shields";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon");
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("square");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("four_done",true);
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
			shieldy_intro_set(2, false);
		}
		else{
			shieldy_intro_set(2, true);
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
			   show_the_text=true;
			   the_text="Some mines have shields.\nHitting a shield layer with either a capture or a destroy attack will remove it.";
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}