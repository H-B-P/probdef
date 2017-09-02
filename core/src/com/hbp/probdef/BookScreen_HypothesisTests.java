package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_HypothesisTests extends GenericBookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_HypothesisTests(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		maxpages=2;
		
		batch=new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
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
		if (page==1){
			if (seconds==4){
				spawnHoloMine(0,200);
			}
			if (seconds==6){
				spawnHoloMine(0,35);
			}
			if (seconds==10){
				spawnMine(-2,65);
				spawnMine(2,65);
			}
			if (seconds==12){
				spawnMine(-2,65);
				spawnMine(2,65);
			}
		}
		if (page==2){
			if (seconds==2){
				spawnHoloProbablistic(-2,45,50);
				spawnHoloProbablistic(0,45,50);
				spawnHoloProbablistic(2,45,50);
			}
			if (seconds==4){
				spawnHoloProbablistic(-1,45,50);
				spawnHoloProbablistic(1,45,50);
			}
			if (seconds==6){
				spawnHoloProbablistic(2,45,50);
				spawnHoloProbablistic(-2,45,50);
			}
			if (seconds==8){
				spawnHoloProbablistic(0,45,50);
			}
		}
		if (page==3){
			
		}
		if (page==4){
			
		}
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	@Override
	void draw_textbox(String text){
		if (page==2){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   greentext=false;
		
		if (page==1){
			
			if (page_time<4){
				show_the_text=true;
				the_text="Holo mines look ordinary, but vanish harmlessly when they touch the ship's shield.";
			}
			if ((page_time>6 && page_time<8) || (seconds==8 && TIMESPEED==0)){
				show_the_text=true;
				the_text="They don't exist, so turrets won't successfully fire on them. Every shot you try fails.";
				
			}
			if (seconds==8 && (turret_one.targeted || turret_two.targeted || turret_three.targeted || turret_four.targeted)){
				show_the_text=true;
				the_text="The autocalculator assumes every mine is real and calculates odds based on that.";
			}
			if ((seconds==12 && TIMESPEED==0)){
				show_the_text=true;
				the_text="Eventually, a holo mine will defy enough odds that you can assume it's fake, and focus on real threats.";
				
			}
			if (page_time>18){
				show_the_text=true;
				greentext=true;
				the_text="(fyi from here on out all holos are generated randomly, so memorising which mines are fake won't work)";
			}
			if (page_time>20){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="There's a way to do this rigorously. Pick a mine and concentrate some turrets on it.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="Your null hypothesis, H0, is that it's a real mine. Your alternative hypothesis, HA, is that it's a holo.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_two.targeted){
				show_the_text=true;
				the_text="If the autocalc gives it less than a p% chance of remaining, but it remains, you reject the null and assume it's fake.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_three.targeted){
				show_the_text=true;
				the_text="The standard p-value is 5%. You may want to use a different one depending on how common holos are and how careful you're being.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_four.targeted){
				show_the_text=true;
				greentext=true;
				the_text="(you ready to do some Scientific Investigation and Test some Hypotheses? here we go)";
			}
			if (page_time>16){
				time_to_move_on=true;
			}
			
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}