package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class BookScreen_Bayes_EliminateAndNormalise extends BookScreen_Bayes {
	
	final ProbDef game;
	
	Texture green_arrow_t;
	Texture tiny_black_arrow_t;
	
	public BookScreen_Bayes_EliminateAndNormalise(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=8;
		
		green_arrow_t=new Texture(Gdx.files.internal("simple_green_arrow.png"));
		tiny_black_arrow_t=new Texture(Gdx.files.internal("tiny_black_arrow.png"));
	}

	@Override
	void set_book_name(){
		bookname="Book_EliminateAndNormalise";
	}
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='e';
		ship_one_front='b';
		ship_one_back='a';
		
		ship_two_engines='e';
		ship_two_front='b';
		ship_two_back='b';
		
		ship_three_engines='e';
		ship_three_front='b';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="circle";
		turret_type_two="triangle";
		turret_type_three="pentagon";
		
		ship_one_percentfreq_one=10;
		ship_one_percentfreq_two=30;
		ship_one_percentfreq_three=60;
		
		ship_two_percentfreq_one=30;
		ship_two_percentfreq_two=20;
		ship_two_percentfreq_three=50;
		
		ship_three_percentfreq_one=10;
		ship_three_percentfreq_two=20;
		ship_three_percentfreq_three=70;
		
	}
    
	@Override
    
	void level_specific_waves(){
		
		suppress_phasing=true;
		
		if (page==1 && shipwave==1){
			ship_one_spawn_enemy_ship(0, "triangle", true);
		}
		if (page==8 && shipwave==1){
			ship_two_spawn_enemy_ship(-1, "triangle", true);
			ship_three_spawn_enemy_ship(1, "triangle", true);
		}
	}
	
	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		if (page==2){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(60f)+"%", 40, 363, 100, 1, true);
			batch.draw(green_arrow_t, 140,320);
			acalc_greenfont.draw(batch, "C: "+present_float(25f)+"%\nT: "+present_float(75f)+"%\nP: "+present_float(0f)+"%", 180, 363, 100, 1, true);
			
			batch.end();
		}
		if (page==3){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(60f)+"%", 40+25, 363, 100, 1, true);
			batch.draw(tiny_black_arrow_t, 155,335);
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(0f)+"%", 180-25, 363, 100, 1, true);
			
			batch.end();
		}
		if (page==4){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(0f)+"%", 110, 363, 100, 1, true);
			
			batch.end();
		}
		if (page==5){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(0f)+"%", 40+25, 363, 100, 1, true);
			batch.draw(tiny_black_arrow_t, 155,335);
			acalc_greenfont.draw(batch, "C: "+present_float(25f)+"%\nT: "+present_float(75f)+"%\nP: "+present_float(0f)+"%", 180-25, 363, 100, 1, true);
			
			batch.end();
		}
		if (page==6){
			batch.begin();
			
			//acalc_greenfont.draw(batch, "10:30 = 1:3 = 25:75", 80, 345, 160, 1, true);
			
			//acalc_greenfont.draw(batch, "10:30 = 1:3 = 25:75", 40, 370, 240, 1, true);
			//acalc_greenfont.draw(batch, "10/[10+30+0] = 10/40 = 25%", 40, 350, 240, 1, true);
			//acalc_greenfont.draw(batch, "30/(10+30+0) = 30/40 = 75%", 40, 335, 240, 1, true);
			//acalc_greenfont.draw(batch, "0/(10+30+0) = 0/40 = 0%", 40, 320, 240, 1, true);

			greenfont.draw(batch, "10 : 30 = 1 : 3 = 25 : 75", 40, 370, 240, 1, true);
			greenfont.draw(batch, "10/(10+30+0) = 10/40 = 25%", 40, 345, 240, 1, true);
			greenfont.draw(batch, "30/(10+30+0) = 30/40 = 75%", 40, 325, 240, 1, true);
			greenfont.draw(batch, "0/(10+30+0) = 0/40 = 0%", 40, 305, 240, 1, true);
			
			batch.end();
		}
		if (page==7){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(60f)+"%", 20, 343, 100, 1, true);
			blackfont.draw(batch, "Step 1",85, 363, 60, 1, true);
			batch.draw(tiny_black_arrow_t, 110, 315);
			acalc_greenfont.draw(batch, "C: "+present_float(10f)+"%\nT: "+present_float(30f)+"%\nP: "+present_float(0f)+"%", 110, 343, 100, 1, true);
			blackfont.draw(batch, "Step 2",175, 363, 60, 1, true);
			batch.draw(tiny_black_arrow_t, 200, 315);
			acalc_greenfont.draw(batch, "C: "+present_float(25f)+"%\nT: "+present_float(75f)+"%\nP: "+present_float(0f)+"%", 200, 343, 100, 1, true);
			
			batch.end();
		}
