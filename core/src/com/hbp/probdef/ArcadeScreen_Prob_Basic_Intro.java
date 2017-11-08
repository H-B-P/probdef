package com.hbp.probdef;


import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Basic_Intro extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Basic_Intro(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	    minecount=60;
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Basic_Intro";
	}
	
	@Override
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (prefs.getInteger("one_captured")<captured){
				prefs.putInteger("one_captured",captured);
			}
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
		   purpletext=false;
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (seconds<5){
			   show_the_text=true;
			   if (CAMPAIGN){
				   the_text="You start with ten shields. If a mine hits, you lose four shields. If your shields hit zero, you fail the level.";
				   if (infuriatingly_specific_bool){
					   the_text="Try to end each level with as many captured mines as possible. You'll need them later.";
				   }
			   }
			   else{
				   the_text="Your score starts at twenty. If you capture a mine, you gain one point. If a mine hits, you lose four points.";
				   if (infuriatingly_specific_bool){
					   the_text="Try to end the level with the highest score you can.";
				   }
			   }
			   
		   }
		   if (seconds==18 && TIMESPEED==0){
			   show_the_text=true;
			   purpletext=true;
			   the_text="(protip: every wave consists of four rows of mines, and the last two rows are always a repeat of the first two)";
		   }
		   
		   
	}
	
	//void 

	@Override
	public void dispose() {
		probgame_dispose();
	}
}