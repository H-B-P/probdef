package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob_Decoy_PerfectTest extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	private int decoyfreq;
	
	public ArcadeScreen_Prob_Decoy_PerfectTest(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
		captured=0;
	    destroyed=0;
	      
	    minecount=50;
	    
	    score=0;
	    decoyfreq=40;
	    
	    wave_number_total=4;
	    
		extra_mines=prefs.getInteger("one_captured")+prefs.getInteger("two_captured")+prefs.getInteger("three_captured")+prefs.getInteger("four_captured");
	}
	
	void set_score_name(){
		score_name="Score_Decoy_PerfectTest";
	}
	
	@Override
	
	void update_score_on_exit(){
		if (CAMPAIGN){
			if (prefs.getInteger("five_captured")<captured){
				prefs.putInteger("five_captured",captured);
			}
			prefs.putBoolean("five_done",true);
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
		   turret_one=new Turret_Standard("square");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("hexagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
		wave_number_update(1,1);
		
		if (seconds==2 || seconds==4 ||seconds==6 || seconds==8){
			
			
			spawnDecoyProbablistic(-1,65,decoyfreq);
			spawnDecoyProbablistic(1,95,decoyfreq);
		}
		
		wave_number_update(13,2);
		
		
		if (seconds==16 || seconds==18 || seconds==20 || seconds==14){
			spawnDecoyProbablistic(-2,65,decoyfreq);
			spawnDecoyProbablistic(0,65,decoyfreq);
			spawnDecoyProbablistic(2,65,decoyfreq);
		}
		
		wave_number_update(27,3);
		
		if (seconds==30 || seconds==32 ||seconds==34 || seconds==28){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(-1,45,decoyfreq);
			spawnDecoyProbablistic(1,45,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
		}
		
		wave_number_update(41,4);
		
		
		if (seconds==42 || seconds==46 ){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(-1,65,decoyfreq);
			spawnDecoyProbablistic(1,65,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
		}
		
		if (seconds==44 || seconds==48){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(0,65,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
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
		   if (total_time<5){
				show_the_text=true;
				the_text="Having a perfect test,\nlike a hexagon turret,\nmakes hunting for decoys much easier.";
			}
		   if (total_time<5 && infuriatingly_specific_bool){
				show_the_text=true;
				purpletext=true;
				the_text="(hexagons always work on real mines, so if a hex fails, the mine that made it fail has to be a decoy)";
				//the_text="To compensate for this advantage, this level has far higher mine density. Good luck!";
			}
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}