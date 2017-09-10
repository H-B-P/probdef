package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Holos_Majority extends ProbGameScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Holos_Majority(final ProbDef gam, boolean play_the_sound) {
		
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
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		//nice starting phalanx: 12 mines
		
		if (seconds==10 || seconds==12 ||seconds==14 || seconds==16){
			spawnHoloProbablistic(-2,45,80);
			spawnHoloProbablistic(0,45,80);
			spawnHoloProbablistic(2,45,80);
		}
		
		//faster tower: 8 mines
		
		if (seconds==22 || seconds==24 ||seconds==26 || seconds==28){
			spawnHoloProbablistic(-1,65,80);
			spawnHoloProbablistic(1,65,80);
		}
		
		//actually let's just redo this ad infi: 20 mines.
		
		if (seconds==36 || seconds==38 ||seconds==40 || seconds==42){
			spawnHoloProbablistic(-2,45,80);
			spawnHoloProbablistic(0,45,80);
			spawnHoloProbablistic(2,45,80);
		}
		
		if (seconds==48 || seconds==50 ||seconds==52 || seconds==54){
			spawnHoloProbablistic(-1,65,80);
			spawnHoloProbablistic(1,65,80);
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
				the_text="In this level, holos are very common.";
			}
		   if (total_time>4 && total_time<10){
				show_the_text=true;
				the_text="This means you have more of them to deal with, but also means you can use lower standards of proof.";
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