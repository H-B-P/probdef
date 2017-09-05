package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class LibrarySelectScreen extends GenericSelectScreen {
	   
	private Texture banner_prob_t;
	private Texture banner_bayes_t;
	private Texture banner_pascal_t;
	
	   public LibrarySelectScreen(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			first_topic="Probability";
			last_topic="Pascal";
			
			
			if (prefs.contains("probdef_library_topic")){
				prefs.putString("probdef_library_topic", "Probability");
			}
			
			TOPIC=prefs.getString("probdef_library_topic");
			
			System.out.println("topic is somehow "+TOPIC);
			
			adjustToTopic();
			check_first_last();
			set_up_level_button_positions(NUMBER_OF_LEVELS);
	   }
	   
	   
	   @Override
	   
	   public void load_in_banner_textures(){
		   banner_prob_t=new Texture(Gdx.files.internal("banner_probability.png"));
		   banner_bayes_t=new Texture(Gdx.files.internal("banner_bayes.png"));
	   }
	   
	   @Override
	   
	   public void load_in_button_textures(){
		   
	   }
	   
	   @Override
	   
	   
	   public void adjustToTopic(){
			
			prefs.putString("probdef_library_topic", TOPIC);
			if(TOPIC.equals("Probability")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_prob_t;
				one_s="Combining Events";
				one_double_liner=true;
				two_s="Probability Trees";
				two_double_liner=true;
				three_s="Hypothesis Testing";
				three_double_liner=true;
				four_s="";
				five_s="";
				six_s="";
			}
			if(TOPIC.equals("Bayes")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_bayes_t;
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
				banner_t=banner_prob_t;
				one_s="More Trees";
				one_double_liner=false;
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
			
			arrowsound.play();
			if (TOPIC.equals("Probability")){
				TOPIC="Bayes";
			}
			else if (TOPIC.equals("Bayes")){
				TOPIC="Pascal";
			}
		}
		
	   @Override
	   
		public void go_back(){
			
			arrowsound.play();
			if (TOPIC.equals("Bayes")){
				TOPIC="Probability";
			}
			else if (TOPIC.equals("Pascal")){
				TOPIC="Bayes";
			}
		}
	   
	   @Override
	   public void render(float delta){
		   generic_render();
		   if (Gdx.input.justTouched()&& TOPIC.equals("Probability")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Combine(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Tree(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_HypothesisTests(game, true));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Bayes")){
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Pascal")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_MoreTrees(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Shortcut(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   
			   }
		   }
	   }
	   
		@Override
		public void dispose() {

			generic_select_dispose();
			
			
		}
	   
}
