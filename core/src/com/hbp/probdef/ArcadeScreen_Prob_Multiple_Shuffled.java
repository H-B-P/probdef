package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Multiple_Shuffled extends ArcadeScreen_Prob_Multiple {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Multiple_Shuffled(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=50;
	    
	    wave_number_total=5;
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Multiple_Shuffled";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle",3);
		   turret_two=new Turret_Standard("circle", 2);
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("pentagon", 2);
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		shieldy_maincourse_set(2);
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