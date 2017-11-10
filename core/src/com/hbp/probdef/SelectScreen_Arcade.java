package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;



public class SelectScreen_Arcade extends SelectScreen {

	
	   public SelectScreen_Arcade(final ProbDef gam, boolean play_the_sound){
			super(gam, play_the_sound);
			
			if (hardcoded_opt_packagename.equals("Inference")){
				first_topic="Basic";
				last_topic="Deduction";
			}
			if (hardcoded_opt_packagename.equals("Combination")){
				first_topic="Titanium";
				last_topic="Challenge";
			}
			
			System.out.println("-----");
			System.out.println(prefs.contains("probdef_arcade_top"));
			System.out.println(prefs.getString("probdef_arcade_top"));
			System.out.println("-----");
			
			title_t=new Texture(Gdx.files.internal("TITLE_ARCADE.png"));
			
			System.out.println("topic is somehow "+TOPIC);
			
			adjustToTopic();
			check_first_last();
			set_up_level_button_positions(NUMBER_OF_LEVELS);
			buttony_font=new BitmapFont(Gdx.files.internal("regular_font/gravity_20.fnt"));
			buttony_font.setColor(Color.BLACK);
			
			scores_exist=true;
			init_scores();
	   }
	   
	   void init_scores(){
		   Array<String> names=new Array<String>();
		   
		   names.add("Score_Basic_Intro");
		   names.add("Score_Basic_Circles");
		   names.add("Score_Basic_Reversed");
		   names.add("Score_Basic_Capture");
		   names.add("Score_Basic_Survive");
		   
		   names.add("Score_Decoy_Intro");
		   names.add("Score_Decoy_Majority");
		   names.add("Score_Decoy_Minority");
		   names.add("Score_Decoy_PerfectTest");
		   names.add("Score_Decoy_Groups");
		   
		   names.add("Score_Deduction_Intro");
		   names.add("Score_Deduction_Blatant");
		   names.add("Score_Deduction_Finale");
		   names.add("Score_Deduction_Subtle");
		   names.add("Score_Deduction_NAF");
		   
		   for (String name:names){
			   if (!prefs.contains(name)){
				   prefs.putInteger(name, 0);
			   }
		   }
		   prefs.flush();
	   }
	   
	   
	   @Override
	   
