package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Basic_Survive extends ArcadeScreen {
	
	final ProbDef game;

	
	public ArcadeScreen_Basic_Survive(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	    minecount=60;
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("hexagon");
		   turret_three=new Turret_Standard("circle");
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
		basic_set(10);
		
		
		
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
		   if (seconds<7){
			   show_the_text=true;
			   the_text="In this level, capturing mines won't raise your score. Just try to avoid collisions.";
		   }
		   if (seconds>=7 && seconds<12){
			   show_the_text=true;
			   the_text="Try to end the level with the highest score you can.";
		   }
		   
		   
	}
	
	@Override
	void calculate_score(){
		score=shields*2+10;
		score=Math.max(score, 0);
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}