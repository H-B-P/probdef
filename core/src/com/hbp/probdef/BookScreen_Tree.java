package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Tree extends GenericBookScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;

	private Texture tree_percentage;
	
	
	public BookScreen_Tree(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		maxpages=4;
		
		batch=new SpriteBatch();
		
		tree_percentage=new Texture(Gdx.files.internal("TREE_III.png"));
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		if (page==2){
			batch.begin();
			batch.draw(tree_percentage, 60, 190);
			batch.end();
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
				spawnMine(0,95);
			}
			if (seconds==4){
				spawnMine(0,95);
			}
			if (seconds==6){
				spawnMine(0,95);
			}
		}
		if (page==2){
			
		}
		if (page==3){
			if (seconds==2){
				spawnMine(0,95);
			}
		}
		if (page==4){
			if (seconds==2){
				spawnMine(0,95);
			}
		}
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	@Override
	void draw_textbox(String text){
		   
		if (page==1 && seconds<7 && TIMESPEED==0 && turret_one.targeted && turret_two.targeted){
			draw_textbox_two(text);
		}
		else if (page==3){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	
	@Override
	
	void autocalc_and_display_dummy(){
		if (page==1){
			if (seconds==4){
				autocalc_and_display("capture");
			}
			else if (seconds==6){
				autocalc_and_display("destroy");
			}
			else{
				autocalc_and_display("survive");
			}
		}
		if (page==3){
			autocalc_and_display("destroy");
		}
		if (page==4){
			autocalc_and_display("destroy");
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   greentext=false;
		
		if (page==1){
			
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			
			if (page_time<3){
				show_the_text=true;
				the_text="If you care whether a mine gets captured or destroyed, reasoning gets more complicated.";
			}
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="Let's say we want to know the odds of this mine being captured.";
				if (turret_one.targeted){
					the_text="The probability that the triangle captures it is 30%.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="The probability that it survives the triangle but the pentagon captures it is 10% of 30%, or 3%.\nSo, 33% total.";
				}
				
			}
			if (seconds==6 && TIMESPEED==0){
				show_the_text=true;
				the_text="Let's say we want to know the odds of this mine being destroyed.";
				if (turret_one.targeted){
					the_text="The probability that the triangle destroys it is 40%.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="The probability that it survives the triangle but the pentagon destroys it is 80% of 30%, or 24%.\n40% + 24% = 64% total.";
				}
				
			}
			if (seconds==8 && TIMESPEED==0){
				show_the_text=true;
				the_text="Since we have the other outcomes, we have a new way to find survival:\n100% - (64% + 33%) = 3%.";
				
			}
			if (page_time>8 && TIMESPEED>0){
				show_the_text=true;
				greentext=true;
				the_text="(a mine either remains, is destroyed, or is captured, so the probabilities can't not add to 100%)";
			}
			if (page_time>10){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			
			turret_four.does_it_work=false;
			turret_three.does_it_work=false;
			show_the_text=true;
			the_text="This reasoning can be shown in a probability tree.\nBranches represent things which can happen each shot.";
			if (page_time>4){
				the_text="The number at the root is 100%, since the probability that SOMETHING happens is 100%.";
			}
			if (page_time>8){
				the_text="Each branch multiplies the probability above by the probability along it to get the probability below it.";
			}
			if (page_time>10){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			turret_four.does_it_work=false;
			if (page_time<4||(seconds==4 && TIMESPEED==0 && turret_one.targeted==false)){
				show_the_text=true;
				the_text="Before targeting, use a probability tree to calculate the probability that this mine will be destroyed when targeted with three turrets.";
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==4){
			if (page_time<4||(seconds==4 && TIMESPEED==0 && turret_one.targeted==false)){
				show_the_text=true;
				the_text="Extend the tree and use it to find the probability this mine will be captured when all four turrets are targeted.";
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