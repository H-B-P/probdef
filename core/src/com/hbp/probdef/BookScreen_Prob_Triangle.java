package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
public class BookScreen_Prob_Triangle extends BookScreen_Prob {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	private Texture triagram_1_t;
	private Texture triagram_1a_t;
	private Texture triagram_1b_t;
	
	private Texture triagram_2_t;
	private Texture triagram_2a_t;
	private Texture triagram_2b_t;
	
	private Texture triagram_3_t;
	private Texture triagram_3a_t;
	private Texture triagram_3b_t;
	
	private Texture triagram_4_t;
	private Texture triagram_4a_t;
	private Texture triagram_4b_t;
	
	private Texture triagram_5_t;
	private Texture triagram_5a_t;
	private Texture triagram_5b_t;
	
	private Texture combination_equation_t;
	
	public BookScreen_Prob_Triangle(final ProbDef gam) {
		
		super(gam);
		
		game = gam;
		
		maxpages=20;
		
		batch=new SpriteBatch();
		
		triagram_1_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_1.png"));
		triagram_1a_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_1a.png"));
		triagram_1b_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_1b.png"));
		
		triagram_2_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_2.png"));
		triagram_2a_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_2a.png"));
		triagram_2b_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_2b.png"));

		triagram_3_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_3.png"));
		triagram_3a_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_3a.png"));
		triagram_3b_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_3b.png"));
		
		triagram_4_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_4.png"));
		triagram_4a_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_4a.png"));
		triagram_4b_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_4b.png"));
		
		triagram_5_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_5.png"));
		triagram_5a_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_5a.png"));
		triagram_5b_t=new Texture(Gdx.files.internal("triagrams/triangle_diag_5b.png"));
		
		combination_equation_t=new Texture(Gdx.files.internal("combination_equation.png"));
	}

	@Override
	public void render(float delta) {
		generic_book_render(delta);
		
		batch.begin();
		if (page==3){
			batch.draw(triagram_1_t, 5, 275);
		}
		if (page==4){
			batch.draw(triagram_1_t, 5, 275);
		}
		if (page==5){
			batch.draw(triagram_1a_t, 5, 275);
		}
		if (page==6){
			batch.draw(triagram_1b_t, 5, 275);
		}
		if (page==7){
			batch.draw(triagram_2_t, 5, 275);
		}
		if (page==8){
			batch.draw(triagram_2a_t, 5, 275);
		}
		if (page==9){
			batch.draw(triagram_2b_t, 5, 275);
		}
		if (page==10){
			batch.draw(triagram_3_t, 5, 275);
		}
		if (page==11){
			batch.draw(triagram_3a_t, 5, 275);
		}
		if (page==12){
			batch.draw(triagram_3b_t, 5, 275);
		}
		if (page==13){
			batch.draw(triagram_4_t, 5, 275);
		}
		if (page==14){
			batch.draw(triagram_4a_t, 5, 275);
		}
		if (page==15){
			batch.draw(triagram_4b_t, 5, 275);
		}
		if (page==16){
			batch.draw(triagram_5_t, 5, 275);
		}
		if (page==17){
			batch.draw(triagram_5a_t, 5, 275);
		}
		if (page==18){
			batch.draw(triagram_5b_t, 5, 275);
		}
		if (page==19){
			batch.draw(combination_equation_t, 100, 210);
		}
		batch.end();
		
	}
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("circle",2);
		   turret_two=new Turret_Standard("circle");
		   turret_three=new Turret_Standard("circle");
		   turret_four=new Turret_Standard("circle");
		   
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
		else if (page==2){
			draw_textbox_two(text);
		}
		else{
			draw_textbox_one(text);
		}
	   }
	
	void draw_other_textbox(String text){
		   
		if (page==19){
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
			System.out.println("got this far!");
			show_the_text=true;
			the_text="When a turret fires once, there are two outcomes: either it fails or it fires.";
			if (page_time>4){
				time_to_move_on=true;
			}
			System.out.println("got this far!");
		}
		if (page==4){
			show_the_text=true;
			the_text="When a turret fires twice, start with the two outcomes you get with one shot.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==5){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==6){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .\nor it fails.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==7){
			show_the_text=true;
			the_text="When a turret fires three times, start with the four outcomes you get with two shots.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==8){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==9){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .\nor it fails.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==10){
			show_the_text=true;
			the_text="When a turret fires four times, start with the eight outcomes you get with three shots.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==11){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==12){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .\nor it fails.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==13){
			show_the_text=true;
			the_text="When a turret fires five times, start with the sixteen outcomes you get with four shots.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==14){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==15){
			show_the_text=true;
			the_text="Either the additional attempt succeeds . . .\nor it fails.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==16){
			show_the_text=true;
			the_text="And, of course, you can just keep adding rows to handle more attempts.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==17){
			show_the_text=true;
			the_text="So if you wanted to find the probability of two hits out of five shots, you would use 10 as the multiplier.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==18){
			show_the_text=true;
			the_text="And if you wanted to find the probability of four hits out of five shots, you would use 5 as the multiplier.";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==19){
			show_the_other_text=true;
			other_purpletext=true;
			the_other_text="(well actually irl we'd probably just use this formula to predict what Pascal's Triangle would say)";
			
			show_the_text=true;
			purpletext=true;
			the_text="(or, like, get a calculator and type '5C2' or '5C4' into it, and not think about the logic behind it at all)";
			
			if (page_time>4){
				time_to_move_on=true;
			}
		}
		if (page==20){
			show_the_other_text=true;
			the_other_text="That's true; drawing out the entire Triangle can also get impractical. Still, it's probably the simplest way to start understanding combinatorics.";
			
			show_the_text=true;
			purpletext=true;
			the_text="(plus if you can't remember the formula & don't have a calculator it's nice to have something to fall back on)";
			if (page_time>4){
				time_to_move_on=true;
			}
		}
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}