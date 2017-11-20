package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Titanium_Pure extends ArcadeScreen_Prob_Titanium {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Titanium_Pure(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=40;
	    
	    wave_number_total=6;
	    
	    ordinary_minetype="titanium";
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Titanium_Pure";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		titanium_set(2);
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}