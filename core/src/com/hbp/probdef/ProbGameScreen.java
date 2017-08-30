package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This is the root of gameplay that's based on standard turrets (i.e. not Bayes). Unmodified, it doubles as the tutorial.
 */

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.hbp.probdef.Kaboom;
import com.hbp.probdef.Mine;
import com.hbp.probdef.Turret;
import com.badlogic.gdx.audio.Sound;


import com.hbp.probdef.ProbDef;

public class ProbGameScreen extends SpaceyScreen {
	
	final ProbDef game;
	
	Rectangle menu_button_r;
	Texture menu_button_t;
	Rectangle fire_button_r;

	Texture fire_button_t;
	Texture blue_button_trim_t;

	Texture orange_button_trim_t;
   
   
   Texture mine_t;
   Texture shieldmine_t;
   Texture mine_shield_one_t;
   Texture mine_shield_two_t;
   Texture mine_shield_three_t;
   Texture mine_shield_four_t;
   
   public Array<Mine> mines;
   public Array<Mine> regularmines;
   public Array<ShieldMine> shieldmines;
   public Array<Kaboom> explosions;
   public Array<Turret> turrets;
   public Array<Turret_Standard> turrets_standard;
   public Array<Dot> dots;
   
   private Texture destroy_dot_t;
   private Texture capture_dot_t;

   

   private Texture explosion_t;
   
   Turret turret_one;
   Turret turret_two;
   Turret turret_three;
   Turret turret_four;
   
   Turret currently_active_turret;
   
   int currently_active_turret_no;
   
   float effective_delta;
	
   private Texture pause_t;
   
   private Texture detaining_t;
   
   int captured;
   int destroyed;
   int shields;
   int minecount;
   int score;
   
   private Texture textbox_one_t;
   
   private Texture textbox_two_t;
   
   String the_text;
   boolean show_the_text;
   
   boolean suppress_freezes;
   
   private float end_time;
   
	private SpriteBatch batch;
	
	private Rectangle screen_proper;
	
	private boolean infuriatingly_specific_bool;
	
	BitmapFont grayfont;
	BitmapFont redfont;
	BitmapFont bluefont;
	BitmapFont greenfont;
	
	Sound minesplode;
	Sound hitshield;
	Sound capture;
	Sound fire;
	
	boolean greentext;
	
	public ProbGameScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
		batch= new SpriteBatch();
		
	      menu_button_r=new Rectangle(230,420,100,40);
	      fire_button_r=new Rectangle(10,420,100,40);

		menu_button_t=new Texture(Gdx.files.internal("ingame_button_menu.png"));
		fire_button_t=new Texture(Gdx.files.internal("ingame_button_fire.png"));

	      blue_button_trim_t=new Texture(Gdx.files.internal("ingame_blue_button_trim.png"));
	      orange_button_trim_t=new Texture(Gdx.files.internal("ingame_orange_button_trim.png"));

		   explosion_t = new Texture(Gdx.files.internal("explosion.png"));

		      mine_t=new Texture(Gdx.files.internal("mine.png"));
		      shieldmine_t=new Texture(Gdx.files.internal("mine_shield.png"));
		      
		      mine_shield_one_t= new Texture(Gdx.files.internal("shield_layer_one.png"));
		      mine_shield_two_t= new Texture(Gdx.files.internal("shield_layer_two.png"));
		      mine_shield_three_t= new Texture(Gdx.files.internal("shield_layer_three.png"));
		      mine_shield_four_t= new Texture(Gdx.files.internal("shield_layer_four.png"));;

	      minesplode=Gdx.audio.newSound(Gdx.files.internal("other_sfx/186945__readeonly__cannon-boom5.wav"));
	      hitshield=Gdx.audio.newSound(Gdx.files.internal("other_sfx/268553_cydon_bang_001.mp3"));
	      capture=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344519__jeremysykes__pick02.wav"));
	      fire=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344524__jeremysykes__gunshot02.wav"));
	      
	      
	      mines = new Array<Mine>();
	      regularmines = new Array<Mine>();
	      shieldmines = new Array<ShieldMine>();
	      explosions = new Array<Kaboom>();
	      turrets= new Array<Turret>();
	      turrets_standard= new Array<Turret_Standard>();
	      dots = new Array<Dot>();
	      
	      
	      
