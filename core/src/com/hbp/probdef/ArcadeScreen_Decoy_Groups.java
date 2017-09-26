package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Decoy_Groups extends ArcadeScreen {
	
	final ProbDef game;
	
	public ArcadeScreen_Decoy_Groups(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
	      
	    minecount=52;
	    shields=5;
	    wave_number_total=4;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		//Visually and mathematically obvious gentle towery intro:10
		
		wave_number_update(1,1);
		
		if (seconds==2 || seconds==6){ 
			spawn_two_group(65);
		}
		
		if (seconds==4 || seconds==8){
			spawn_three_group(65);
		}
		
		//Gentle seige:14
		
		wave_number_update(15,2);
		
		if (seconds==16 || seconds==20){ 
			spawn_four_group(45);
		}
		
		if (seconds==18 || seconds==22){
			spawn_three_group(45);
		}
		
		//tower II:12
		
		wave_number_update(29,3);
		
		if (seconds==30 || seconds==34 ){
			spawn_two_group(65);
		}
		
		if (seconds==32 || seconds==36 ){
			spawn_four_group(65);
		}
		
		//seige II
		
		wave_number_update(43,4);
		
		if (seconds==44 || seconds==48 ){
			spawn_four_group(45);
		}
		
		if (seconds==46 || seconds==50){
			spawn_four_group(45);
		}
		
		if (minecount==0){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
		
		
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
		   if (turret_one.targeted||turret_two.targeted||turret_three.targeted||turret_four.targeted){
			   infuriatingly_specific_bool=true;
		   }
		   if (total_time<5){
				show_the_text=true;
				the_text="In this level, every row of mines has exactly one decoy. Knowing this makes investigation easier.";
			}
		   if (total_time<5 && infuriatingly_specific_bool){
				show_the_text=true;
				the_text="To compensate for this advantage, this level has far higher mine density. Good luck!";
			}
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}