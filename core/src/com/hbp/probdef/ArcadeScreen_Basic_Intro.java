package com.hbp.probdef;


import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Basic_Intro extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Intro(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	    minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("triangle");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		basic_set(2);
		
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
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (seconds<5){
			   show_the_text=true;
			   the_text="Your score starts at twenty. If you capture a mine, you gain one point. If a mine hits, you lose four points.";
			   if (infuriatingly_specific_bool){
				   the_text="Try to end the level with the highest score you can.";
			   }
		   }
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}