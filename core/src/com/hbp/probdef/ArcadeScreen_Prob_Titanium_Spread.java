package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Titanium_Spread extends ArcadeScreen_Prob_Titanium {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Titanium_Spread(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	    minecount=60;
	    
	    wave_number_total=6;
	    
	    ordinary_minetype="titanium";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("triangle");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		mixed_set(2);
		
		
		
		if (minecount==0){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
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