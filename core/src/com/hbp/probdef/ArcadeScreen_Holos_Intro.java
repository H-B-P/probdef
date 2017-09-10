package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Holos_Intro extends ProbGameScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Holos_Intro(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		captured=0;
	    destroyed=0;
	      
	    minecount=40;
	    shields=5;
	    
	    score=0;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("square");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("square");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
		//The intro: 6 mines
		
		if (seconds==4){
			spawnHoloMine(0,200);
		}
		if (seconds==6){
			spawnHoloMine(0,95);
		}
		
		//Massed I: 12 mines
		
		if (seconds==26 || seconds==28 || seconds==30 || seconds==32){
			spawnHoloProbablistic(-2,45,40);
			spawnHoloProbablistic(0,45,40);
			spawnHoloProbablistic(2,45,40);
		}
		
		//Drizzle: 6 mines
		
		if (seconds==40){
			spawnHoloProbablistic(1,65,40);
		}
		if (seconds==42){
			spawnHoloProbablistic(-1,65,40);
			spawnHoloProbablistic(2,65,40);
		}
		if (seconds==44){
			spawnHoloProbablistic(3,65,40);
		}
		if (seconds==46){
			spawnHoloProbablistic(-1,65,40);
			spawnHoloProbablistic(-3,95,40);
		}
		
		//Slow-mo Tsunami:12 mines
		
		if (seconds==52|| seconds==56){
			spawnHoloProbablistic(-3,45,40);
			spawnHoloProbablistic(-1,45,40);
			spawnHoloProbablistic(1,45,40);
			spawnHoloProbablistic(3,45,40);
		}
		if (seconds==54|| seconds==58){
			spawnHoloProbablistic(-1,45,40);
			spawnHoloProbablistic(1,45,40);
		}
		
		//Faster-mo wall: 8 mines
		
		if (seconds==64 || seconds==68){
			spawnHoloProbablistic(-3,95,40);
			spawnHoloProbablistic(-1,65,40);
			spawnHoloProbablistic(1,65,40);
			spawnHoloProbablistic(3,45,40);
		}
		
		if (minecount==0){
			game.setScreen(new ArcadeSelectScreen(game, true));
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
				the_text="Holo mines look ordinary, but vanish harmlessly when they touch the ship's shield.";
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
				the_text="Eventually, a holo mine will defy enough odds that you can assume it's fake, and focus on real threats.";
				
			}
			if (total_time>18 && total_time<24){
				show_the_text=true;
				greentext=true;
				the_text="(from here on out all holos are generated randomly, so memorising which mines are fake won't work)";
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