package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob_Decoy_Minority extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	public ArcadeScreen_Prob_Decoy_Minority(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
	      
	    minecount=50;
	    
	    score=0;
	    decoyfreq=20;
	    
	    wave_number_total=5;
	    ordinary_minetype="decoy";
	}
	
	@Override
	
	void set_score_name(){
		score_name="Score_Decoy_Minority";
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("pentagon");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		decoy_set(2);
		
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (total_time<5){
				show_the_text=true;
				the_text="In this level, decoys are rare.";
			}
		   if (total_time<5 && infuriatingly_specific_bool){
				show_the_text=true;
				the_text="This means you have fewer of them to deal with, but also means you must use higher standards of proof.";
			}
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}