	   void screen_specific_initial_adjustment(){
		   if (hardcoded_opt_packagename.equals("Combination")){
			   if (!prefs.contains("probdef_arcade_combination_top")){
				   prefs.putString("probdef_arcade_combination_top", "Titanium");
					prefs.flush();
			   }
			   TOPIC=prefs.getString("probdef_arcade_combination_top");
		   }
		   else{
			   if (!prefs.contains("probdef_arcade_top")){
					prefs.putString("probdef_arcade_top", "Basic");
					prefs.flush();
				}
			   TOPIC=prefs.getString("probdef_arcade_top");
		   }
		   //TOPIC=first_topic;
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
			
			prefs.putString("probdef_arcade_top", TOPIC);
			prefs.flush();
			if(TOPIC.equals("Basic")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Basic";
				
				one_s="Intro";
				one_double_liner=false;
				score_one=prefs.getInteger("Score_Basic_Intro");
				
				two_s="Circles";
				two_double_liner=false;
				score_two=prefs.getInteger("Score_Basic_Circles");
				
				three_s="Reversed";
				three_double_liner=false;
				score_three=prefs.getInteger("Score_Basic_Reversed");
				
				four_s="Capture";
				four_double_liner=false;
				score_four=prefs.getInteger("Score_Basic_Capture");
				
				five_s="Survive";
				five_double_liner=false;
				score_five=prefs.getInteger("Score_Basic_Survive");
			}
			if(TOPIC.equals("Decoys")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Decoys";
				
				one_s="Intro";
				one_double_liner=false;
				score_one=prefs.getInteger("Score_Decoy_Intro");
				
				
				
				two_s="Perfect Test";
				two_double_liner=false;
				score_two=prefs.getInteger("Score_Decoy_PerfectTest");
				
				three_s="Groups";
				three_double_liner=false;
				score_three=prefs.getInteger("Score_Decoy_Groups");
				
				four_s="Minority Decoy";
				four_double_liner=true;
				score_four=prefs.getInteger("Score_Decoy_Minority");
				
				five_s="Majority Decoy";
				five_double_liner=true;
				score_five=prefs.getInteger("Score_Decoy_Majority");
			}
			if(TOPIC.equals("Deduction")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Deduction";
				
				one_s="Intro";
				one_double_liner=false;
				score_one=prefs.getInteger("Score_Deduction_Intro");
				
				two_s="Blatant Evidence";
				two_double_liner=true;
				score_two=prefs.getInteger("Score_Deduction_Blatant");
				
				three_s="Finale";
				three_double_liner=false;
				score_three=prefs.getInteger("Score_Deduction_Finale");
				
				four_s="Subtle Evidence";
				four_double_liner=true;
				score_four=prefs.getInteger("Score_Deduction_Subtle");
				
				five_s="Near and Far";
				five_double_liner=false;
				score_five=prefs.getInteger("Score_Deduction_NAF");
			}
			
			
			
			if(TOPIC.equals("Titanium")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Titanium";
				one_s="Intro";
				one_double_liner=false;
				two_s="Tradeoff";
				two_double_liner=false;
				three_s="Pure Titanium";
				three_double_liner=true;
				four_s="";//"Titanium, Decoys";
				four_double_liner=true;
				five_s="";//"Titanium Decoys";
				five_double_liner=true;
			}
			if(TOPIC.equals("Shields")){
				NUMBER_OF_LEVELS=6;
				banner_t=banner_blank_t;
				banner_s="Shields";
				one_s="";//"Intro I";
				one_double_liner=false;
				two_s="";//"Intro II";
				two_double_liner=false;
				three_s="";//"Doubles";
				three_double_liner=false;
				four_s="";//"Shuffled";
				four_double_liner=true;
				five_s="";//"Binary";
				five_double_liner=true;
				six_s="";//"Shields and Decoys";
				six_double_liner=true;
			}
			if(TOPIC.equals("Challenge")){
				NUMBER_OF_LEVELS=6;
				banner_t=banner_blank_t;
				banner_s="Challenge";
				one_s="";//"Shields and Titanium";
				one_double_liner=false;
				two_s="";//"Shielded Titanium";
				two_double_liner=false;
				three_s="";//" ";
				three_double_liner=false;
				four_s="";//" ";
				four_double_liner=true;
				five_s="";//"Combination";
				five_double_liner=true;
				six_s="";//"Synthesis";
				six_double_liner=true;
			}
			if(TOPIC.equals("Conditional")){
				NUMBER_OF_LEVELS=5;
				banner_t=banner_blank_t;
				banner_s="Conditional";
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
			
			if(TOPIC.equals("Dukkha")){
				NUMBER_OF_LEVELS=6;
				banner_t=banner_blank_t;
				banner_s="Dukkha";
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
			
		}
	   
	   @Override
	   
		void go_forward(){
			
			arrowsound.play();
			if (TOPIC.equals("Basic")){
				TOPIC="Decoys";
			}
			else if (TOPIC.equals("Decoys")){
				TOPIC="Deduction";
				
			}
			
			
			
			
			
			
			else if (TOPIC.equals("Titanium")){
				TOPIC="Shields";
			}
			
			else if (TOPIC.equals("Shields")){
				TOPIC="Challenge";
			}
			
			
			
			else if (TOPIC.equals("Deduction")){
				TOPIC="Dukkha";
			}
			
			else if (TOPIC.equals("Dukkha")){
				TOPIC="Induction";
			}
		}
		
	   @Override
	   
		void go_back(){
			
		   System.out.println("FROM "+TOPIC);
			arrowsound.play();
			if (TOPIC.equals("Decoys")){
				TOPIC="Basic";
			}
			else if (TOPIC.equals("Deduction")){
				TOPIC="Decoys";
			}
			
			
			
			
			
			else if (TOPIC.equals("Titanium")){
				TOPIC="Decoys";
			}
			
			else if (TOPIC.equals("Shields")){
				TOPIC="Titanium";
			}
			else if (TOPIC.equals("Challenge")){
				TOPIC="Shields";
			}
			else if (TOPIC.equals("Conditional")){
				TOPIC="Challenge";
			}
			
			else if (TOPIC.equals("Dukkha")){
				TOPIC="Deduction";
			}
			else if (TOPIC.equals("Induction")){
				TOPIC="Dukkha";
			}
			System.out.println("TO "+TOPIC);
		}
	   
	   @Override
	   public void render(float delta){
		   generic_render();
		   if (Gdx.input.justTouched()&& TOPIC.equals("Basic")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Basic_Intro(game, false));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Basic_Circles(game, false));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Basic_Reversed(game, false));
				   dispose();
			   }
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Basic_Capture(game, false));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Basic_Survive(game, false));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Decoys")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Decoy_Intro(game, false));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Decoy_PerfectTest(game, false));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Decoy_Groups(game, false));
				   dispose();
			   }
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Decoy_Minority(game, false));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Decoy_Majority(game, false));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Titanium")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Titanium_Intro(game, false));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Titanium_Spread(game, false));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Prob_Titanium_Pure(game, false));
				   dispose();
			   }
		   }
		   if (Gdx.input.justTouched()&& TOPIC.equals("Deduction")){
			   if (one_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Intro(game, false));
				   dispose();
			   }
			   if (two_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Blatant(game, false));
				   dispose();
			   }
			   if (three_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Finale(game, false));
				   dispose();
			   }
			   if (four_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_Subtle(game, false));
				   dispose();
			   }
			   if (five_r.contains(tp_x,tp_y)){
				   game.setScreen(new ArcadeScreen_Bayes_Deduction_NAF(game, false));
				   dispose();
			   }
		   }
	   }
	   
		@Override
		public void dispose() {

			generic_select_dispose();
			
			
		}
	   
}
