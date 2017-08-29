package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;



public class LibrarySelectScreen extends GenericSelectScreen {
	   
	private Texture banner_prob_t;
	private Texture banner_bayes_t;
	private Texture combined_t;
	
	   public LibrarySelectScreen(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			first_topic="Probability";
			last_topic="Bayes";
			
			
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
		   combined_t=new Texture(Gdx.files.internal("abutton_combined.png"));
	   }
	   
	   @Override
	   
	   
	   public void adjustToTopic(){
			
			prefs.putString("probdef_library_topic", TOPIC);
			if(TOPIC.equals("Probability")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_prob_t;
				one_t=combined_t;
			}
			if(TOPIC.equals("Bayes")){
				NUMBER_OF_LEVELS=4;
				banner_t=banner_bayes_t;
			}
			
		}
	   
	   @Override
	   
		public void go_forward(){
			
			arrowsound.play();
			if (TOPIC.equals("Probability")){
				TOPIC="Bayes";
			}
		}
		
	   @Override
	   
		public void go_back(){
			
			arrowsound.play();
			if (TOPIC.equals("Bayes")){
				TOPIC="Probability";
			}
		}
	   
	   @Override
	   public void render(float delta){
		   generic_render();
		   if (Gdx.input.justTouched()){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new BookScreen_Combine(game, true));
				dispose();
			   }
		   }
	   }
	   
		@Override
		public void dispose() {

			generic_select_dispose();
			
			
		}
	   
}
