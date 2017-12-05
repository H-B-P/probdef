package com.hbp.probdef;


import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Basic_Intro_Comb extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Basic_Intro_Comb(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=28;
	    
	    wave_number_total=3;
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Basic_Intro_Comb";
	}
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			prefs.putBoolean("one_done",true);
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
	
	void level_specific_turret_setup(){
		
			turret_one=new Turret_Standard("circle");
			   turret_two=new Turret_Standard("square");
			   turret_three=new Turret_Standard("triangle");
			   turret_four=new Turret_Standard("pentagon");
		
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
			basic_set_veryshort(2);
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (seconds==4 && TIMESPEED==0){
			   show_the_text=true;
			   if (CAMPAIGN){
				   the_text="You have five shields.\nIf a mine hits, you lose one shield. If you lose all your shields, you fail the level.";
				   if (turret_one.targeted){
					   the_text="The number of shields you start a level with is five plus half the shields you ended the previous level with.";
				   }
				   if (turret_one.targeted && turret_two.targeted){
					   purpletext=true;
					   the_text="(since there was no level before this one, that's five plus half of zero here)";
				   }
				   if (turret_one.targeted && turret_two.targeted && turret_three.targeted){
					   purpletext=false;
					   the_text="This implies that you should aim to end each level with as many shields as you can, to make later levels easier.";
				   }
				   if (turret_one.targeted && turret_two.targeted && turret_three.targeted && turret_four.targeted){
					   purpletext=true;
					   the_text="(and also for bragging rights)";
					   //the_text="(btw in this campaign there's no reason to prefer capturing mines, we just toss them out the airlock when we're done)";
				   }
			   }
			   else{
				   the_text="Your score starts at twenty. If you capture a mine, you gain one point. If a mine hits, you lose four points.";
				   if (infuriatingly_specific_bool){
					   the_text="Try to end the level with the highest score you can.";
				   }
			   }
			   
		   }
		   if (seconds==20 && TIMESPEED==0){
			   show_the_text=true;
			   purpletext=true;
			   the_text="(protip: every wave consists of four rows of mines, and the last two rows are always a repeat of the first two)";
		   }
		   if (seconds==36 && TIMESPEED==0){
			   show_the_text=true;
			   purpletext=true;
			   the_text="(one more thing: you're given this many shields for a reason)";
			   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
				   the_text="(don't expect to finish a level with all of them intact)";
			   }
		   }
		   
		   
	}
	@Override
	public void dispose() {
		probgame_dispose();
	}
}