package com.hbp.probdef;



import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Prob_Basic_Reversed extends ArcadeScreen_Prob {
	
	final ProbDef game;

	
	public ArcadeScreen_Prob_Basic_Reversed(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
	      
	    minecount=60;
	    
		extra_mines=prefs.getInteger("one_captured")+prefs.getInteger("two_captured");
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Basic_Reversed";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("hexagon");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("circle");
		   
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
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (prefs.getInteger("three_captured")<captured){
				prefs.putInteger("three_captured",captured);
			}
			prefs.putBoolean("three_done",true);
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
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (seconds<5){
			   show_the_text=true;
			   the_text="Someone arranged these turrets so they fire in exactly the worst order.";
		   }
		   if (seconds<5 && infuriatingly_specific_bool){
			   show_the_text=true;
			   purpletext=true;
			   the_text="(whoever did that must be really careless and/or inconsiderate smh)";
		   }
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}