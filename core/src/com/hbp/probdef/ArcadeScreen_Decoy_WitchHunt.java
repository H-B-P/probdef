package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Decoy_WitchHunt extends GameScreen_Prob {
	
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
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
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
		
				if (seconds==10 || seconds==12 ||seconds==14 || seconds==16){
					spawnDecoyProbablistic(-3,45,decoyfreq);
					spawnDecoyProbablistic(-1,45,decoyfreq);
					spawnDecoyProbablistic(1,45,decoyfreq);
					spawnDecoyProbablistic(3,45,decoyfreq);
				}
				
				
				
				
				if (seconds==24 || seconds==26 || seconds==28 || seconds==30){
					spawnDecoyProbablistic(-2,65,decoyfreq);
					spawnDecoyProbablistic(0,65,decoyfreq);
					spawnDecoyProbablistic(2,65,decoyfreq);
				}
				
				
				
				
				if (seconds==38 || seconds==40 ||seconds==42 || seconds==44){
					spawnDecoyProbablistic(-1,65,decoyfreq);
					spawnDecoyProbablistic(1,95,decoyfreq);
				}
				
				
				
				
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
	void level_specific_HUD(){
		font.draw(batch, "MINES: "+minecount, 90, 472, 140, 1, true);
		font.draw(batch, "CAPTURED: "+captured, 90, 455, 140, 1, true);
		font.draw(batch, "DESTROYED: "+destroyed, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+score, 90, 420, 140, 1, true);   
	   }
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (total_time<6){
				show_the_text=true;
				the_text="Having a perfect test, like a hexagon turret, makes scientific investigation much easier.";
			}
		   if (total_time>6 && total_time<12){
				show_the_text=true;
				the_text="To compensate for this advantage, this level has far higher mine density. Good luck.";
			}
		   
		   
	}
	
	@Override
	void calculate_score(){
		score=captured+shields*2;
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}