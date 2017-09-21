package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Decoy_Groups extends GameScreen_Prob {
	
	final ProbDef game;
	
	private int decoyfreq;
	
	public ArcadeScreen_Decoy_Groups(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
	      
	    minecount=52;
	    shields=5;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
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
		
				//Visually and mathematically obvious gentle intro:10
		
				if (seconds==10 || seconds==14){ 
					spawn_two_group(65);
				}
				
				if (seconds==12 || seconds==16){
					spawn_three_group(65);
				}
				
				//Gentle seige:14
				
				if (seconds==24 || seconds==28){ 
					spawn_four_group(45);
				}
				
				if (seconds==26 || seconds==30){
					spawn_three_group(45);
				}
				
				//tower II:12
				
				if (seconds==38 || seconds==42 ){
					spawn_two_group(65);
				}
				
				if (seconds==40 || seconds==44 ){
					spawn_four_group(65);
				}
				
				//seige II
				
				if (seconds==52 || seconds==56 ){
					spawn_four_group(45);
				}
				
				if (seconds==54 || seconds==58){
					spawn_four_group(45);
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
	
	
	void spawn_two_group(int spd){
		int q=MathUtils.random(1,2);
		if (q==1){
			spawnDecoyMine(-1, spd);
			spawnMine(1, spd);
		}
		else if (q==2){
			spawnMine(-1, spd);
			spawnDecoyMine(1, spd);
		}
	}
	
	void spawn_three_group(int spd){
		int q=MathUtils.random(1,3);
		if (q==1){
			spawnDecoyMine(-2, spd);
			spawnMine(0, spd);
			spawnMine(2, spd);
		}
		else if (q==2){
			spawnMine(-2, spd);
			spawnDecoyMine(0, spd);
			spawnMine(2, spd);
		}
		else if (q==3){
			spawnMine(-2, spd);
			spawnMine(0, spd);
			spawnDecoyMine(2, spd);
		}
	}

	void spawn_four_group(int spd){
		int q=MathUtils.random(1,4);
		if (q==1){
			spawnDecoyMine(-3, spd);
			spawnMine(-1, spd);
			spawnMine(1, spd);
			spawnMine(3, spd);
		}
		else if (q==2){
			spawnMine(-3, spd);
			spawnDecoyMine(-1, spd);
			spawnMine(1, spd);
			spawnMine(3, spd);
		}
		else if (q==3){
			spawnMine(-3, spd);
			spawnMine(-1, spd);
			spawnDecoyMine(1, spd);
			spawnMine(3, spd);
		}
		else if (q==4){
			spawnMine(-3, spd);
			spawnMine(-1, spd);
			spawnMine(1, spd);
			spawnDecoyMine(3, spd);
		}
	}
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (total_time<7){
				show_the_text=true;
				the_text="In this level, every row of mines has exactly one decoy. Knowing this makes investigation easier.";
			}
		   if (total_time>7 && total_time<12){
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