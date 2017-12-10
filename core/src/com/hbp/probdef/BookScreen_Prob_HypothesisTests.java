package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Prob_HypothesisTests extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_Prob_HypothesisTests(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=2;
		
		batch=new SpriteBatch();
		
	}
	
	@Override
	void set_book_name(){
		bookname="Book_HypothesisTests";
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
			if (seconds==10){
				spawnDecoyMine(0,200);
			}
			if (seconds==12){
				spawnDecoyMine(0,35);
			}
			if (seconds==16){
				spawnMine(-2,65);
				spawnMine(2,65);
			}
			if (seconds==18){
				spawnMine(-2,65);
				spawnMine(2,65);
			}
		}
		if (page==2){
			if (seconds==2){
				spawnDecoyProbablistic(-2,45,50);
				spawnDecoyProbablistic(0,45,50);
				spawnDecoyProbablistic(2,45,50);
			}
			if (seconds==4){
				spawnDecoyProbablistic(-1,45,50);
				spawnDecoyProbablistic(1,45,50);
				spawnDecoyProbablistic(3,45,50);
			}
			if (seconds==6){
				spawnDecoyProbablistic(-2,45,50);
				spawnDecoyProbablistic(2,45,50);
			}
		}
	}
	
	@Override
	void draw_textbox(String text){
		if (page==2 && turret_three.targeted && !turret_four.targeted){
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
		   purpletext=false;
		
		if (page==1){
			
			if (page_time<10){
				show_the_text=true;
				the_text="Decoy mines look ordinary, but vanish harmlessly when they touch the ship's shield.";
			}
			if ((page_time>12 && page_time<14) || (seconds==14 && TIMESPEED==0)){
				show_the_text=true;
				the_text="They don't exist, so turrets won't successfully fire on them. Every shot you try fails.";
				
			}
			if (seconds==14 && (turret_one.targeted || turret_two.targeted || turret_three.targeted || turret_four.targeted)){
				show_the_text=true;
				the_text="The autocalculator assumes every mine is real and calculates odds based on that.";
			}
			if ((seconds==18 && TIMESPEED==0)){
				show_the_text=true;
				the_text="Eventually, a decoy will defy enough odds that you can assume it's fake, and focus on real threats.";
				
			}
			if (page_time>24){
				show_the_text=true;
				purpletext=true;
				the_text="(btw: from here on out all decoys are generated randomly, so memorising which mines are fake won't work)";
			}
			if (page_time>26){
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
				the_text="Your null hypothesis, 'H0', is that it's a real mine. Your alternative hypothesis, 'Ha', is that it's a decoy.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_two.targeted){
				show_the_text=true;
				the_text="If it has 5%-or-less chance of remaining after a volley, but it remains, you 'reject the null' and assume it's fake.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_three.targeted){
				show_the_text=true;
				the_text="Note: the standard 'p-value' is 5%, but you may want to use a different one depending on how common decoys are and how careful you're being.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_four.targeted){
				show_the_text=true;
				purpletext=true;
				the_text="(you ready to test some hypotheses?)";
			}
			if (page_time>14){
				time_to_move_on=true;
			}
			
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}