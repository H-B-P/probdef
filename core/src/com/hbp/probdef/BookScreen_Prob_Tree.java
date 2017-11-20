package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class BookScreen_Prob_Tree extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;

	private Texture tree_percentage;
	private Texture tree_decimal;
	private Texture tree_fraction;
	
	private Texture lb_percentage_t;
	private Texture lb_fraction_t;
	private Texture lb_decimal_t;
	
	private Texture lb_trim_blue_t;
	private Texture lb_trim_orange_t;
	
	private Rectangle lb_percentage_r;
	private Rectangle lb_fraction_r;
	private Rectangle lb_decimal_r;
	
	private String which_tree;

	
	public BookScreen_Prob_Tree(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=6;
		
		batch=new SpriteBatch();
		
		tree_percentage=new Texture(Gdx.files.internal("TREE_CENT.png"));
		tree_decimal=new Texture(Gdx.files.internal("TREE_DECI.png"));
		tree_fraction=new Texture(Gdx.files.internal("TREE_FRAC.png"));
		
		lb_percentage_t=new Texture(Gdx.files.internal("little_button_cent.png"));
		lb_decimal_t=new Texture(Gdx.files.internal("little_button_deci.png"));
		lb_fraction_t=new Texture(Gdx.files.internal("little_button_frac.png"));
		
		lb_percentage_r=new Rectangle(5, 355, 100,40);
		lb_fraction_r=new Rectangle(110, 355, 100,40);
		lb_decimal_r=new Rectangle(215, 355, 100,40);
		
		
		lb_trim_blue_t=new Texture(Gdx.files.internal("little_button_blue_trim.png"));
		lb_trim_orange_t=new Texture(Gdx.files.internal("little_button_orange_trim.png"));
		
		
		which_tree="percentage";
	}
	
	@Override
	void set_book_name(){
		bookname="Book_Tree";
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		if (page==2 || page==3 || page==4){
			batch.begin();
			
			batch.draw(lb_percentage_t, lb_percentage_r.x, lb_percentage_r.y);
			batch.draw(lb_decimal_t, lb_decimal_r.x, lb_decimal_r.y);
			batch.draw(lb_fraction_t, lb_fraction_r.x, lb_fraction_r.y);
			
			if (which_tree.equals("percentage")){
				batch.draw(tree_percentage, 60, 190);
				batch.draw(lb_trim_orange_t, lb_percentage_r.x, lb_percentage_r.y);
			}
			if (which_tree.equals("decimal")){
				batch.draw(tree_decimal, 60, 190);
				batch.draw(lb_trim_orange_t, lb_decimal_r.x, lb_decimal_r.y);
			}
			if (which_tree.equals("fraction")){
				batch.draw(tree_fraction, 60, 190);
				batch.draw(lb_trim_orange_t, lb_fraction_r.x, lb_fraction_r.y);
			}
			
			if (lb_percentage_r.contains(tp_x, tp_y)){
				batch.draw(lb_trim_blue_t, lb_percentage_r.x, lb_percentage_r.y);
				if (Gdx.input.justTouched()){
					which_tree="percentage";
				}
			}
			if (lb_decimal_r.contains(tp_x, tp_y)){
				batch.draw(lb_trim_blue_t, lb_decimal_r.x, lb_decimal_r.y);
				if (Gdx.input.justTouched()){
					which_tree="decimal";
				}
			}
			if (lb_fraction_r.contains(tp_x, tp_y)){
				batch.draw(lb_trim_blue_t, lb_fraction_r.x, lb_fraction_r.y);
				if (Gdx.input.justTouched()){
					which_tree="fraction";
				}
			}
			
			
			
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
		if (page==5){
			if (seconds==2){
				spawnMine(0,95);
			}
		}
		if (page==6){
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
		else if (page==5){
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
		if (option_acalc.equals("Normal")){
			if (page==5){
				autocalc_and_display("destroy");
			}
			if (page==6){
				autocalc_and_display("capture");
			}
	    }
	    if (option_acalc.equals("Detail")){
	    	if(page>4){
	    		autocalc_and_display("everything");
	    	}
	    }
	
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   purpletext=false;
		writing_symbol_visible=false;
		if (page==1){
			
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="The blue text shows the odds of this mine being captured.";
				if (turret_one.targeted){
					the_text="The probability that the triangle captures it is 30%.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="The probability that it remains after the triangle fires, but the pentagon captures it, is 10% of 30%, or 3%. So, 33% total.";
				}
				
			}
			if (seconds==6 && TIMESPEED==0){
				show_the_text=true;
				the_text="The red text the odds of this mine being destroyed.";
				if (turret_one.targeted){
					the_text="The probability that the triangle destroys it is 40%.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="The probability that it remains after the triangle but the pentagon destroys it is 80% of 30%, or 24%.\n40% + 24% = 64% total.";
				}
				
			}
			if (seconds==8 && TIMESPEED==0){
				show_the_text=true;
				the_text="Since we have the other outcomes, we have a new way to find survival:\n100% - (64% + 33%) = 3%.";
				
			}
			if (page_time>8 && TIMESPEED>0){
				show_the_text=true;
				purpletext=true;
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
			if (page_time>2){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
					
				turret_four.does_it_work=false;
				turret_three.does_it_work=false;
				show_the_text=true;
				the_text="The number at the root is 100%, since the probability that SOMETHING happens is 100%.";
				if (page_time>2){
					time_to_move_on=true;
				}
			}
		
		if (page==4){
			
			turret_four.does_it_work=false;
			turret_three.does_it_work=false;
			show_the_text=true;
			the_text="Each branch multiplies the probability above it by the probability along it to get the probability below it.";
			if (page_time>2){
				time_to_move_on=true;
			}
			
		}
		
		if (page==5){
			turret_four.does_it_work=false;
			if (page_time<4||(seconds==4 && TIMESPEED==0 && turret_one.targeted==false)){
				show_the_text=true;
				writing_symbol_visible=true;
				the_text="Before targeting, use a probability tree to calculate the probability that this mine will be destroyed when targeted with these turrets.";
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==6){
			if (page_time<4||(seconds==4 && TIMESPEED==0 && turret_one.targeted==false)){
				writing_symbol_visible=true;
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