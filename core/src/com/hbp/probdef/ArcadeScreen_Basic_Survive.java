package com.hbp.probdef;


import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Basic_Survive extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Survive(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	    minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon");
		   turret_two=new Turret_Standard("triangle");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("triangle");
		   
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
		   purpletext=false;
		   if (seconds<5){
			   show_the_text=true;
			   the_text="In this level, capturing mines won't raise your score. Just avoid mines hitting the ship.";
		   }
		   
		   
	}
	
	@Override
	void calculate_score(){
		score=shields*4+20;
		score=Math.max(score, 0);
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}