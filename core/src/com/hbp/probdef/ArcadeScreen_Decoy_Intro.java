package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Decoy_Intro extends ArcadeScreen {
	
	final ProbDef game;
	
	public ArcadeScreen_Decoy_Intro(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;

	    
	    decoyfreq=40;
	    
	    ordinary_minetype="decoy";
	    
	    minecount=42;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
		//The intro
		
		if (seconds==4){
			spawnDecoyMine(0,200);
		}
		if (seconds==6){
			spawnDecoyMine(0,95);
		}
//		
//		//Massed I: 12 mines
//		
//		if (seconds==26 || seconds==28 || seconds==30 || seconds==32){
//			spawnDecoyProbablistic(-2,45,40);
//			spawnDecoyProbablistic(0,45,40);
//			spawnDecoyProbablistic(2,45,40);
//		}
//		
//		//Drizzle: 6 mines
//		
//		if (seconds==40){
//			spawnDecoyProbablistic(1,65,40);
//		}
//		if (seconds==42){
//			spawnDecoyProbablistic(-1,65,40);
//			spawnDecoyProbablistic(2,65,40);
//		}
//		if (seconds==44){
//			spawnDecoyProbablistic(3,65,40);
//		}
//		if (seconds==46){
//			spawnDecoyProbablistic(-1,65,40);
//			spawnDecoyProbablistic(-3,95,40);
//		}
//		
//		//Slow-mo Tsunami:12 mines
//		
//		if (seconds==52|| seconds==56){
//			spawnDecoyProbablistic(-3,45,40);
//			spawnDecoyProbablistic(-1,45,40);
//			spawnDecoyProbablistic(1,45,40);
//			spawnDecoyProbablistic(3,45,40);
//		}
//		if (seconds==54|| seconds==58){
//			spawnDecoyProbablistic(-1,45,40);
//			spawnDecoyProbablistic(1,45,40);
//		}
//		
//		//Faster-mo wall: 8 mines
//		
//		if (seconds==64 || seconds==68){
//			spawnDecoyProbablistic(-3,95,40);
//			spawnDecoyProbablistic(-1,65,40);
//			spawnDecoyProbablistic(1,65,40);
//			spawnDecoyProbablistic(3,45,40);
//		}
		
		
		
		//nice starting phalanx: 12 mines
		
//				if (seconds==20 || seconds==22 ||seconds==24 || seconds==26){
//					spawnDecoyProbablistic(-2,45,decoyfreq);
//					spawnDecoyProbablistic(0,45,decoyfreq);
//					spawnDecoyProbablistic(2,45,decoyfreq);
//				}
//				
//				//faster tower: 8 mines
//				
//				if (seconds==32 || seconds==34 || seconds==36 || seconds==38){
//					spawnDecoyProbablistic(-2,65,decoyfreq);
//					spawnDecoyProbablistic(2,65,decoyfreq);
//				}
//				
//				//actually let's just redo this ad infi: 20 mines.
//				
//				if (seconds==46 || seconds==48 ||seconds==50 || seconds==52){
//					spawnDecoyProbablistic(-2,45,decoyfreq);
//					spawnDecoyProbablistic(0,45,decoyfreq);
//					spawnDecoyProbablistic(2,45,decoyfreq);
//				}
//				
//				if (seconds==58 || seconds==60 || seconds==62 || seconds==64){
//					spawnDecoyProbablistic(-2,65,decoyfreq);
//					spawnDecoyProbablistic(2,65,decoyfreq);
//				}
//		
		
		decoy_set(20);
		
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
		   if (total_time<4){
				show_the_text=true;
				the_text="Decoy mines look ordinary, but vanish harmlessly when they touch the ship's shield.";
			}
			if ((total_time>6 && total_time<8) || (seconds==8 && TIMESPEED==0)){
				show_the_text=true;
				the_text="They don't exist, so turrets won't successfully fire on them. Every shot you try fails.";
				
			}
			if (seconds==8 && (turret_one.targeted || turret_two.targeted || turret_three.targeted || turret_four.targeted)){
				show_the_text=true;
				the_text="The autocalculator assumes every mine is real and calculates odds based on that.";
			}
			if (total_time>12 && total_time<18){
				show_the_text=true;
				the_text="Eventually, a decoy will defy enough odds that you can assume it's fake, and focus on real threats.";
				
			}
			if (total_time>18 && total_time<24){
				show_the_text=true;
				greentext=true;
				the_text="(from here on out all decoys are generated randomly, so memorising which mines are fake won't work)";
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