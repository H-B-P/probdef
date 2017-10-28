package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Prob_MoreTrees extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_Prob_MoreTrees(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=6;
		
		batch=new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
	}
	
	@Override
	void draw_textbox(String text){
		   
		if (page==1 && seconds==8){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("pentagon");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("circle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			if (seconds==2){
				spawnShieldMine(-2,65,1);
				spawnShieldMine(0,65,2);
			}
			if (seconds==6){
				spawnShieldMine(0, 95, 1);
			}
			if (seconds==8){
				spawnShieldMine(-1, 95, 1);
				spawnShieldMine(1, 65, 2);
			}
			if (seconds==12){
				spawnShieldMine(-2, 45, 1);
				spawnShieldMine(0, 45, 2);
				spawnShieldMine(2, 45, 3);
			}
		}
		if (page==2){
			if (seconds==4){
				spawnShieldMine(0,95,1);
			}
		}
		if (page==3){
			if (seconds==4){
				spawnShieldMine(0,95,2);
			}
		}
		if (page==4){
			if (seconds==6){
				spawnShieldMine(0,95,1);
			}
		}
		if (page==5){
			if (seconds==2){
				spawnShieldMine(0,95,1);
			}
		}
		if (page==6){
			if (seconds==2){
				spawnShieldMine(0,95,2);
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
		   purpletext=false;
		
		if (page==1){
			if (page_time<3){
				show_the_text=true;
				the_text="Some mines have shields. Each shield can absorb one shot before disappearing.";
			}
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="To destroy or capture them, all their shields must be dropped.";
			}
			if (seconds==8 && TIMESPEED==0){
				purpletext=true;
				show_the_text=true;
				the_text="(btw survival stays 100% after targeting your first turret because no single attack can remove a shielded mine)";
			}
			if (page_time>20){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			if (page_time<5){
				show_the_text=true;
				the_text="Say you target a mine with one shield, using two turrets. What happens?";
			}
			if (seconds==6 && TIMESPEED==0){
				show_the_text=true;
				the_text="There are four outcomes, ignoring the destroy/capture distinction: fail-fail, hit-fail, fail-hit, and hit-hit.";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="The only outcome which removes it is both shots hitting. So the mine's surival chance is 100% minus that.";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted){
				show_the_text=true;
				the_text="90% of 70% is 63%, so the mine has a 100%-63%, or 37%, chance of remaining.";
			}
			if (page_time>8){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			turret_four.does_it_work=false;
			if (page_time<5){
				show_the_text=true;
				the_text="Say you target a mine with two shields, using three turrets.";
			}
			if (seconds==6 && TIMESPEED==0){
				show_the_text=true;
				the_text="We could draw out a whole tree, but the only outcome you care about is the one where all three fire.";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="So 80% of 90% of 70% . . .";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted){
				show_the_text=true;
				the_text="So 80% of 90% of 70% . . .\nthat's a 50.4% chance of the mine being removed,";
			}
			if (seconds==6 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted && turret_three.targeted){
				show_the_text=true;
				the_text="So 80% of 90% of 70% . . .\nthat's a 50.4% chance of the mine being removed,\n or a 49.6% chance it remains.";
			}
			if (seconds==6 && TIMESPEED>0 && TIMESPEED<1){
				show_the_text=true;
				purpletext=true;
				the_text="(do you feel lucky, punk?)";
			}
			
			if (page_time>8){
				time_to_move_on=true;
			}
		}
		if (page==4){
			turret_four.does_it_work=false;
			if (page_time<7){
				show_the_text=true;
				the_text="Say you target a mine with one shield, using three turrets. Now we need to look at individual outcomes.";
			}
			if (seconds==8 && TIMESPEED==0){
				show_the_text=true;
				the_text="If F is a fail and H is a hit, the outcomes where this mine remains are FFF, HFF, FHF, and FFH.";
			}
			if (seconds==8 && TIMESPEED==0 && turret_one.targeted){
				show_the_text=true;
				the_text="So it's possible to calculate the odds of each of those outcomes and sum them to get the chance it remains.";
			}
			if (seconds==8 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted){
				show_the_text=true;
				the_text="FFF = 0.3*0.1*0.2 = 0.006\nHFF = 0.7*0.1*0.2 = 0.014\nFHF = 0.3*0.9*0.2 = 0.054\nFFH = 0.3*0.1*0.8 = 0.024";
			}
			if (seconds==8 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted && turret_three.targeted){
				show_the_text=true;
				the_text="Add all those up and you get 0.098, or 9.8% chance of remaining.";
			}
			if (page_time>10){
				time_to_move_on=true;
			}
		}
		if (page==5){
			if (seconds<4 || (seconds==4 && TIMESPEED==0)){
				show_the_text=true;
				the_text="Try using this reasoning to predict what the autocalc will say when we target all four turrets on this mine.";
			}
			if (seconds==4 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted && turret_three.targeted && turret_four.targeted){
				show_the_text=true;
				the_text="Does that line up with your prediction? If so, well done.";
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==6){
			if (seconds<4){
				show_the_text=true;
				the_text="If you want a challenge, predict what the autocalc will say when we target all four turrets on THIS mine.";
			}
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				purpletext=true;
				the_text="(or just skip ahead, this is even more optional than most things in life are)";
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