//		if (page==2 || page==3 || page==4){
//			batch.begin();
//			
//			batch.draw(lb_percentage_t, lb_percentage_r.x, lb_percentage_r.y);
//			batch.draw(lb_decimal_t, lb_decimal_r.x, lb_decimal_r.y);
//			batch.draw(lb_fraction_t, lb_fraction_r.x, lb_fraction_r.y);
//			
//			if (which_tree.equals("percentage")){
//				batch.draw(tree_percentage, 60, 190);
//				batch.draw(lb_trim_purple_t, lb_percentage_r.x, lb_percentage_r.y);
//			}
//			if (which_tree.equals("decimal")){
//				batch.draw(tree_decimal, 60, 190);
//				batch.draw(lb_trim_purple_t, lb_decimal_r.x, lb_decimal_r.y);
//			}
//			if (which_tree.equals("fraction")){
//				batch.draw(tree_fraction, 60, 190);
//				batch.draw(lb_trim_purple_t, lb_fraction_r.x, lb_fraction_r.y);
//			}
//			
//			if (lb_percentage_r.contains(tp_x, tp_y)){
//				batch.draw(lb_trim_blue_t, lb_percentage_r.x, lb_percentage_r.y);
//				if (Gdx.input.justTouched()){
//					which_tree="percentage";
//				}
//			}
//			if (lb_decimal_r.contains(tp_x, tp_y)){
//				batch.draw(lb_trim_blue_t, lb_decimal_r.x, lb_decimal_r.y);
//				if (Gdx.input.justTouched()){
//					which_tree="decimal";
//				}
//			}
//			if (lb_fraction_r.contains(tp_x, tp_y)){
//				batch.draw(lb_trim_blue_t, lb_fraction_r.x, lb_fraction_r.y);
//				if (Gdx.input.justTouched()){
//					which_tree="fraction";
//				}
//			}
//			
//			
//			
//			batch.end();
//		}
	}
	

	
	@Override
	
	void level_specific_events(){
		
	}
	@Override
	void level_specific_HUD(){
		font.draw(batch, "CIRC/TRI/PENT", 90, 455, 140, 1, true);
		font.draw(batch, "SHIELDS: "+shields, 90, 437, 140, 1, true);
	   }
	
	@Override
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN Y19", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN Y29", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN Y39", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void draw_textbox(String text){
		if (page==2 || page==3 || page==5 || page==6 || page==7 || page==8){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	@Override
	void draw_other_textbox(String text){
		if (page==2|| page==3 || page==4 || page==5){
			draw_other_textbox_one(text);
		}
		else{
			draw_other_textbox_two(text);
		}
	   }
	
	
	
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		show_the_other_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   purpletext=false;
		other_purpletext=false;
		
		vane_one.does_it_work=true;
		vane_two.does_it_work=true;
		   
		   if (page==1){
			   show_the_text=true;
			   vane_two.does_it_work=false;
			   the_text="You may have noticed how the probabilities associated with obscured ships update as zaps fail. How does that work?";
			   if (shipwave>1){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==2){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="You probably started that fight with a pentagon zap.\nIf so, you saw probabilities update from 10%/30%/60% to 25%/75%/0% when it failed.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==3){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="Step 1: when a ship survives a pentagon zap, that proves it doesn't have a pentagon turret, so the relevant probability goes to zero.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==4){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="But that can't be the only step in the update, since the probabilities we're left with add up to less than 100%.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==5){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="Step 2: the remaining probabilities 'normalise': scaling up until they total 100%, while keeping the ratio between them constant.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==6){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="The circles:triangles ratio stays constant because we only eliminated pentagons, and provided no evidence for circles vs triangles.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==7){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="To summarise:\n\n1. Eliminate impossibilities.\n2. Normalise to get to 100%.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   
		   if (page==8){
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   show_the_text=true;
				   the_text="Test your understanding here: predict in advance what the autocalc will display immediately after a failed pentagon zap to each ship.";
				   vane_one.current_energy="pentagon";
				   vane_two.current_energy="pentagon";
				   if (vane_one.targeted || vane_two.targeted){
					   purpletext=true;
					   the_text="(if you're wondering how we can know it'll fail: we're blatantly lying to the autocalc, all ships in this book are definitely triangles)";
				   }
				   if (vane_one.targeted && vane_two.targeted){
					   purpletext=true;
					   the_text="(but it'll still update perfectly on its false priors, so yeah just roll with it and pretend you don't know what's under the obscurities)";
				   }
			   }
			   if (shipwave==1 && round==2 && current_status.equals("targeting")){
				   show_the_text=true;
				   the_text="In case that went by too fast: that was 60%/40%/0% for the left ship, 33.33%/66.67%/0% for the right ship.\nDid you predict that?";
			   }
			   if (shipwave>1){
				   time_to_move_on=true;
			   }
		   }
	}
}