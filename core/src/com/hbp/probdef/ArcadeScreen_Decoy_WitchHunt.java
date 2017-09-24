package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Decoy_WitchHunt extends ArcadeScreen {
	
	final ProbDef game;
	
	private int decoyfreq;
	
	public ArcadeScreen_Decoy_WitchHunt(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		captured=0;
	    destroyed=0;
	      
	    minecount=50;
	    shields=5;
	    
	    score=0;
	    decoyfreq=40;
	    
	    wave_number_total=4;
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
		
		//nice starting phalanx: 16 mines
		
		wave_number_update(9,1);
		
		if (seconds==10 || seconds==12 ||seconds==14 || seconds==16){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(-1,45,decoyfreq);
			spawnDecoyProbablistic(1,45,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
		}
		
		wave_number_update(23,2);
		
		
		if (seconds==24 || seconds==26 || seconds==28 || seconds==30){
			spawnDecoyProbablistic(-2,65,decoyfreq);
			spawnDecoyProbablistic(0,65,decoyfreq);
			spawnDecoyProbablistic(2,65,decoyfreq);
		}
		
		wave_number_update(37,3);
		
		if (seconds==38 || seconds==40 ||seconds==42 || seconds==44){
			spawnDecoyProbablistic(-1,65,decoyfreq);
			spawnDecoyProbablistic(1,95,decoyfreq);
		}
		
		wave_number_update(49,4);
		
		
		if (seconds==50 || seconds==54 ){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(-1,65,decoyfreq);
			spawnDecoyProbablistic(1,65,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
		}
		
		if (seconds==52 || seconds==56){
			spawnDecoyProbablistic(-3,45,decoyfreq);
			spawnDecoyProbablistic(0,65,decoyfreq);
			spawnDecoyProbablistic(3,45,decoyfreq);
		}
		
		if (minecount==0){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
		
		
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (total_time<10){
				show_the_text=true;
				the_text="Having a perfect test,\nlike a hexagon turret,\nmakes scientific investigation much easier.";
			}
		   if (total_time>10 && total_time<13){
				show_the_text=true;
				the_text="To compensate for this advantage, this level has far higher mine density. Good luck!";
			}
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}