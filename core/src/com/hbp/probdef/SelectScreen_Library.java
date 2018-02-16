package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;



public class SelectScreen_Library extends SelectScreen {
	
	   public SelectScreen_Library(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			
			title_t=new Texture(Gdx.files.internal("TITLE_LIBRARY.png"));
			
			if (hardcoded_opt_packagename.equals("Inference")){
				first_topic="Probability";
				last_topic="Bayes";
			}
			if (hardcoded_opt_packagename.equals("Combination")){
				first_topic="Probability";
				last_topic="Pascal";
			}
			if (hardcoded_opt_packagename.equals("Bayesian")){
				first_topic="Bayes";
				last_topic="Bayes";
			}
			
			System.out.println("topic is somehow "+TOPIC);
			
			adjustToTopic();
			check_first_last();
			set_up_level_button_positions(NUMBER_OF_LEVELS);
	   }
	   
	   @Override
	   
	   void screen_specific_initial_adjustment(){
		   if (hardcoded_opt_packagename.equals("Combination")){
			   if (!prefs.contains("probdef_library_combination_top")){
				   prefs.putString("probdef_library_combination_top", "Probability");
					prefs.flush();
			   }
			   TOPIC=prefs.getString("probdef_library_combination_top");
		   }
		   else if (hardcoded_opt_packagename.equals("Bayesian")){
			   if (!prefs.contains("probdef_library_bayesian_top")){
				   prefs.putString("probdef_library_bayesian_top", "Bayes");
					prefs.flush();
			   }
			   TOPIC=prefs.getString("probdef_library_bayesian_top");
		   }
		   else{
			   if (!prefs.contains("probdef_library_top")){
					prefs.putString("probdef_library_top", "Probability");
					prefs.flush();
				}
			   TOPIC=prefs.getString("probdef_library_top");
		   }
			
		}
	   
	   
	   @Override
	   
	   public void load_in_banner_textures(){
		   
	   }
	   
	   @Override
	   
	   public void load_in_button_textures(){
		   
	   }
	   
	   @Override
	   
	   
	   public void adjustToTopic(){
			
			prefs.putString("probdef_library_topic", TOPIC);
			if(TOPIC.equals("Probability")){
				
					NUMBER_OF_LEVELS=3;
				banner_t=banner_blank_t;
				banner_s="Probability";
				one_s="Combining Events";
				one_double_liner=true;
				two_s="Probability Trees";
				two_double_liner=true;
				if (hardcoded_opt_packagename.equals("Inference")){
					three_s="Hypothesis Testing";
					three_double_liner=true;
				}
				else{
					three_s="The Naive Approach";
					three_double_liner=true;
				}
				four_s="";
				five_s="";
				six_s="";
			}
			if(TOPIC.equals("Bayes")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_blank_t;
				banner_s="Bayes";
				one_s="Expected Value";
				one_double_liner=true;
				two_s="Eliminate & Normalise";
				two_double_liner=true;
				three_s="Bayes' Theorem";
				three_double_liner=true;
				four_s="";
				five_s="";
				six_s="";
			}
			if(TOPIC.equals("Pascal")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_blank_t;
				banner_s="Pascal";
				one_s="Listing Outcomes";
				one_double_liner=true;
				two_s="A Shortcut";
				two_double_liner=false;
				three_s="Pascal's Triangle";
				three_double_liner=true;
				four_s="";
				five_s="";
				six_s="";
			}
			
		}
	   
	   @Override
	   
		public void go_forward(){
			
			arrowsound.play(option_sfx_volume);
			if (TOPIC.equals("Probability")){
				if (hardcoded_opt_packagename.equals("Combination")){
					TOPIC="Pascal";
				}
				else{
					TOPIC="Bayes";
				}
			}
		}
		
	   @Override
	   
		public void go_back(){
			
			arrowsound.play(option_sfx_volume);
			if (TOPIC.equals("Bayes")){
				TOPIC="Probability";
			}
			if (TOPIC.equals("Pascal")){
				TOPIC="Probability";
			}
		}
	   
	   @Override
	   
	   void set_up_level_button_positions(int how_many){
				
			   one_r = new Rectangle();
				one_r.x=90;
				one_r.y=230;
				one_r.height=60;
				one_r.width=140;
			   
				two_r = new Rectangle();
				two_r.x=90;
				two_r.y=140;
				two_r.height=60;
				two_r.width=140;
				
				three_r = new Rectangle();
				three_r.x=90;
				three_r.y=50;
				three_r.height=60;
				three_r.width=140;
	   }
	   
	   @Override
	   public void render(float delta){
		   generic_render();
		   if (Gdx.input.justTouched()&& TOPIC.equals("Probability")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Prob_Combine(game));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Prob_Tree(game));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   
				   if(hardcoded_opt_packagename.equals("Inference")){
					   game.setScreen(new BookScreen_Prob_HypothesisTests(game));
					   dispose();
				   }
				   else{
					   game.setScreen(new BookScreen_Prob_Naive(game));
					   dispose();
				   }
				   
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Bayes")){
			   if (one_r.contains(tp_x, tp_y)){
				   game.setScreen(new BookScreen_Bayes_ExpectedValue(game));
				   dispose();
			   }
			   if (two_r.contains(tp_x, tp_y)){
				   game.setScreen(new BookScreen_Bayes_EliminateAndNormalise(game));
				   dispose();
			   }
			   if (three_r.contains(tp_x, tp_y)){
				   game.setScreen(new BookScreen_Bayes_BayesTheorem(game));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Pascal")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Prob_ListingOutcomes(game));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Prob_Shortcut(game));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Prob_Triangle(game));
				   dispose();
			   }
		   }
	   }
	   
		@Override
		public void dispose() {

			generic_select_dispose();
			
			
		}
	   
}
