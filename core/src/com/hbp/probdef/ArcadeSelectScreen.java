package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;



public class ArcadeSelectScreen extends GenericSelectScreen {

	
	   public ArcadeSelectScreen(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			first_topic="Basic";
			last_topic="Holos";
			
			
			if (prefs.contains("probdef_library_topic")){
				prefs.putString("probdef_library_topic", "Basic");
			}
			
			TOPIC=prefs.getString("probdef_library_topic");
			
			System.out.println("topic is somehow "+TOPIC);
			
			adjustToTopic();
			check_first_last();
			set_up_level_button_positions(NUMBER_OF_LEVELS);
			buttony_font=new BitmapFont(Gdx.files.internal("regular_font/gravity_20.fnt"));
			buttony_font.setColor(Color.BLACK);

	   }
	   
	   
	   @Override
	   
	   public void load_in_banner_textures(){
		   //banner_basic_t=new Texture(Gdx.files.internal("banner_probability.png"));
		   //banner_holo_t=new Texture(Gdx.files.internal("banner_bayes.png"));
		   //banner_deduction_t=new Texture(Gdx.files.internal("banner_pascal.png"));
	   }
	   
	   @Override
	   
	   public void load_in_button_textures(){
		   
	   }
	   
	   
	   @Override
	   
	   void set_up_level_button_positions(int how_many){
		   if (how_many==5|| how_many==3){
				
			   one_r = new Rectangle();
				one_r.x=90;
				one_r.y=230;
				one_r.height=60;
				one_r.width=140;
			   
				two_r = new Rectangle();
				two_r.x=10;
				two_r.y=140;
				two_r.height=60;
				two_r.width=140;
				
				three_r = new Rectangle();
				three_r.x=170;
				three_r.y=140;
				three_r.height=60;
				three_r.width=140;
				
		   }
		   if (how_many==5){
				four_r = new Rectangle();
				four_r.x=10;
				four_r.y=50;
				four_r.height=60;
				four_r.width=140;
				
				five_r = new Rectangle();
				five_r.x=170;
				five_r.y=50;
				five_r.height=60;
				five_r.width=140;
			}
		   if (how_many==6){
				
			   one_r = new Rectangle();
				one_r.x=10;
				one_r.y=230;
				one_r.height=60;
				one_r.width=140;
				
				two_r = new Rectangle();
				two_r.x=170;
				two_r.y=230;
				two_r.height=60;
				two_r.width=140;
			   
				three_r = new Rectangle();
				three_r.x=10;
				three_r.y=140;
				three_r.height=60;
				three_r.width=140;
				
				four_r = new Rectangle();
				four_r.x=170;
				four_r.y=140;
				four_r.height=60;
				four_r.width=140;
				
				five_r = new Rectangle();
				five_r.x=10;
				five_r.y=50;
				five_r.height=60;
				five_r.width=140;
				
				six_r = new Rectangle();
				six_r.x=170;
				six_r.y=50;
				six_r.height=60;
				six_r.width=140;
			}
	   }
	   
	   @Override
	   
	   
	   public void adjustToTopic(){
			
			prefs.putString("probdef_library_topic", TOPIC);
			if(TOPIC.equals("Basic")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_blank_t;
				banner_s="Basic";
				one_s="Intro";
				one_double_liner=false;
				two_s="Circles";
				two_double_liner=false;
				three_s="Reversed";
				three_double_liner=false;
				four_s="Capture";
				four_double_liner=false;
				five_s="Survive";
				five_double_liner=false;
			}
			if(TOPIC.equals("Holos")){
				NUMBER_OF_LEVELS=3;
				banner_t=banner_blank_t;
				banner_s="Holos";
				one_s="Intro";
				one_double_liner=false;
				two_s="Minority Holo";
				two_double_liner=true;
				three_s="Majority Holo";
				three_double_liner=true;
				four_s="Witchfinder";
				four_double_liner=false;
				five_s="Groups";
				five_double_liner=false;
			}
			if(TOPIC.equals("Deduction")){
				NUMBER_OF_LEVELS=6;
				banner_t=banner_blank_t;
				banner_s="Deduction";
				one_s="Intro I";
				one_double_liner=false;
				two_s="Intro II";
				two_double_liner=false;
				three_s="Possibility";
				three_double_liner=false;
				four_s="Plausibility";
				four_double_liner=false;
				five_s="Scarcity";
				five_double_liner=false;
				six_s="Certainty";
				six_double_liner=false;
			}
			
		}
	   
	   @Override
	   
		public void go_forward(){
			
			arrowsound.play();
			if (TOPIC.equals("Basic")){
				TOPIC="Holos";
			}
			else if (TOPIC.equals("Holos")){
				TOPIC="Deduction";
			}
		}
		
	   @Override
	   
		public void go_back(){
			
			arrowsound.play();
			if (TOPIC.equals("Holos")){
				TOPIC="Basic";
			}
			else if (TOPIC.equals("Deduction")){
				TOPIC="Holos";
			}
		}
	   
	   @Override
	   public void render(float delta){
		   generic_render();
		   if (Gdx.input.justTouched()&& TOPIC.equals("Basic")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Basic_Intro(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Basic_Circles(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Basic_Reversed(game, true));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Holos")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Holos_Intro(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Holos_Minority(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Holos_Majority(game, true));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Pascal")){
			   if (one_r.contains(tp_x,tp_y)){
			   }
			   if (two_r.contains(tp_x,tp_y)){
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
