package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Shortcut extends BookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_Shortcut(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		maxpages=4;
		
		batch=new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("square",3);
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
			if (seconds==2){
				spawnShieldMine(0,65,4);
			}
			if (seconds==6){
				spawnShieldMine(-1, 95, 1);
				spawnShieldMine(1, 95, 1);
			}
			if (seconds==8){
				spawnMine(-2, 95);
				spawnMine(1, 95);
			}
			if (seconds==10){
				spawnMine(-2, 95);
				spawnMine(0, 95);
				spawnMine(2, 95);
			}
			if (seconds==12){
				spawnShieldMine(-2, 65, 2);
				spawnShieldMine(0, 65, 2);
				spawnShieldMine(2, 65, 2);
			}
		}
		if (page==2){
			if (seconds==2){
				spawnShieldMine(1,65, 2);
			}
			if (seconds==10){
				spawnMine(0,95);
			}
			if (seconds==12){
				spawnShieldMine(0,95,1);
			}
		}
		if (page==3){
			if (seconds==2){
				spawnShieldMine(0,95,1);
			}
		}
		if (page==4){
			if (seconds==2){
				spawnShieldMine(0,95,1);
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
		   greentext=false;
		
		if (page==1){
			if (page_time<3){
				show_the_text=true;
				the_text="Multishot turrets fire several shots per volley. They're good for handling shielded mines.";
			}
			if (seconds==8 && TIMESPEED==0){
				show_the_text=true;
				the_text="The string of shots fired by a multishot turret are all independent.";
			}
			if (seconds==8 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="This means a 3-shot square turret is equivalent to three 1-shot square turrets targeting the same mine.";
			}
			if (page_time>18){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="When every turret targeted on a mine is of the same type, you get to take some shortcuts.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="This is because the chance of an outcome with a given number of shots is the same no matter how you get there.";
			}
			if (seconds==4 && turret_one.targeted && turret_two.targeted){
				show_the_text=true;
				the_text="HHHF has the same probability as HHFH, HFHH, and FHHH. 0.8 x 0.8 x 0.8 x 0.2 =\n0.2 x 0.8 x 0.8 x 0.8.";
			}
			if (seconds==12 && TIMESPEED==0){
				show_the_text=true;
				the_text="The probability of remaining for a mine with no shield is the probability of every shot failing.";
			}
			if (seconds==12 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="So FFFF: 0.2 x 0.2 x 0.2 x 0.2.";
			}
			if (seconds==12 && TIMESPEED==0 && turret_one.targeted&& turret_two.targeted){
				show_the_text=true;
				the_text="So FFFF: 0.2 x 0.2 x 0.2 x 0.2.\nThis is 0.0016, or 0.16%.";
			}
			if (seconds==14 && TIMESPEED==0){
				show_the_text=true;
				the_text="The probability of remaining for a mine with one shield is that plus the probability of only one shot failing.";
			}
			if (seconds==14 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="So FFFF + HFFF + FHFF + FFHF + FFFH. But the last four probabilities will be the same!";
			}
			if (seconds==14 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted){
				show_the_text=true;
				the_text="So FFFF + 4 x HFFF:\n0.2 x 0.2 x 0.2 x 0.2 +\n4 x (0.8 x 0.2 x 0.2 x 0.2)\nThis gives 2.72%";
			}
			if (page_time>16){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			turret_four.does_it_work=false;
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="Predict what the autocalc will say when five shots are targeted at a mine with one shield.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				greentext=true;
				the_text="(note that the autocalc only displays to two decimal places)";
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==4){
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="Predict what the autocalc will say when six shots are targeted at a mine with one shield.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				greentext=true;
				the_text="(don't stop noting that the autocalc only displays to two decimal places)";
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