package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Titanium_Intro extends ArcadeScreen_Prob_Titanium {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Titanium_Intro(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=60;
	    
	    wave_number_total=6;
	    
	    if (CAMPAIGN){
	    	shields=5+(1+prefs.getInteger("two_remain"))/2;
	    }
	    
	    //if (CAMPAIGN){shields=30;}
	    
	}
	
	
	@Override
	
	void set_score_name(){
		score_name="Score_Titanium_Intro";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon");
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("triangle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("three_done",true);
			if (shields>prefs.getInteger("three_remain")){
				prefs.putInteger("three_remain", shields);
			}
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
		   if (seconds<5 && TIMESPEED==0){
			   show_the_text=true;
			   the_text="Titanium mines cannot be destroyed; only captured.";
			   if (turret_one.targeted||turret_two.targeted || turret_three.targeted || turret_four.targeted){
				   the_text="This can lead to some interesting tradeoffs when deciding which mines to prioritise.";
			   }
		   }
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}