package com.hbp.probdef;

import com.hbp.probdef.ProbDef;
public class ArcadeScreen_Prob_Basic_Circles extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Basic_Circles(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		minecount=60;
		
		extra_mines=prefs.getInteger("one_captured");
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Basic_Circles";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("hexagon");
		   turret_four=new Turret_Standard("hexagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		basic_set(2);
		
	}
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (prefs.getInteger("two_captured")<captured){
				prefs.putInteger("two_captured",captured);
			}
			prefs.putBoolean("two_done",true);
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
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (seconds==4 && TIMESPEED==0){
			   show_the_text=true;
			   the_text="Circle turrets fire only 50% of the time, but when they do, they always capture.";
		   }
		   if (seconds==4 && TIMESPEED==0 && (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted)){
			   show_the_text=true;
			   the_text="They work well alongside hexagon turrets,\n which never fail.";
		   }
		   
		   if (seconds==6 && TIMESPEED==0 && CAMPAIGN){
			   show_the_text=true;
			   the_text="By the way: if you took damage last level, you may have noticed that you have ten shields again.";
			   if (turret_one.targeted){
				   the_text="This is because shields regenerate between levels. Conversely, the mines you collected stay with you.";
			   }
			   if (turret_two.targeted){
				   purpletext=true;
				   the_text="(so if you're finding this boringly easy so far, consider risking your shields more in order to collect more mines)";
			   }
			   if (turret_three.targeted || turret_four.targeted || infuriatingly_specific_bool){
				   purpletext=true;
				   the_text="(like, by not using turrets with high destroy rates and low capture rates when you don't need to)";
				   infuriatingly_specific_bool=true;
			   }
		   }
	}
	
	

	@Override
	public void dispose() {
		probgame_dispose();
	}
}