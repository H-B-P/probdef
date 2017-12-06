package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Multiple_Multishot extends ArcadeScreen_Prob_Multiple {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Multiple_Multishot(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=40;
	    
	    wave_number_total=4;
	    
	    if (CAMPAIGN){
	    	shields=5+(1+prefs.getInteger("three_remain"))/2;
	    }
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Multiple_Multishot";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   
			turret_one=new Turret_Standard("square", 4);
		   turret_two=new Turret_Standard("square", 2);
		   turret_three=new Turret_Standard("square", 1);
		   turret_four=new Turret_Standard("square", 1);
		
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("four_done",true);
			if (shields>prefs.getInteger("four_remain")){
				prefs.putInteger("four_remain", shields);
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
		
		square_multishot_set(2);
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds<5){
				show_the_text=true;
				the_text="Multishot turrets fire several shots per volley. They're good for handling shielded mines.";
			}
			if (seconds==6 && TIMESPEED==0){
				show_the_text=true;
				the_text="The string of shots fired by a multishot turret are all independent.";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="This means a 2-shot square turret is equivalent to two 1-shot square turrets targeting the same mine.";
			}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}