package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Prob_Triangle extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	
	public BookScreen_Prob_Triangle(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=20;
		
		batch=new SpriteBatch();
		
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("pentagon",3);
		   turret_two=new Turret_Standard("pentagon",2);
		   turret_three=new Turret_Standard("pentagon");
		   turret_four=new Turret_Standard("pentagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		
	}
	@Override
	void level_specific_HUD(){
		   
	   }
	
	@Override
	void draw_textbox(String text){
		   
		if (page==1){
			draw_textbox_one(text);
		}
		if (page==2){
			draw_textbox_two(text);
		}
	   }
	
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		show_the_other_text=false;
		   suppress_freezes=false;
		   time_to_move_on=false;
		   purpletext=false;
		
		if (page==1){
			
			show_the_other_text=true;
			the_other_text="As per the last section: to find the probability of four hits out of five attempts, you can find the probability of HHHHF, then multiply by five.";
			show_the_text=true;
			the_text="The number you multiply by isn't always this obvious. How many ways you can arrange two hits among five attempts?";
			if (page_time>8){
				time_to_move_on=true;
			}
		}
		
		if (page==2){
			show_the_other_text=true;
			the_other_text="The answer is 10: HHFFF, HFHFF, HFFHF, HFFFH, FHHFF, FHFHF, FHFFH, FFHHF, FFHFH, FFFHH. You may have found that by trial and error.";
			show_the_text=true;
			the_text="But as numbers get larger, that approach becomes impractical very quickly. So instead, we can use an idea called Pascal's Triangle.";
			
			if (page_time>6){
				time_to_move_on=true;
			}
		}
		
		if (page==3){
			
			show_the_text=true;
			the_text="When a turret fires no times, there's one outcome, because no chances are being taken.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==4){
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