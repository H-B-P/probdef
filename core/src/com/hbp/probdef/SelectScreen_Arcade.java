package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;



public class SelectScreen_Arcade extends SelectScreen {

	
	   public SelectScreen_Arcade(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			first_topic="Basic";
			last_topic="Titanium";
			
			
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
				NUMBER_OF_LEVELS=5;
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
			if(TOPIC.equals("Decoys")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Decoys";
				one_s="Intro";
				one_double_liner=false;
				two_s="Minority Decoy";
				two_double_liner=true;
				three_s="Majority Decoy";
				three_double_liner=true;
				four_s="Witch Hunt";
				four_double_liner=false;
				five_s="Groups";
				five_double_liner=false;
			}
			if(TOPIC.equals("Deduction")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Deduction";
				one_s="Intro";
				one_double_liner=false;
				two_s="Blatant Evidence";
				two_double_liner=true;
				three_s="Finale";
				three_double_liner=false;
				four_s="Subtle Evidence";
				four_double_liner=true;
				five_s="Near and Far";
				five_double_liner=false;
			}
			if(TOPIC.equals("Imperfection")){
				NUMBER_OF_LEVELS=6;
				banner_t=banner_blank_t;
				banner_s="Imperfexion";
				one_s="";//"Flawed Priors";
				one_double_liner=false;
				two_s="";//"Incomplete Priors";
				two_double_liner=false;
				three_s="";//"Unreliable Vanes";
				three_double_liner=false;
				four_s="";//"Impaired Vanes";
				four_double_liner=false;
				five_s="";//"Red Shield";
				five_double_liner=false;
				six_s="";//"Blue Shield";
				six_double_liner=false;
			}
			if(TOPIC.equals("Induction")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Induction";
				one_s="";
				one_double_liner=false;
				two_s="";
				two_double_liner=false;
				three_s="";
				three_double_liner=false;
				four_s="";
				four_double_liner=false;
				five_s="";
				five_double_liner=false;
			}
			if(TOPIC.equals("Titanium")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Titanium";
				one_s="";//"Intro";
				one_double_liner=false;
				two_s="";//"Barbell";
				two_double_liner=false;
				three_s="";//"Spread";
				three_double_liner=false;
				four_s="";//"Titanium, Decoys";
				four_double_liner=true;
				five_s="";//"Titanium Decoys";
				five_double_liner=true;
			}
			
		}
	   
	   @Override
	   
		public void go_forward(){
			
			arrowsound.play();
			if (TOPIC.equals("Basic")){
				TOPIC="Decoys";
			}
			else if (TOPIC.equals("Decoys")){
				TOPIC="Deduction";
			}
			else if (TOPIC.equals("Deduction")){
				TOPIC="Imperfection";
			}
			else if (TOPIC.equals("Imperfection")){
				TOPIC="Induction";
			}
			else if (TOPIC.equals("Induction")){
				TOPIC="Titanium";
			}
		}
		
	   @Override
	   
		public void go_back(){
			
			arrowsound.play();
			if (TOPIC.equals("Decoys")){
				TOPIC="Basic";
			}
			else if (TOPIC.equals("Deduction")){
				TOPIC="Decoys";
			}
			else if (TOPIC.equals("Imperfection")){
				TOPIC="Deduction";
			}
			else if (TOPIC.equals("Induction")){
				TOPIC="Imperfection";
			}
			else if (TOPIC.equals("Titanium")){
				TOPIC="Induction";
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
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Basic_Capture(game, true));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Basic_Survive(game, true));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Decoys")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Decoy_Intro(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Decoy_Minority(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Decoy_Majority(game, true));
				   dispose();
			   }
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Decoy_WitchHunt(game, true));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Decoy_Groups(game, true));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Deduction")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Intro(game, true));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Blatant(game, true));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Finale(game, true));
				   dispose();
			   }
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Subtle(game, true));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_NAF(game, true));
				   dispose();
			   }
		   }
	   }
	   
		@Override
		public void dispose() {

			generic_select_dispose();
			
			
		}
	   
}