	      pause_t=new Texture(Gdx.files.internal("pause_symbol.png"));
	      
	      destroy_dot_t=new Texture(Gdx.files.internal("shot_destroy.png"));
	      capture_dot_t=new Texture(Gdx.files.internal("shot_capture.png"));
	      
	      detaining_t=new Texture(Gdx.files.internal("mine_capturing_circle.png"));
	      
	      screen_proper=new Rectangle();
	      screen_proper.x=0;
	      screen_proper.y=0;
	      screen_proper.height=480;
	      screen_proper.width=320;
	      
	      captured=0;
	      destroyed=0;
	      
	      minecount=20;
	      shields=100;
	      
	      the_text="";
	      show_the_text=false;
	      suppress_freezes=false;
	      textbox_one_t=new Texture(Gdx.files.internal("textbox_1.png"));
	      textbox_two_t=new Texture(Gdx.files.internal("textbox_2.png"));

	      turret_setup();
	      
	      level_specific_music_setup();
	      
	      grayfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
			grayfont.setColor(new Color(0.6f, 0.6f, 0.6f, 1.0f));
			redfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
			redfont.setColor(new Color(1.0f, 0.1f, 0.1f, 1.0f));
			bluefont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
			bluefont.setColor(new Color(0.1f, 0.1f, 1.0f, 1.0f));
			greenfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
			greenfont.setColor(new Color(0.1f, 0.6f, 0.1f, 1.0f));
	      
	      infuriatingly_specific_bool=false; //so infuriating
	      
