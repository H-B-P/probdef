package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class BookScreen_Bayes_BayesTheorem extends BookScreen_Bayes {
	
	final ProbDef game;
	
	Texture green_arrow_t;
	Texture tiny_black_arrow_t;
	
	Texture FOREST_t;
	Texture FOREST_chop_one_t;
	Texture FOREST_chop_two_t;
	
	public BookScreen_Bayes_BayesTheorem(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=10;
		
		minecount=999;
		
		green_arrow_t=new Texture(Gdx.files.internal("simple_green_arrow.png"));
		tiny_black_arrow_t=new Texture(Gdx.files.internal("tiny_black_arrow.png"));
	
		FOREST_t=new Texture(Gdx.files.internal("FOREST.png"));
		FOREST_chop_one_t=new Texture(Gdx.files.internal("FOREST_chop_1.png"));
		FOREST_chop_two_t=new Texture(Gdx.files.internal("FOREST_chop_2.png"));
		
	}
	
	@Override
	void set_book_name(){
		bookname="Book_BayesTheorem";
	}
	
	@Override
	void level_specific_bayesian_update(String dot_type, EnemyShip enemyship){
		if (dot_type.equals("destroy")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.4f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.8f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.3f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.5f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.3f;
			enemyship.assignedprob_three=enemyship.assignedprob_three*0.1f;
		}
		normalize(enemyship);
	}
	
	
	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='e';
		ship_one_front='c';
		ship_one_back='a';
		
		ship_two_engines='e';
		ship_two_front='c';
		ship_two_back='b';
		
		ship_three_engines='e';
		ship_three_front='c';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="circle";
		turret_type_two="triangle";
		turret_type_three="pentagon";
		
		ship_one_percentfreq_one=50;
		ship_one_percentfreq_two=50;
		ship_one_percentfreq_three=0;
		
		ship_two_percentfreq_one=0;
		ship_two_percentfreq_two=40;
		ship_two_percentfreq_three=60;
		
		ship_three_percentfreq_one=50;
		ship_three_percentfreq_two=25;
		ship_three_percentfreq_three=25;
		
	}
    
	@Override
    
	void level_specific_waves(){
		
		suppress_phasing=true;
		
		if (page==1){
			if (shipwave==1){
			ship_one_spawn_random(-1,true);
			ship_one_spawn_random(1,true);
			}
		}
		if (page==9){
			if (shipwave==1){
			ship_two_spawn_random(-1,true);
			ship_two_spawn_random(1,true);
			}
		}
		if (page==10){
			if (shipwave==1){
				ship_three_spawn_random(-1,true);
				ship_three_spawn_random(1,true);
			}
		}
		if (page==11){
			if (shipwave==1){
				minecount=999;
				ship_three_spawn_random(0, true);
				suppress_phasing=false;
			}
			
		}
	}
	
	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		if (page==2){
			batch.begin();
			
			batch.draw(FOREST_t, 60,220);
			
			batch.end();
		}
		if (page==3){
			batch.begin();
			
			batch.draw(FOREST_chop_one_t, 60,220);
			
			batch.end();
		}
		if (page==4){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(0f)+"%\nT: "+present_float(20f)+"%\nP: "+present_float(0f)+"%", 40+25, 363, 100, 1, true);
			batch.draw(tiny_black_arrow_t, 155,335);
			acalc_greenfont.draw(batch, "C: "+present_float(0f)+"%\nT: "+present_float(100f)+"%\nP: "+present_float(0f)+"%", 180-25, 363, 100, 1, true);
			
			batch.end();
		}
		if (page==5){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(50f)+"%\nT: "+present_float(50f)+"%\nP: "+present_float(0f)+"%", 20, 343, 100, 1, true);
			blackfont.draw(batch, "Step 1",85, 363, 60, 1, true);
			batch.draw(tiny_black_arrow_t, 110, 315);
			acalc_greenfont.draw(batch, "C: "+present_float(0f)+"%\nT: "+present_float(20f)+"%\nP: "+present_float(0f)+"%", 110, 343, 100, 1, true);
			blackfont.draw(batch, "Step 2",175, 363, 60, 1, true);
			batch.draw(tiny_black_arrow_t, 200, 315);
			acalc_greenfont.draw(batch, "C: "+present_float(0f)+"%\nT: "+present_float(100f)+"%\nP: "+present_float(0f)+"%", 200, 343, 100, 1, true);
			
			batch.end();
		}
		if (page==6){
			batch.begin();
			
			batch.draw(FOREST_t, 60,210);
			
			batch.end();
		}
		if (page==7){
			batch.begin();
			
			batch.draw(FOREST_chop_two_t, 60,210);
			
			batch.end();
		}
		if (page==8){
			batch.begin();
			
			acalc_greenfont.draw(batch, "C: "+present_float(25f)+"%\nT: "+present_float(15f)+"%\nP: "+present_float(0f)+"%", 40+25, 363, 100, 1, true);
			batch.draw(tiny_black_arrow_t, 155,335);
			acalc_greenfont.draw(batch, "C: "+present_float(62.5f)+"%\nT: "+present_float(37.5f)+"%\nP: "+present_float(0f)+"%", 180-25, 363, 100, 1, true);
			
			batch.end();
		}
		
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
		font.draw(batch, "SHIP DESIGN Z01", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN Z02", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN Z03", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void draw_textbox(String text){
		if (page==2 || page==4 || page==9 || page==10 || page==11){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	@Override
	void draw_other_textbox(String text){
		if (page==2|| page==3 || page==4 || page==6 || page==8){
			draw_other_textbox_one(text);
		}
		else{
			draw_other_textbox_two(text);
		}
	   }
	
	void level_specific_probability_display(){
		if (!suppress_autocalc){
			for (EnemyShip enemyship:enemyships){
				if (enemyship.obscured){
					acalc_greenfont.draw(batch, "C: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nT: "+present_float(enemyship.assignedprob_two*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_three*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
				}
			}
		}
	}
	
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		show_the_other_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   purpletext=false;
		   vane_two.does_it_work=true;
		other_purpletext=false;
		   writing_symbol_visible=false;
		   if (page==1){
			   if(shipwave<2 && round<2){
				   vane_two.does_it_work=false;
			   }
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   
				   show_the_text=true;
				   the_text="You may have noticed how the probabilities associated with obscured ships update as turrets fire.";
				   if (vane_one.targeted){
					   the_text="This makes sense; in the extreme case, a destroying shot is perfect proof that a turret isn't a circle.";
				   }
			   }
			   if (shipwave==1 && round>1){
				   show_the_text=true;
				   the_text="But what exactly is it that happens when the probabilities update?";
			   }
			   if (shipwave>1){
				   show_the_text=true;
				   the_text="But what exactly is it that happens when the probabilities update?";
				   time_to_move_on=true;
			   }
		   }
		   if (page==2){
			   show_the_text=true;
			   the_text="Let's start by putting numbers around what we understand: how updates work when one of those ships fires a destroying shot.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==3){
			   show_the_text=true;
			   the_text="First, all outcomes which don't result in a destroying shot have their probabilities set to zero.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==4){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="With those outcomes eliminated, our remaining percentages are as shown above. These are normalised so they add to 100%.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==5){
			   show_the_text=true;
			   purpletext=true;
			   show_the_other_text=true;
			   the_other_text="";
			   the_text="(yeah, we're eliminating and normalising again, this is a thing, it keeps happening)";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==6){
			   show_the_text=true;
			   the_text="So what happens on a capturing shot? The same thing, more or less.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==7){
			   show_the_text=true;
			   the_text="Step one: Eliminate outcomes with a destroying shot, or no shot. Collect remaining outcomes by turret type.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==8){
			   show_the_text=true;
			   the_text="Step two: Normalise to get the new probabilities.";
			   show_the_other_text=true;
			   the_other_text="";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==9){
			   writing_symbol_visible=true;
			   vane_two.does_it_work=false;
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   
				   show_the_text=true;
				   the_text="Try using this method to predict how one of these ships will update when it fires a destroy shot, a capture shot, or nothing.";
				   if (vane_one.targeted){
					   purpletext=true;
					   the_text="(okay, time to see if you're right)";
				   }
				   
				   
				   
			   }
			   if (shipwave==1 && round>1){
				   show_the_text=true;
				   vane_two.does_it_work=true;
				   the_text="Do these probabilities line up with your prediction? If not, compare your method to the one used over the last few pages.";
			   }
			   if (shipwave>1){
				   vane_two.does_it_work=true;
				   time_to_move_on=true;
			   }
		   }

		   if (page==10){
			   writing_symbol_visible=true;
			   vane_two.does_it_work=false;
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   show_the_text=true;
				   the_text="Time for a challenge. Predict how one of these ships will update when it fires a destroy shot, a capture shot, or nothing.";
				   if (vane_one.targeted){
					   purpletext=true;
					   the_text="(last chance to double-check your work!)";
				   } 
				   
			   }
			   if (shipwave==1 && round>1){
				   show_the_text=true;
				   vane_two.does_it_work=true;
				   the_text="Did you get that right? If so, well done!";
			   }
			   if (shipwave>1){
				   show_the_text=true;
				   purpletext=true;
				   suppress_phasing=true;
				   the_text="(welp, that's it for this book, and also for the Library; hope you had a nice time and feel appropriately Educated)";
				   time_to_move_on=true;
			   }
			   //if (shipwave>1){
				//   time_to_move_on=true;
				//   vane_two.does_it_work=true;
			   //}
		   }
		   if (page==11){
			   if (shipwave==1 && current_status.equals("bowling")){
				   show_the_text=true;
				   if (minecount==999){
					   the_text="Your ship now starts the wave intangible. Click on the enemy ship to launch mines toward it, and see probabilities update.";
				   }
				   if (minecount==998 || minecount==997){
					   purpletext=true;
					   the_text="(fyi, this section is just so you can watch the autocalc zero in on certainty without worrying about mine shortages)";
				   }
				   if (minecount<997){
					   purpletext=false;
					   the_text="When you're ready to start the wave proper, click your ship. Alternatively, press space with no ship selected.";
				   }
				   if (minecount<989){
					   the_text="Note: 0.00% doesn't always mean eliminated, it could just be that the chance got too small to represent with two decimal places.";
				   }
			   }
			   if (shipwave>1){
				   show_the_text=true;
				   purpletext=true;
				   suppress_phasing=true;
				   the_text="(welp, that's it for this book, and also for the Library; hope you had a nice time and feel appropriately Educated)";
				   time_to_move_on=true;
			   }
			   
			   
		   }
		   
		   
		   
	}
}