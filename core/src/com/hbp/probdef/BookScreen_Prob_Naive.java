package com.hbp.probdef;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class BookScreen_Prob_Naive extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	public BookScreen_Prob_Naive(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=4;
	}
	
	@Override
	void set_book_name(){
		bookname="Book_Naive";
	}
	
	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("square");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("pentagon");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	void draw_textbox(String text){
		   
		if (page==4){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			if (seconds==2){
				spawnTitaniumMine(0,105);
			}
		}
		if (page==2){
			if (seconds==2){
				spawnTitaniumMine(0,105);
			}
			
			if (seconds==4){
				spawnTitaniumMine(0,105);
			}
		}
		if (page==3){
			if (seconds==2){
				spawnTitaniumMine(0,105);
			}
		}
		if (page==4){
			if (seconds==2){
				spawnTitaniumMine(0,90);
			}
		}
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		show_the_other_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   purpletext=false;
		   writing_symbol_visible=false;
		
		if (page==1){
			
			turret_three.does_it_work=false;
			turret_four.does_it_work=false;
			
			
			
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="Titanium mines cannot be destroyed; only captured.";
				if (turret_one.targeted || turret_two.targeted){
					the_text="The chance each turret has to remove a titanium mine from play is small.";
				}
				if (turret_one.targeted && turret_two.targeted){
					the_text="When combining small, independent probabilities, you can use approximations you couldn't otherwise.";
				}
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			
			turret_one.does_it_work=false;
			turret_two.does_it_work=false;
			
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
				the_text="A naive thinker might say that, since one pentagon has a 10% chance to remove this mine, two have a 20% chance.";
				if (turret_three.targeted || turret_four.targeted){
					the_text="This approach predicts a 80% chance that it remains.\nBut the true value . . .";
				}
				if (turret_three.targeted && turret_four.targeted){
					the_text="This approach predicts a 80% chance that it remains.\nBut the true value . . .\nactually, that's pretty close!";				}
			}
			
			if (seconds==6 && TIMESPEED==0){
				show_the_other_text=true;
				the_other_text="For our purposes,\nthere are three outcomes:\nfirst turret captures,\nsecond turret captures,\nand neither capture.";
				show_the_text=true;
				the_text="The naive approach gives these outcomes probabilities of 10%, 10% and 80% respectively.";
				if (turret_three.targeted){
					show_the_other_text=false;
					the_text="It's right about the first turret: the chance that one captures is 10%.";
				}
				if (turret_three.targeted && turret_four.targeted){
					show_the_other_text=true;
					the_other_text="The chance the second turret captures is 10% of the chance it remains after the first shot: 10% of (100%-10%),\nor 10% of 90%, or 9%.";
					the_text="Because 10% is small, most of the 100% remains after the first shot, so 10% of what remains is nearly just 10%.";
				}
			}
			
			if (page_time>8){
				time_to_move_on=true;
			}
			
		}
		
		if (page==3){
			turret_one.does_it_work=false;
			turret_four.does_it_work=false;
			if (seconds==4 && TIMESPEED==0){
				writing_symbol_visible=true;
				show_the_text=true;
				the_text="Try using the naive approach to predict what the autocalc will say when both active turrets are targeted.";
				if (turret_two.targeted && turret_three.targeted){
					the_text="The naive approach gives 70%, which is - again - pretty close to the true value.";
				}
			}
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		if (page==4){
			if (seconds==4 && TIMESPEED==0){
				show_the_text=true;
					the_text="In general, when people add probabilities they 'should' multiply, they're relying on them being small enough for the naive approach to work.";
				if (turret_one.targeted){
					the_text="And it's not just probabilities. Measurement error, compound interest, material strain: any context with multiple small percentage changes.";
				}
				if (turret_two.targeted){
					the_text="But if they aren't small, or aren't independent, or there are too many of them, the approximation breaks down.";
				}
				if (turret_three.targeted){
					purpletext=true;
					the_text="(case in point: this mine won't have anywhere near a 40% chance to remain once we finish targeting turrets)";
				}
				if (turret_four.targeted){
					purpletext=true;
					the_text="(case in point: this mine won't have anywhere near a 40% chance to remain once we finish targeting turrets)\n(yeah, told you so)";
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