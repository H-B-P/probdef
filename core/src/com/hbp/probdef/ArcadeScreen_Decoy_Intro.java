package com.hbp.probdef;


import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Decoy_Intro extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	public ArcadeScreen_Decoy_Intro(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;

	    
	    decoyfreq=40;
	    
	    ordinary_minetype="decoy";
	    
	    minecount=42;
	    
	    wave_number_total=4;
	}
	
	void set_score_name(){
		score_name="Score_Decoy_Intro";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
		//The intro
		
		if (seconds==4){
			spawnDecoyMine(0,200);
		}
		if (seconds==6){
			spawnDecoyMine(0,95);
		}
		
		//and then . . .
		
		decoy_set(10);
		
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
		   if (total_time<5){
				show_the_text=true;
				the_text="Decoy mines look ordinary, but vanish harmlessly when they touch the ship's shield.";
			}
			if ((total_time>6 && total_time<8) || (seconds==8 && TIMESPEED==0)){
				show_the_text=true;
				the_text="They don't exist, so turrets won't successfully fire on them. Every shot you try fails.";
				
			}
			if (seconds==8 && (turret_one.targeted || turret_two.targeted || turret_three.targeted || turret_four.targeted)){
				show_the_text=true;
				the_text="The autocalculator assumes every mine is real and calculates odds based on that.";
			}
			
			
			if (seconds>10 && seconds<13){
				show_the_text=true;
				if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
					   infuriatingly_specific_bool=true;
				}
				
				if (!infuriatingly_specific_bool){
					the_text="Eventually, a decoy will defy enough odds that you can assume it's fake, and focus on real threats.";
				}
				else{
					purpletext=true;
					the_text="(from here on out all decoys are generated randomly, so memorising which mines are fake won't work)";
				}
			}		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}