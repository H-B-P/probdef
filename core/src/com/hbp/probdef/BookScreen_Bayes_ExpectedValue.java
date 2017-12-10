package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class BookScreen_Bayes_ExpectedValue extends BookScreen_Bayes {
	
	final ProbDef game;
	
	private boolean first_decision_clever;
	private boolean second_decision_clever;
	
	public BookScreen_Bayes_ExpectedValue(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=11;
		
		number_of_turret_types=3;
		
		first_decision_clever=false;
		second_decision_clever=false;
	}
	
	@Override
	void set_book_name(){
		bookname="Book_ExpectedValue";
	}

	@Override
	
	void level_specific_ship_aesthetics(){
		ship_one_engines='e';
		ship_one_front='a';
		ship_one_back='a';
		
		ship_two_engines='e';
		ship_two_front='a';
		ship_two_back='b';
		
		ship_three_engines='e';
		ship_three_front='a';
		ship_three_back='c';
		
	}
	
	@Override
	
	void level_specific_environment_setup(){
		turret_type_one="circle";
		turret_type_two="triangle";
		turret_type_three="pentagon";
		
		ship_one_percentfreq_one=30;
		ship_one_percentfreq_two=40;
		ship_one_percentfreq_three=30;
		
		ship_two_percentfreq_one=55;
		ship_two_percentfreq_two=0;
		ship_two_percentfreq_three=45;
		
		ship_three_percentfreq_one=40;
		ship_three_percentfreq_two=35;
		ship_three_percentfreq_three=25;
		
	}
    
	@Override
    
	void level_specific_waves(){
		
		suppress_phasing=true;
		
		if (page==1 && shipwave==1){
			ship_one_spawn_enemy_ship(-2, "pentagon", false);
			ship_one_spawn_enemy_ship(0, "circle", false);
			ship_one_spawn_enemy_ship(2, "circle", false);
		}
		
		if (page==2 && shipwave==1){
			ship_one_spawn_enemy_ship(-2, "triangle", false);
			ship_one_spawn_enemy_ship(0, "circle", false);
			ship_one_spawn_enemy_ship(2, "pentagon", false);
		}
		
		if (page==5 && shipwave==1){
			ship_two_spawn_random(0, true);
		}
		if (page==6 && shipwave==1){
			ship_three_spawn_random(0, true);
		}
	}
	
	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
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
		font.draw(batch, "CIRC/TRI/PENT", 90, 473, 140, 1, true);
		font.draw(batch, "SHIELDS: "+shields, 90, 455, 140, 1, true);
	   font.draw(batch, "PAGE: "+ page + "/"+maxpages, 90, 428, 140, 1, true);
	   }
	
	@Override
	void level_specific_ST_ONE_HUD(){
		font.draw(batch, "SHIP DESIGN X44", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_one_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_one_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_one_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_TWO_HUD(){
		font.draw(batch, "SHIP DESIGN X47", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_two_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_two_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_two_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void level_specific_ST_THREE_HUD(){
		font.draw(batch, "SHIP DESIGN X49", 90, 473, 140, 1, true);
		font.draw(batch, "CIRCLE: "+ship_three_percentfreq_one+"%", 90, 455, 140, 1, true);
		font.draw(batch, "TRIANGLE: "+ship_three_percentfreq_two+"%", 90, 437, 140, 1, true);
		font.draw(batch, "PENTAGON: "+ ship_three_percentfreq_three+"%", 90, 419, 140, 1, true);
	}
	
	@Override
	void draw_textbox(String text){
		if (page==4 || page==5 || (page==6 && shipwave==1) || page==7){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	@Override
	void draw_other_textbox(String text){
		if ( page==8 || page==9 || page==11){
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
		indicate=false;
		   
		   if (page==1){
			   if(shipwave==1){
					if (round==1 && current_status.equals("targeting")){
						indicate=true;
						show_the_text=true;
						the_text="You've destroyed things with turrets; now let's destroy things-with-turrets. Click the leftmost ship to target it.";
						if (vane_one.targeted){
							the_text="The right shocker currently has a pentagon zap, so it won't work on ships with circle turrets; click it to cycle.";
							if (vane_two.current_energy.equals("circle")){
								the_text="Now click on one of the circle ships, to target it with that shocker.";
							}
						}
						if (vane_one.targeted&&vane_two.targeted){
							if (vane_two.current_energy.equals("circle")){
								the_text="As with turrets, you can click the fire button to fire, or click on a shocker to retarget it before firing.";
							}
							else{
								the_text="The shapes don't match. Click the right shocker - at the bottom right corner of your screen - to retarget it.";
							}
						}
					}
					if ((round==1 && (current_status.equals("firing") || current_status.equals("zapping") || current_status.equals("waiting")))||(round==2 &&current_status.equals("targeting"))){
						show_the_text=true;
						the_text="On the enemy turn, the remaining ships shoot back. All successful attacks cost you one shield.";
						
					}
					if ((round==2 &&current_status.equals("targeting")) && vane_one.targeted){
						show_the_text=true;
						purpletext=true;
						the_text="(btw you can hover your mouse over a ship if you want a reminder of which turrets do what)";
					}
			   }
			   if (shipwave>1){
					time_to_move_on=true;
			   }
			}
		   
		   if (page==2){
			   if(shipwave==1){
					if (round==1 && current_status.equals("targeting")){
						show_the_text=true;
						the_text="So here's a question: which ships should you destroy first, to try and hold on to as many shields as possible?";
					}
					if (round==1 && current_status.equals("targeting") && vane_one.targeted && vane_two.targeted){
						if ((vane_one.target_ship.turret.ident.equals("pentagon") &&vane_two.target_ship.turret.ident.equals("triangle"))||(vane_one.target_ship.turret.ident.equals("triangle") &&vane_two.target_ship.turret.ident.equals("pentagon"))){
							first_decision_clever=true;
						}
						else{
							first_decision_clever=false;
						}
						if (first_decision_clever){
							show_the_text=true;
							the_text="It's pretty straightforward: turrets with more sides fire more often, so you should target them first.";
						}
						else{
							show_the_text=true;
							the_text="That's an . . . interesting decision. Let's see how it plays out.";
						}
						
					}
					if (round==2&& current_status.equals("targeting")){
						
						show_the_text=true;
						purpletext=true;
						
						if (first_decision_clever && shields==10){
							the_text="(aaaaaaand your strategic brilliance pays off, well played)";
						}
						if (!first_decision_clever && shields==10){
							the_text="(haha, dumb luck ftw!)";
						}
						if (first_decision_clever && shields<10){
							the_text="(don't feel bad, you made the best decision, just got unlucky)";
							}
						if (!first_decision_clever &&shields<10){
							the_text="(pretty badly, looks like)";
						}
					}
			   }
			   if (shipwave>1){
				   show_the_text=true;
				   if (first_decision_clever){
					   the_text="Anyway, let's put some numbers around that intuition.";
				   }
				   else{
					   the_text="Let me explain why another approach would have been better.";
				   }
				   time_to_move_on=true;
			   }
		   }
		   if (page==3){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="We have a large number of shields, so it makes sense to use Expected Value.\nA pentagon has a 90% chance to shoot, causing 1 damage.";
			   the_text="So the Expected Value of the shields lost per turn the pentagon survives is 90% of 1 shield, or 0.9 shields/turn.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==4){
			   show_the_text=true;
			   show_the_other_text=true;
			   the_other_text="For the same reason, damage from triangles and damage from circles have Expected Values of 0.7 shields/turn and 0.5 shields/turn respectively.";
			   the_text="We want to lose as few shields as possible, so it makes sense to prioritise pentagons over triangles and triangles over circles.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==5){
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   show_the_text=true;
				   the_text="This ship has obscured itself, but we can tell from its design it has a 55% chance of being a circle and a 45% chance of being a pentagon.";
				   if (current_status.equals("targeting") && vane_one.targeted){
					   the_text="In other words, if you target it with a circle zap and a pentagon zap, you can be sure it will be removed from play before it can fire.";
				   }
			   }
			   if (shipwave>1){
				   time_to_move_on=true;
			   }
		   }
		   if (page==6){
			   if (shipwave==1 && round==1 && current_status.equals("targeting")){
				   show_the_text=true;
				   the_text="So here's a harder question: what should we do here to have the best chance of conserving as many shields as possible?";
				   if (vane_one.targeted && vane_two.targeted){
					   if ((vane_one.current_energy.equals("pentagon") && vane_two.current_energy.equals("triangle"))||(vane_one.current_energy.equals("triangle") && vane_two.current_energy.equals("pentagon"))){
						   second_decision_clever=true;
					   }
					   else{
						   second_decision_clever=false;
					   }
				   }
			   }
			   if (shipwave>1){
				   show_the_text=true;
				   the_text="Not a bad strategy. But was it the best one you could have used? Let's work it out.";
				   time_to_move_on=true;
			   }
		   }
		   if (page==7){
			   show_the_text=true;
			   show_the_other_text=true;
			   
			   the_other_text="You had two zaps, so you could eliminate all but one of the three possibilities. And if you were unlucky, the ship would get one chance to fire.";
			   the_text="So: choose a 25% chance of being shot by a pentagon, a 35% chance of being shot by a triangle, or a 40% chance of being shot by a circle?";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==8){
			   show_the_text=true;
			   show_the_other_text=true;
			   
			   the_other_text="Remember that these turret types have Expected Values of 0.9, 0.7, and 0.5 shields lost per turn respectively. So:";
			   the_text=". A 25% chance of letting a pentagon shoot you once loses you 25% of 0.9 shields, or 0.225 shields, on average.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==9){
			   show_the_text=true;
			   show_the_other_text=true;
			   
			   the_other_text=". A 35% chance of letting a triangle shoot you once loses you 35% of 0.7 shields, or 0.245 shields, on average.";
			   the_text=". And a 40% chance of letting a circle shoot you once loses you 40% of 0.5 shields, or 0.2 shields, on average.";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==10){
			   show_the_text=true;
			   show_the_other_text=true;
			   
			   if (second_decision_clever){
				   the_other_text=". E(pentagon) = 0.225 shields\n. E(triangle) = 0.245 shields\n. E(circle) = 0.2 shields\nSo you were right to use a triangle and a pentagon zap.";
			   }
			   else{
				   the_other_text=". E(pentagon) = 0.225 shields\n. E(triangle) = 0.245 shields\n. E(circle) = 0.2 shields\nSo you should have used a triangle and a pentagon zap.";
			   }
			   
			   
			   purpletext=true;
			   the_text="(there's not much in it tbh, the answer was only non-obvious because the difference was like 2% of a shield)";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
		   if (page==11){
			   show_the_text=true;
			   show_the_other_text=true;
			   purpletext=true;
			   the_other_text="That's true, and it's also true that things get much more complicated when there's more than one ship.";
			   the_text="(but yeah all else being equal thinking in terms of Expected Value for things like this is usually a good idea)";
			   if (seconds>6){
				   time_to_move_on=true;
			   }
		   }
	}
}