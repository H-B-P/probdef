package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class BookScreen_Combine extends GenericBookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	private float trapfloat;
	
	private boolean trapbool;
	
	private boolean gotcha;
	
	public BookScreen_Combine(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		maxpages=3;
		
		trapbool=false;
		
		gotcha=false;
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		if (trapbool){
			trapfloat+=delta;
		}
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("triangle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		trapbool=false;
		if (page==1){
			if (seconds==2){
				spawnMine(1,95);
			}
			if (seconds==4){
				spawnMine(-1,95);
			}
		}
		if (page==2){
			if (seconds==2){
				spawnMine(1,95);
			}
		}
		if (page==3){
			if (seconds==2){
				spawnMine(1,95);
			}
			if (seconds==4){
				trapbool=true;
				trapfloat=0;
			}
		}
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		
		if (page==1){
			
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			
			if (page_time>3.5 && page_time<4.5 && TIMESPEED==0){
				show_the_text=true;
				the_text="When no turret is targeted on a mine, the probability of it remaining is 100%.";
				if (turret_one.targeted || turret_two.targeted){
					the_text="When one turret is targeted, it's the probability of that turret failing.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="When two turrets are targeted, it's the probability of both turrets failing.";
				}
			}
			
			if (page_time>5.5 && page_time<6.5 && TIMESPEED==0){
				show_the_text=true;
				the_text="The probability of two unrelated things both happening is the product of their probabilities.";
				if (turret_one.targeted && turret_two.targeted){
					the_text="In this case, that means 20% of 30%: 6%.";
				}
			}
			if (page_time>6 && TIMESPEED>0){
				show_the_text=true;
				the_text="Or, equivalently, 0.3*0.2: 0.06. Or, also equivalently, 3/10*2/10: 6/100.";
			}
			if (page_time>8){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			
			turret_four.does_it_work=false;

			if (page_time>3.5 && page_time<4.5 && TIMESPEED==0){
				show_the_text=true;
				the_text="When more than two turrets are targeted on a mine, apply the same reasoning.";
				if (turret_one.targeted || turret_two.targeted || turret_three.targeted){
					the_text="The probability of a mine remaining is the probability of all turrets missing.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="20% of 30% is 6%";
				}
				if (turret_one.targeted && turret_two.targeted && turret_three.targeted){
					the_text="20% of 30% is 6%, and 20% of 6% is 1.2%.";
				}
			}
			if (page_time>4 && TIMESPEED>0){
				show_the_text=true;
				the_text="Likewise, 0.3*0.2*0.2 = 0.012, and 3/10*2/10*2/10 = 12/1000.";
			}
			if (page_time>6){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			if (page_time<4){
				show_the_text=true;
				the_text="See if you can work out what will be shown when you target all four turrets.";
			}
			if (page_time>3.5 && page_time<4.5 && TIMESPEED==0){
				show_the_text=true;
				if (!gotcha){
				the_text="(you may need a calculator and/or pen and paper for this)";
				}
				else{
				the_text="(or you can just blatantly skip ahead that's fine too I guess)";
				}
				if (turret_one.targeted || turret_two.targeted || turret_three.targeted || turret_four.targeted){
					if (trapfloat<1.5){
						gotcha=true;
					}
				}
				if (turret_one.targeted && turret_two.targeted && turret_three.targeted && turret_four.targeted){
					the_text="0.36%, also known as 0.0036, 36/10000, or 9/2500. Hopefully that lines up with your predictions.";
				}
			}
			
			if (page_time>6){
				time_to_move_on=true;
			}
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}