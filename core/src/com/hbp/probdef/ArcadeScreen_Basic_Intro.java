package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Basic_Intro extends ProbGameScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Intro(final ProbDef gam, boolean play_the_sound) {
		
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
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("triangle");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		//Interesting intro: 11 mines.
		if (seconds==10){
			spawnMine(-3,45);
			spawnMine(-1,45);
			spawnMine(1,65);
			spawnMine(3,95);
		}
		if (seconds==12){
			spawnMine(1,65);
			spawnMine(3,65);
		}
		if (seconds==14){
			spawnMine(-3,65);
			spawnMine(-1,65);
		}
		
		if (seconds==16){
			spawnMine(-3,65);
			spawnMine(-1,65);
			spawnMine(3,95);
			
		}

		//Alternating attacks: 12 mines
		
		if (seconds==22){
			spawnMine(-3,65);
			spawnMine(-1,65);
			spawnMine(2,95);
			
		}
		if (seconds==24){
			spawnMine(-3,65);
			spawnMine(-1,65);
			spawnMine(3,95);
			
		}
		if (seconds==26){
			spawnMine(-3,65);
			spawnMine(-1,65);
			spawnMine(2,95);
			
		}
		if (seconds==28){
			spawnMine(-3,65);
			spawnMine(-1,65);
			spawnMine(3,95);
			
		}
		
		//Different alternating pattern: 16 mines
		
		if (seconds==34){
			spawnMine(-3,65);
			spawnMine(-1,45);
			spawnMine(1,45);
			spawnMine(3,65);
		}
		
		if (seconds==36){
			spawnMine(-3,65);
			spawnMine(-1,45);
			spawnMine(1,45);
		}
		
		if (seconds==38){
			spawnMine(-3,65);
			spawnMine(-1,45);
			spawnMine(1,45);
			spawnMine(3,65);
			
		}
		
		if (seconds==40){
			spawnMine(-3,65);
			spawnMine(-1,45);
			spawnMine(1,45);
		}
		
		//One last trio, to make a nice round 40
		
		if (seconds==46){
			spawnMine(-2,95);
			spawnMine(2,95);
		}
		if (seconds==48){
			spawnMine(0,65);
		}
		
		if (minecount==0){
			game.setScreen(new TitleScreen(game, true));
			  dispose();
		}
	}
	@Override
	void level_specific_HUD(){
		font.draw(batch, "MINES: "+minecount, 90, 472, 140, 1, true);
		font.draw(batch, "CAPTURED: "+captured, 90, 455, 140, 1, true);
		font.draw(batch, "SHIELDS: "+ shields, 90, 437, 140, 1, true);
		font.draw(batch, "SCORE: "+score, 90, 420, 140, 1, true);   
	   }
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   greentext=false;
		   if (seconds<4){
			   show_the_text=true;
			   the_text="Your score starts at ten. For every mine you capture, it increases by one point.";
		   }
		   if (seconds>=4 && seconds<8){
			   show_the_text=true;
			   the_text="When you get hit by a mine, you lose two points.\nAlso, if you run out of shields, you fail the level.";
		   }
		   if (seconds>=8 && seconds<12){
			   show_the_text=true;
			   the_text="Try to end the level with the highest score you can.";
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