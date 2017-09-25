package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Basic_Capture extends ArcadeScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Capture(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	    minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("circle");
		   turret_four=new Turret_Standard("square");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		basic_set(10);
		
		
		
		if (minecount==0){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (seconds<10){
			   show_the_text=true;
			   the_text="In this level, mines hitting your ship won't lower your score. All that matters is how many you capture.";
		   }
		   if (seconds>=10 && seconds<13){
			   show_the_text=true;
			   the_text="Try to end the level with the highest score you can.";
		   }
		   
		   
	}
	
	@Override
	void calculate_score(){
		score=captured;
		score=Math.max(score, 0);
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}