	      greentext=false;
	      
	      
	      
	}
	
	//---Level-specific functions---
	
	//--Level-specific_setup--
	
	void level_specific_music_setup(){
		bgm=Gdx.audio.newMusic(Gdx.files.internal("MCS_Dampen.mp3"));
		bgm.setLooping(true);
		bgm.play();
	}
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("pentagon");
		   turret_four=new Turret_Standard("hexagon");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	   
	//--What actually happens in the level?--
	
	   void level_specific_events(){
		   if (seconds==2){
				spawnMine(-1, 15);
				spawnMine(1, 15);
				
			}
			if (seconds==12){
				spawnMine(-3,65);
				spawnMine(3,65);
			}
			if (seconds==22){
				spawnMine(0,65);
				spawnMine(2,65);
				spawnMine(-2,65);
			}
			if (seconds==30){
				spawnMine(0,100);
				spawnMine(2,65);
				spawnMine(-2,50);
			}
			if (seconds==36){
				spawnMine(2,65);
				spawnMine(0,65);
				spawnMine(-3, 50);
			}
			if (seconds==40){
				spawnMine(-3,50);
				spawnMine(-1, 50);
				spawnMine(1,50);
				spawnMine(3, 50);
			}
			if (seconds==46){
				spawnMine(-3,100);
				spawnMine(-1, 100);
				spawnMine(2,100);
			}
			if (minecount==0){
				game.setScreen(new TitleScreen(game, true));
  			  dispose();
			}
	   }
	   
	   void level_specific_timeline(){
		   show_the_text=false;
		   suppress_freezes=false;
		   if (total_time>1 && total_time<5){
			   suppress_freezes=true;
			   show_the_text=true;
				the_text="Mines come towards your ship. We want to stop them before they hit.";
		   }
			if (total_time>5 && total_time<9){
				suppress_freezes=true;
				show_the_text=true;
				the_text="Every two seconds, time freezes, and you can target mines.";
			}
			
			   if (total_time>9 && total_time<11.5){
				   suppress_freezes=true;
				   show_the_text=true;
				   the_text="Click on a mine while time is frozen to target it.";
			   }
			   if (total_time>11.5 && total_time<12.5){
				   show_the_text=true;
				   if(TIMESPEED==0){
					   if (currently_active_turret_no==0){
						   the_text="Click on a mine while time is frozen to target it.";
					   }
					   else if (currently_active_turret_no==1){
						   the_text="Triangle Turrets fail 30% of the time, capture 30% of the time, and destroy 40% of the time.";
					   }
					   else if (currently_active_turret_no==2){
						   the_text="Square Turrets fail 20% of the time, capture 20% of the time, and destroy 60% of the time.";
					   }
					   else if (currently_active_turret_no==3){
						   the_text="Pentagon Turrets fail 10% of the time, capture 10% of the time, and destroy 80% of the time.";
					   }
					   else if (currently_active_turret_no==4){
						   the_text="Hexagon Turrets will always successfully destroy a mine, and never fail or capture.";
					   }
					   else if (currently_active_turret_no==5){
						   the_text="Once you're done targeting, click the fire button (at the top of the screen) to launch a volley.";
					   }
				   }
			   }
			   if (total_time>14 && total_time<15 && TIMESPEED==0){
				   show_the_text=true;
				   if (currently_active_turret_no<5 && !infuriatingly_specific_bool){
					   the_text="You can remind yourself of the probabilities at any time by hovering your mouse over a turret.";
				   }
				   else{
					   infuriatingly_specific_bool=true;
					   the_text="If you change your mind before firing, click on the relevant turret to re-target it.";
				   }
			   }
			   if (total_time>18 && total_time<22){
				   show_the_text=true;
				   the_text="The percentage below a mine shows the probability it will survive the volley.";
			   }
			   if (total_time>28 && total_time<32){
				   show_the_text=true;
				   the_text="Mines won't always have the same speed, so prioritise.";
			   }
	   }
	   
	   void level_specific_HUD(){
		   font.draw(batch, "MINES: "+minecount, 90, 472, 140, 1, true);
			font.draw(batch, "CAPTURED: "+captured, 90, 455, 140, 1, true);
			font.draw(batch, "DESTROYED: "+ destroyed, 90, 437, 140, 1, true);
			font.draw(batch, "SHIELDS: "+shields, 90, 420, 140, 1, true);
	   }
	   //--Autocalc--
	   
	   void autocalc_and_display_survive(){
		   for (Mine regularmine:regularmines){
			   float survival=1.0f;
			   for (Turret_Standard turret_standard:turrets_standard){
				   if (turret_standard.targeted){
					   if (turret_standard.target_mine.equals(regularmine)){
						   survival=survival*turret_standard.fail_percent/100.0f;
					   }
				   }
			   }
			   grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", regularmine.rect.x-20, regularmine.rect.y-20, 81, 1, true);
		   }
		   for (ShieldMine shieldmine:shieldmines){
			   float four=0f;
			   float three=0f;
			   float two=0f;
			   float one=0f;
			   float zero=0f;
			   float destroy=0f;
			   float capture=0f;
			   
			   if (shieldmine.shields_rn==4){
				   four=1f;
			   }
			   if (shieldmine.shields_rn==3){
				   three=1f;
			   }
			   if (shieldmine.shields_rn==2){
				   two=1f;
			   }
			   if (shieldmine.shields_rn==1){
				   one=1f;
			   }
			   if (shieldmine.shields_rn==0){
				   zero=1f;
			   }
			   
			   for (Turret_Standard turret_standard:turrets_standard){
				   if (turret_standard.targeted){
					   if (turret_standard.target_mine.equals(shieldmine)){
						   destroy= destroy + zero*turret_standard.destroy_percent/100.0f;
						   capture= destroy + zero*turret_standard.destroy_percent/100.0f;
						   
						   zero= zero*turret_standard.fail_percent/100.0f + one*(1f-turret_standard.fail_percent/100.0f);
						   one= one*turret_standard.fail_percent/100.0f + two*(1f-turret_standard.fail_percent/100.0f);
						   two= two*turret_standard.fail_percent/100.0f + three*(1f-turret_standard.fail_percent/100.0f);
						   three=three*turret_standard.fail_percent/100.0f + four*(1f-turret_standard.fail_percent/100.0f);
						   four=four*turret_standard.fail_percent/100.0f;
						   
					   }
				   }
			   }
			   
			   float survival=zero+one+two+three+four;
			   
			   grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", shieldmine.rect.x-20, shieldmine.rect.y-20, 81, 1, true);
			   
		   }
	   }
	   
	   void autocalc_and_display_capture(){
		   for (Mine mine:mines){
			   float survival=1.0f;
			   float capture=0.0f;
			   for (Turret_Standard turret_standard:turrets_standard){
				   if (turret_standard.targeted){
					   if (turret_standard.target_mine.equals(mine)){
						   capture+=survival*turret_standard.capture_percent/100.0f;
						   survival=survival*turret_standard.fail_percent/100.0f;
					   }
				   }
			   }
			   bluefont.draw(batch, present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 80, 1, true);
		   }
	   }
	   
	   void autocalc_and_display_destroy(){
		   for (Mine mine:mines){
			   float survival=1.0f;
			   float destruction=0.0f;
			   for (Turret_Standard turret_standard:turrets_standard){
				   if (turret_standard.targeted){
					   if (turret_standard.target_mine.equals(mine)){
						   destruction+=survival*turret_standard.destroy_percent/100.0f;
						   survival=survival*turret_standard.fail_percent/100.0f;
					   }
				   }
			   }
			   redfont.draw(batch, present_float(destruction*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 80, 1, true);
		   }
	   }
	   
	   void autocalc_and_display(){
		   autocalc_and_display_survive();
	   }
	   
	   //---General Setup---
	   
	private void turret_setup(){
		level_specific_turret_setup();
	      
	      turret_one.rect.x=50;
	      turret_one.turret_no=1;
	      
	      turret_two.rect.x=110;
	      turret_two.turret_no=2;
	      
	      turret_three.rect.x=170;
	      turret_three.turret_no=3;
	      
	      turret_four.rect.x=230;
	      turret_four.turret_no=4;
	      
	      turrets.add(turret_one);
	      turrets.add(turret_two);
	      turrets.add(turret_three);
	      turrets.add(turret_four);
	      
	      for(Turret turret: turrets) {
	    	  turret.rect.y=ship_posn+410;
	    	  turret.rect.width=40;
	    	  turret.rect.height=40;
	      }
	      
	      currently_active_turret_no=0;
	}
	
	//---Spawning functions---
	
	   void spawnMine(int xposn, float m_speed) {
		   
		   Mine mine = new Mine(xposn, m_speed);
		   regularmines.add(mine);
		   mines.add(mine);
		         
	   }
	   
	   void spawnShieldMine(int xposn, float m_speed, int shields) {
		   ShieldMine shieldmine= new ShieldMine(xposn, m_speed, shields);
		   mines.add(shieldmine);
		   shieldmines.add(shieldmine);
	   }
	   
	   void spawnExplosion(float X, float Y){
		   Kaboom boom = new Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;
		   boom.rect.x= X;
		   boom.rect.y= Y;
		   explosions.add(boom);
	   }
	   
	   //---Drawing functions---
	   
	   
	   
	   private void draw_the_statusbar(){
		   batch.draw(statusbar_t, 0, 400);
			
			batch.draw(menu_button_t,menu_button_r.x,menu_button_r.y);
		      if (menu_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,menu_button_r.x,menu_button_r.y);
		      }
		      
		      batch.draw(fire_button_t,fire_button_r.x,fire_button_r.y);
		      
		      boolean torrit=false;
		      
		      for (Turret turret:turrets){
		    	  if (turret.rect.contains(tp_x, tp_y)){
		    		  torrit=true;
		    		  if (turret.lines_no==4){
		    			font.draw(batch, turret.line_one, 90, 472, 140,1, true);
		  				font.draw(batch, turret.line_two, 90, 455, 140, 1, true);
		  				font.draw(batch, turret.line_three, 90, 437, 140, 1, true);
		  				font.draw(batch, turret.line_four, 90, 420, 140, 1, true);
		    		  }
		    		  if (turret.lines_no==3){
			  				font.draw(batch, turret.line_one, 90, 465, 140, 1, true);
			  				font.draw(batch, turret.line_two, 90, 448, 140, 1, true);
			  				font.draw(batch, turret.line_three, 90, 431, 140, 1, true);
			    		  }
		    	  }
		      }
		      
		      if (!torrit){
		    	level_specific_HUD();
		      }
				
		      if (current_status.equals("targeting") && currently_active_turret_no==5){
		    	  batch.draw(orange_button_trim_t,fire_button_r.x,fire_button_r.y);
			   }
		      if (fire_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,fire_button_r.x,fire_button_r.y);
		      }
		      
	   }
	   
	   private void draw_iterable_objects(){
		   for(Mine mine: regularmines) {
		          batch.draw(mine_t, mine.rect.x-20, mine.rect.y-20);
		          if (mine.being_detained){
		        	  batch.draw(detaining_t, mine.rect.x-20, mine.rect.y-20);
		          }
		       }
		   for (ShieldMine shieldmine:shieldmines){
			   batch.draw(shieldmine_t, shieldmine.rect.x-20, shieldmine.rect.y-20);
		          if (shieldmine.being_detained){
		        	  batch.draw(detaining_t, shieldmine.rect.x-20, shieldmine.rect.y-20);
		          }
		          if (shieldmine.shields_rn>=1){
		        	  batch.draw(mine_shield_one_t, shieldmine.shield_one.x, shieldmine.shield_one.y);
		          }
		          if (shieldmine.shields_rn>=2){
		        	  batch.draw(mine_shield_two_t, shieldmine.shield_two.x, shieldmine.shield_two.y);
		          }
		          if (shieldmine.shields_rn>=3){
		        	  batch.draw(mine_shield_three_t, shieldmine.shield_three.x, shieldmine.shield_three.y);
		          }
		          if (shieldmine.shields_rn>=4){
		        	  batch.draw(mine_shield_four_t, shieldmine.shield_four.x, shieldmine.shield_four.y);
		          }
		   }
			for(Kaboom boom: explosions) {
		          batch.draw(explosion_t, boom.rect.x-20, boom.rect.y-20);
		       }
			for(Turret turret: turrets) {
				if (turret.does_it_work){
					batch.draw(turret.current_t, turret.rect.x, turret.rect.y);
				}
				else{
					batch.draw(turret.dead_t, turret.rect.x, turret.rect.y);
				}
		       }
			
			
			for(Dot dot: dots) {
				if (dot.type.equals("destroy")){
					batch.draw(destroy_dot_t, dot.rect.x, dot.rect.y);
				}
				if (dot.type.equals("capture")){
					batch.draw(capture_dot_t, dot.rect.x, dot.rect.y);
				}
				
		       }
			
			if (current_status.equals("firing")){
				for(Turret turret: turrets) {
					if (turret.does_it_work){
						batch.draw(turret.overlay_t, turret.rect.x, turret.rect.y);
			       }
				}
			}
			
	   }
	   
	   void draw_textbox(String text){
		   draw_textbox_one(text);
	   }
	   
	   void draw_textbox_one(String text){
		   batch.draw(textbox_one_t, 20, 100);
		   if (greentext){
			   greenfont.draw(batch, text, 30, 170, 260, 1, true);
		   }
		   else{
			   font.draw(batch, text, 30, 170, 260, 1, true);
		   }
	   }
	   
	   void draw_textbox_two(String text){
		   batch.draw(textbox_two_t, 20, 100);
		   if (greentext){
			   greenfont.draw(batch, text, 30, 190, 260, 1, true);
		   }
		   else{
			   font.draw(batch, text, 30, 190, 260, 1, true);
		   }
	   }
	   
	   private void draw_targeting_symbols(){
		   if (turret_one.targeted){
			   batch.draw(turret_one.target_t, turret_one.target_mine.rect.x-25, turret_one.target_mine.rect.y+5);
		   }
		   if (turret_two.targeted){
			   batch.draw(turret_two.target_t, turret_two.target_mine.rect.x+5, turret_two.target_mine.rect.y+5);
		   }
		   if (turret_three.targeted){
			   batch.draw(turret_three.target_t, turret_three.target_mine.rect.x-25, turret_three.target_mine.rect.y-25);
		   }
		   if (turret_four.targeted){
			   batch.draw(turret_four.target_t, turret_four.target_mine.rect.x+5, turret_four.target_mine.rect.y-25);
		   }
	   }
	   
	   //---Generally useful functions---
	   
	   private void move_iterable_objects(float delta){
		   for (Mine mine:mines){
			   mine.rect.x += mine.horz_vel * delta;
			     mine.rect.y -= mine.vert_speed * delta;
		   }
		   for(Dot dot: dots) {
		          dot.rect.x+=dot.horz_vel*delta;
		          dot.rect.y+=dot.vert_vel*delta;
		       }
		   for (ShieldMine shieldmine:shieldmines){
			   shieldmine.shield_one.x=shieldmine.rect.x-5;
			   shieldmine.shield_one.y=shieldmine.rect.y-5;
			   shieldmine.shield_two.x=shieldmine.rect.x-10;
			   shieldmine.shield_two.y=shieldmine.rect.y-10;
			   shieldmine.shield_three.x=shieldmine.rect.x-15;
			   shieldmine.shield_three.y=shieldmine.rect.y-15;
			   shieldmine.shield_four.x=shieldmine.rect.x-20;
			   shieldmine.shield_four.y=shieldmine.rect.y-20;
		   }
	   }
	   
	   private void check_for_shipshield_mine_collisions(){
		   for (Mine mine: mines){
				if(mine.rect.overlaps(shield_r)) {
			     	spawnExplosion(mine.rect.x,mine.rect.y);
			        minecount-=1;
			        shields-=1;
			        hitshield.play(0.4f);
			        minesplode.play();
			        shipshield_t=shipshield_flicker_t;
			        unlist_this_mine(mine);
			     }
			}
	   }
	   
	   private void kill_lost_dots(){
		   Iterator<Dot> iter_dots = dots.iterator();
			while(iter_dots.hasNext()) {
				Dot dot = iter_dots.next();
			     if (!dot.rect.overlaps(screen_proper)){
			    	 iter_dots.remove();
			     }

			}
	   }
	   
	   private void exit_stage_whatever(Mine exiting_mine){
		   if (exiting_mine.rect.x>160){
			   exiting_mine.horz_vel=2000;
		   }
		   else{
			   exiting_mine.horz_vel=-2000;
		   }
	   }
	   
	   private void collect_captured(){
		   for (Mine mine: mines){
			   if (mine.being_detained){
				   if ((mine.rect.x+mine.rect.width+20)<0 || (mine.rect.x-20)>320){					   
					   captured+=1;
					   minecount-=1;
					   unlist_this_mine(mine);
				   }
			   }
		   }
	   }
	   
	   private void check_for_dot_mine_collisions(){
		   for (Dot dot:dots){
			   if (dot.rect.overlaps(dot.target_mine.rect)){
				    	 
				   if (dot.type.equals("destroy") && !dot.target_mine.destroyproof){
					    	 dot.target_mine.actually_there=false;
						     	spawnExplosion(dot.target_mine.rect.x,dot.target_mine.rect.y);
						     	destroyed+=1;
						     	minecount-=1;
						     	minesplode.play();
						     	
						     	unlist_this_mine(dot.target_mine);
						     	
				    	 }
				    	 if (dot.type.equals("capture") && !dot.target_mine.captureproof){
					    	 dot.target_mine.being_detained=true;
					    	 exit_stage_whatever(dot.target_mine);
					    	 capture.play();
				    	 }
				    	 dots.removeValue(dot, true);
				     }
			   }
	   }
	   
	   private void check_for_dot_mineshield_collisions(){
		   for (Dot dot:dots){
			   if (dot.target_mine.minetype.equals("shield")){
				   if (dot.type.equals("destroy")||dot.type.equals("capture")){
					   
					   ShieldMine S=(ShieldMine)dot.target_mine;
					   
					   if (S.shields_rn==4 && S.shield_four.overlaps(dot.rect)){
						   S.shields_rn-=1;
						   dots.removeValue(dot, true);
					   }
					   else if (S.shields_rn==3 && S.shield_three.overlaps(dot.rect)){
						   S.shields_rn-=1;
						   dots.removeValue(dot, true);
					   }
					   else if (S.shields_rn==2 && S.shield_two.overlaps(dot.rect)){
						   S.shields_rn-=1;
						   dots.removeValue(dot, true);
					   }
						else if (S.shields_rn==1 && S.shield_one.overlaps(dot.rect)){
							S.shields_rn-=1;
							dots.removeValue(dot, true);
						}
				   }
			   }
		   }
	   }
	   
	   private void time_out_explosions(){
		   Iterator<Kaboom> iterk = explosions.iterator();
		   while(iterk.hasNext()) {
		    	  Kaboom boom = iterk.next();
		    	  if(total_time - boom.birthtime > 0.25) iterk.remove();
		      }
	   }
	   
	   private void status_effects(){
		   if (current_status.equals("waiting")){
			   TIMESPEED=1;
		   }
		   if (current_status.equals("firing")){
			   TIMESPEED=0.1;
		   }
		   if (current_status.equals("targeting")){
			   TIMESPEED=0;
		   }
	   }
	   

	   
	   void handle_seconds(){
		   if (seconds<Math.floor(total_time)){
				seconds+=1;
				if (seconds%2==0 && mines.size>0 && !suppress_freezes){
					current_status="targeting";
					TIMESPEED=0;
					currently_active_turret_no=1;
					number_to_turret();
				}
				System.out.println(seconds+" s");
				level_specific_events();
				
			}
	   }
	   
	   private Mine pick_a_mine(){
		   for(Mine mine: mines) {
			   if (mine.rect.contains(tp_x, tp_y) && tp_y<440){
				   return mine;
			   }
		   }
		return null;
	   }
	   
	   void number_to_turret(){
		   if (currently_active_turret_no==1){
			   currently_active_turret=turret_one;
		   }
		   else if (currently_active_turret_no==2){
			   currently_active_turret=turret_two;
		   }
		   else if (currently_active_turret_no==3){
			   currently_active_turret=turret_three;
		   }
		   else if (currently_active_turret_no==4){
			   currently_active_turret=turret_four;
		   }
		   else{
			   currently_active_turret=null;
		   }
	   }
	   
	   private void set_up_firing_times(){
		   
		   float q=total_time+0.05f;
		   
		   for (Turret turret: turrets){
			   if (turret.targeted){
				   q+=0.15;
				   turret.firing_time=q;
			   }
		   }
		   
		   end_time=q+0.2f;
	   }

	   private void do_firing_things(){
		   for(Turret turret: turrets) {
			   if (turret.target_mine!=null){
				   if (!turret.target_mine.being_detained && turret.target_mine.actually_there){
					   if (turret.targeted && turret.firing_time<total_time){
						   Dot dot=new Dot(turret.rect, turret.target_mine, 3000, turret.determine_output());
						   dots.add(dot);
						   fire.play(0.3f);
						   turret.targeted=false;
					   }
					   
				   }
			   }
			   if (turret.firing_time<total_time && (turret.firing_time+0.04)>total_time){
				   turret.current_t=turret.firing_t;
			   }
			   else{
					turret.current_t=turret.normal_t;
			   }
		   }
		   
		   if (total_time>end_time){
			   current_status="waiting";
			   status_effects();
		   }
	   }
	   
	   private void do_targeting_things(){
		   if (currently_active_turret_no<5 && currently_active_turret_no>0){
			   
			   
			   currently_active_turret.current_t=currently_active_turret.selected_t;
			   number_to_turret();
			   
			   
			   
			   if (Gdx.input.justTouched() && pick_a_mine()!=null){
				   currently_active_turret.targeted=true;
					   currently_active_turret.target_mine=pick_a_mine();
					   currently_active_turret.current_t=currently_active_turret.normal_t;
			   }
			   
			   boolean exitwhile=false;
			   
			   while (!exitwhile){
				   if (currently_active_turret_no>4){
					   exitwhile=true;
				   }
				   else{
					   if (!currently_active_turret.targeted && currently_active_turret.does_it_work){
						   exitwhile=true;
					   }
				   }
				   if (!exitwhile){
					   currently_active_turret_no+=1;
					   number_to_turret();
				   }
			   }
		   }
		   
		   
		   if (Gdx.input.justTouched()){
				if (fire_button_r.contains(tp_x, tp_y)){
					if (current_status.equals("targeting")){
						current_status="firing";
						set_up_firing_times();
						turret_one.current_t=turret_one.normal_t;
						   turret_two.current_t=turret_two.normal_t;
						   turret_three.current_t=turret_three.normal_t;
						   turret_four.current_t=turret_four.normal_t;
					}
				}
				for (Turret turret:turrets){
					if (turret.rect.contains(tp_x, tp_y)){
						if (currently_active_turret_no<5){
							currently_active_turret.current_t=currently_active_turret.normal_t;
						}
						turret.targeted=false;
						currently_active_turret_no=turret.turret_no;
						number_to_turret();
					}
				}
			}
	   }
	   
	   private void detarget_the_dead(){
		   float decrement=0;
		   for (Turret turret:turrets){
			   turret.firing_time-=decrement;
			   if (turret.targeted==true){
				   if (!turret.target_mine.actually_there || turret.target_mine.being_detained){
					   turret.targeted=false;
					   turret.firing_time=-1;
					   decrement+=0.15;
				   }
			   }
		   }
		   end_time-=decrement;
	   }
	   
	   
	   private String present_float(float flo){//All the normal methods won't export to html so I have to reinvent things.
		   int a=Math.round(flo*100);
		   int b=a%100;
		   int c=(a-b)/100;
		   if (b<10){
			   return c+"."+b+"0";
		   }
		   else{
			   return c+"."+b;
		   }
	   }
	   
	   void fiat_function(){
		   for (Turret turret:turrets){
			   turret.does_it_work=true;
		   }
	   }
	   
	   void unlist_this_mine(Mine mine){
		   mines.removeValue(mine,true);
		   if (regularmines.contains(mine, true)){
			   regularmines.removeValue(mine, true);
		   }
		   if (mine.minetype.equals("shield")){
			   if (shieldmines.contains((ShieldMine)mine, true)){
				   shieldmines.removeValue((ShieldMine)mine, true);
			   }
		   }
	   }
	   
	   public void probgame_render(float delta){
		   effective_delta=(float) (delta*TIMESPEED); //If time is running slow, the delta to feed into all calculations will be lower.
			
		   level_specific_timeline();
		   
			status_effects();
			
			move_iterable_objects(effective_delta); //Update position of dots and mines.
			
			time_out_explosions(); //Eventually things finish exploding.
			
			
			
			
			
			if (current_status.equals("firing")){
				do_firing_things();
			}
			
			if (current_status.equals("targeting")){
				do_targeting_things();
			}
			
			
			spacey_render(delta); //Do the generic rendering defined in SpaceyScreen.
			
			
			batch.begin();
			
			batch.setProjectionMatrix(camera.combined);
			
			draw_iterable_objects();
			
			if (current_status.equals("targeting")){
				autocalc_and_display();
			}
			
		    batch.draw(shipshield_t, shield_r.x, shield_r.y);
		    
		    if (current_status.equals("targeting") && currently_active_turret_no<5 && currently_active_turret_no>0){
		    	batch.draw(currently_active_turret.target_t,tp_x-30,tp_y-30);
		    }
		    
		    draw_targeting_symbols();
			
			draw_the_statusbar(); //Draw the bar at the top of the screen, and everything on it.
		    
			if (show_the_text){
				draw_textbox(the_text);
			}

			
			batch.draw(poncho_t, -640, -960); //Draw a massive object over everything to frame the game screen.
			
			batch.end();
			
			check_for_dot_mineshield_collisions();
			
			check_for_dot_mine_collisions();
			
			check_for_shipshield_mine_collisions();
			
			
			kill_lost_dots(); // Remove dots which are no longer onscreen from the 'dots' array.
			
			collect_captured(); // Remove mines which are no longer onscreen from the 'mines' array.
			
			detarget_the_dead(); //Make turrets not target destroyed or captured mines.
			
			
			handle_seconds(); //Execute the events which happen at the beginning of a second.
			
			
			
			
				if (current_status.equals("firing") && total_time>end_time){
					current_status="waiting";
				}
			
			
			
			
			
			shipshield_t=shipshield_normal_t; // If the shield is flickering, we want it to flicker for only one frame; we reset it here.
			
			if(Gdx.input.justTouched()){
				if (menu_button_r.contains(tp_x, tp_y)){
		    			  game.setScreen(new TitleScreen(game, true));
		    			  dispose();
				}
			}
	   }
	@Override
	public void render(float delta) {
		
		probgame_render(delta);
		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public void probgame_dispose(){
		spacey_dispose();
		bgm.stop();
		bgm.dispose();
	}
	
	@Override
	public void dispose() {
		probgame_dispose();
		
	}
}