package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Titanium_Tradeoff extends ArcadeScreen_Prob_Titanium {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Titanium_Tradeoff(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=60;
	    
	    wave_number_total=6;
	    
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("pentagon");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("triangle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		if (CAMPAIGN){
			mixed_set(2,2);
		}
		else{
			mixed_set(2,3);
		}
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
			   show_the_text=true;
			   the_text="Choices in this level are not as clear-cut as before. Choose wisely.";
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}