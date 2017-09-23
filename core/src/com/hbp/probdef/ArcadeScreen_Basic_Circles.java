package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Basic_Circles extends ArcadeScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Circles(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle");
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("hexagon");
		   turret_four=new Turret_Standard("hexagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
//		//Stacatto: 8 mines.
//		if (seconds==10){
//			spawnMine(-2,95);
//			spawnMine(0,65);
//		}
//		if (seconds==12){
//			spawnMine(0,65);
//			spawnMine(2,95);
//		}
//		if (seconds==14){
//			spawnMine(-2,95);
//			spawnMine(0,65);
//		}
//		if (seconds==16){
//			spawnMine(0,65);
//			spawnMine(2,95);
//			
//		}
//		
//		//Slow, massed: 14 mines
//		
//		if (seconds==22 || seconds==26){
//			spawnMine(-3,45);
//			spawnMine(-1,45);
//			spawnMine(1,45);
//			spawnMine(3,45);
//			
//		}
//		
//		if (seconds==24 || seconds==28){
//			spawnMine(-2,45);
//			spawnMine(0,45);
//			spawnMine(2,45);
//		}
//		
//		//Stacattoesque: 8 mines
//		
//		if (seconds==36){
//			spawnMine(-2,65);
//			spawnMine(0,65);
//			spawnMine(2,65);
//		}
//		if (seconds==38){
//			spawnMine(0,65);
//		}
//		if (seconds==40){
//			spawnMine(-3,65);
//			spawnMine(3,65);
//			
//		}
//		if (seconds==42){
//			spawnMine(-1, 95);
//			spawnMine(1, 95);
//		}
//		
//		//Make the tactics obvious with some singles: 4 mines
//		
//		if (seconds==48){
//			spawnMine(-1,65);
//		}
//		if (seconds==50){
//			spawnMine(3,65);
//		}
//		if (seconds==52){
//			spawnMine(-3,65);
//		}
//		if (seconds==54){
//			spawnMine(1,65);
//		}
//		
//		//Drive it home: 4 mines
//		
//		if (seconds==58){
//			spawnMine(-1,65);
//		}
//		if (seconds==60){
//			spawnMine(3,95);
//		}
//		if (seconds==62){
//			spawnMine(-3,65);
//		}
//		if (seconds==64){
//			spawnMine(1,95);
//		}
//		
//		//Final choice: 2 mines
//		
//		if (seconds==70){
//			spawnMine(-2,95);
//			spawnMine(2,95);
//		}
		
		basic_set(10);
		
		if (minecount==0){
			game.setScreen(new SelectScreen_Arcade(game, true));
			  dispose();
		}
		
		
	}
	@Override
	void level_specific_HUD(){
		font.draw(batch, "MINES: "+minecount, 90, 471, 140, 1, true);
		font.draw(batch, "CAPTURED: "+captured, 90, 454, 140, 1, true);
		font.draw(batch, "DESTROYED: "+ destroyed, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+score, 90, 420, 140, 1, true);   
	   }
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (seconds>=0 && seconds<5){
			   show_the_text=true;
			   the_text="Circle turrets fire only 50% of the time, but when they do, they always capture.";
		   }
		   if (seconds>=5 && seconds<10){
			   show_the_text=true;
			   the_text="They work well alongside hexagon turrets,\n which never fail.";
		   }
		   
		   
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}