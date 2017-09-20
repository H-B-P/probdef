package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Basic_Reversed extends ArcadeScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Reversed(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
	      
	    minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("hexagon");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("triangle");
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		
//		//Laboring points:4 mines
//		
//		if (seconds==10){
//			spawnMine(1,65);
//		}
//		if (seconds==12){
//			spawnMine(3,95);
//		}
//		if (seconds==14){
//			spawnMine(1,65);
//		}
//		if (seconds==16){
//			spawnMine(3,95);
//		}
//		
//		//Slow marathon I: 10 mines
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
//			spawnMine(0,45);
//		}
//		
//		//Slow marathon II: 10 mines
//		
//		if (seconds==36){
//			spawnMine(-3,45);
//			spawnMine(-1,45);
//			spawnMine(1,45);
//			spawnMine(3,45);
//		}
//		if (seconds==38){
//			spawnMine(-2,45);
//			spawnMine(0,45);
//			spawnMine(2,45);
//		}
//		if (seconds==40){
//			spawnMine(-1,45);
//			spawnMine(1,45);
//		}
//		if (seconds==42){
//			spawnMine(0, 45);
//		}
//		
//		//Intentionally awkward finale
//		
//		if (seconds==52){
//			spawnMine(-3,65);
//			spawnMine(3,65);
//		}
//		if (seconds==54){
//			spawnMine(0,65);
//		}
//		if (seconds==56){
//			spawnMine(-3,65);
//			spawnMine(3,65);
//		}
//		if (seconds==58){
//			spawnMine(0,65);
//		}
		
		basic_set(10);
		
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
		   if (seconds>=0 && seconds<5){
			   show_the_text=true;
			   the_text="Someone arranged these turrets so they fire in exactly the worst order.";
		   }
		   if (seconds>=5 && seconds<10){
			   show_the_text=true;
			   greentext=true;
			   the_text="(whoever did that must be really careless and/or inconsiderate smh)";
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