package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This is where we start being specific to this game, but not specific to game modes.
 * 
 * In other words, everything which would show up in ProbDef and BayesDef but not SineDef is here.
 * 
 * Loads in all the resources (even the ones which aren't currently used in both), sets up all the shared arrays, and so on)
 */

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.hbp.probdef.RT_Kaboom;
import com.hbp.probdef.Mine;
import com.hbp.probdef.Turret;
import com.badlogic.gdx.audio.Sound;


import com.hbp.probdef.ProbDef;

public class GameScreen extends SpaceScreen {
	
	final ProbDef game;
	
	
	//--Textures--
	

	Texture enemyship_t;
	
	Texture enemyshipshield_t;
	
	Texture enemyshipshield_flicker_t;
	Texture enemyshipshield_normal_t;
	
	Texture obscurity_t;
	Texture big_explosion_t;
	
	//-Dots-
	
	Texture destroy_dot_t;
	Texture capture_dot_t;
	
	//-Buttons-
	Texture fire_button_t;
	Texture menu_button_t;
	
	Texture blue_button_trim_t;
	Texture orange_button_trim_t;
	Texture green_button_trim_t;
   
	Texture attention_button_trim_t;
   //-Mines and so on-
   Texture mine_t;
   
   Texture titaniummine_t;
   
   Texture mine_shield_one_t;
   Texture mine_shield_two_t;
   Texture mine_shield_three_t;
   Texture mine_shield_four_t;
   
   Texture mine_trim_t;
   
   Texture explosion_t;
   
   Texture detaining_t;
   
   //-Textboxes
   
   Texture textbox_one_t;//Four lines max
   Texture textbox_two_t;//Five lines max
   
   //-Turrets and turret accessories-
   
   Texture scratch_two;
	Texture scratch_three;
	Texture scratch_four;
	Texture scratch_five;
   
	
	//--Vanes et al--
	
	Texture vane_outline_t;
	Texture vane_t;
	Texture vane_trim_t;
	
	Texture vane_energy_circle_t;
	Texture vane_energy_triangle_t;
	Texture vane_energy_square_t;
	Texture vane_energy_pentagon_t;
	Texture vane_energy_hexagon_t;
	
	Texture vane_crosshairs_circle_t;
	Texture vane_crosshairs_triangle_t;
	Texture vane_crosshairs_square_t;
	Texture vane_crosshairs_pentagon_t;
	Texture vane_crosshairs_hexagon_t;
	
   //--Sounds--
   
   Sound minesplode;
	Sound minehitshield;
	Sound deshield;
	Sound capture;
	Sound fire;
	Sound laser;
	Sound mistaken;
	Sound shock;
	
	//--Fonts--
	
	BitmapFont acalc_grayfont;
	BitmapFont acalc_redfont;
	BitmapFont acalc_bluefont;
	BitmapFont acalc_greenfont;
   
   //--Arrays--
	
	Array<Mine> mines;
   Array<RT_Kaboom> explosions;
   Array<RT_Dot> dots;
   
   Array<Turret> turrets;
   Array<Turret_Standard> turrets_standard;
   
   Array<EnemyShip> enemyships;
   
   Array<Vane> vanes;
   
   //--Rectangles--
    
   Rectangle menu_button_r;
	Rectangle fire_button_r;
	
	Rectangle screen_proper; //For checking whether objects are still in bounds (can still be seen by the player)
   
	//--Counts--
   
	int shields;
   int score;
   int minecount;
   int captured;
   int destroyed;
   
   
   
   String the_text;
   
   boolean show_the_text;
   boolean suppress_freezes;
   
   boolean infuriatingly_specific_bool;
	boolean greentext;
	
	float effective_delta;
	   
	float volley_ending_time;
	float zappy_ending_time;
	
	SpriteBatch batch;
	
	public GameScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		game=gam;
		
		menu_button_r=new Rectangle(230,420,100,40);
	    fire_button_r=new Rectangle(10,420,100,40);
		
	    screen_proper=new Rectangle(0, 0, 320, 400);
	    
	    mines = new Array<Mine>();	      
	    explosions = new Array<RT_Kaboom>();
	    dots = new Array<RT_Dot>();
	    turrets= new Array<Turret>();
	    turrets_standard= new Array<Turret_Standard>();
	    enemyships=new Array<EnemyShip>();
	    vanes=new Array<Vane>();
	    
	    the_text="";
	    show_the_text=false;
	    suppress_freezes=false;
	    
	    load_in_textures();
	    
	    load_in_sounds();
	    
	    load_in_fonts();
	    
	    level_specific_music_setup();
	    
	    attention_button_trim_t=blue_button_trim_t;
	    
	    big_explosion_t=new Texture(Gdx.files.internal("big_explosion.png"));
	    obscurity_t=new Texture(Gdx.files.internal("obscurity.png"));
	    
	    batch=new SpriteBatch();
	}

	//---Loading in resources which will be useful in all game modes---
	
	void load_in_textures(){
		
		//-Vanes et al-
		
		vane_t=new Texture(Gdx.files.internal("vanes/vane.png"));
		vane_outline_t=new Texture(Gdx.files.internal("vanes/vane_outline.png"));
		vane_trim_t=new Texture(Gdx.files.internal("vanes/vane_trim.png"));
		
		vane_energy_circle_t= new Texture(Gdx.files.internal("vanes/vanes_energy_circle.png"));
		vane_energy_triangle_t= new Texture(Gdx.files.internal("vanes/vanes_energy_triangle.png"));
		vane_energy_square_t= new Texture(Gdx.files.internal("vanes/vanes_energy_square.png"));
		vane_energy_pentagon_t= new Texture(Gdx.files.internal("vanes/vanes_energy_pentagon.png"));
		vane_energy_hexagon_t= new Texture(Gdx.files.internal("vanes/vanes_energy_hexagon.png"));
		
		vane_crosshairs_circle_t= new Texture(Gdx.files.internal("vanes/vanes_target_circle.png"));
		vane_crosshairs_triangle_t= new Texture(Gdx.files.internal("vanes/vanes_target_triangle.png"));
		vane_crosshairs_square_t= new Texture(Gdx.files.internal("vanes/vanes_target_square.png"));
		vane_crosshairs_pentagon_t= new Texture(Gdx.files.internal("vanes/vanes_target_pentagon.png"));
		vane_crosshairs_hexagon_t= new Texture(Gdx.files.internal("vanes/vanes_target_hexagon.png"));
		
		
		//-Enemyship-
		
		enemyship_t=new Texture(Gdx.files.internal("enemyship_alt.png"));
		enemyshipshield_normal_t=new Texture(Gdx.files.internal("enemy_ship_shield.png"));
		enemyshipshield_flicker_t=new Texture(Gdx.files.internal("enemy_ship_shield_flicker.png"));
		enemyshipshield_t=enemyshipshield_normal_t;
		
		//-Dots-
		
		destroy_dot_t=new Texture(Gdx.files.internal("shot_destroy.png"));
	    capture_dot_t=new Texture(Gdx.files.internal("shot_capture.png"));
		
		//-Buttons-
		
		menu_button_t=new Texture(Gdx.files.internal("ingame_button_menu.png"));
		fire_button_t=new Texture(Gdx.files.internal("ingame_button_fire.png"));

	    blue_button_trim_t=new Texture(Gdx.files.internal("ingame_blue_button_trim.png"));
	    orange_button_trim_t=new Texture(Gdx.files.internal("ingame_orange_button_trim.png"));
	    green_button_trim_t=new Texture(Gdx.files.internal("ingame_green_button_trim.png"));
	    
	    //-Textboxes-
	    
	    textbox_one_t=new Texture(Gdx.files.internal("textbox_1.png"));
	    textbox_two_t=new Texture(Gdx.files.internal("textbox_2.png"));
	    
		//-Mines and so on-
	    
		mine_t=new Texture(Gdx.files.internal("mine.png"));
		titaniummine_t=new Texture(Gdx.files.internal("mine_titanium.png"));
		  
		mine_shield_one_t= new Texture(Gdx.files.internal("shield_layer_one.png"));
		mine_shield_two_t= new Texture(Gdx.files.internal("shield_layer_two.png"));
		mine_shield_three_t= new Texture(Gdx.files.internal("shield_layer_three.png"));
		mine_shield_four_t= new Texture(Gdx.files.internal("shield_layer_four.png"));
		
		explosion_t = new Texture(Gdx.files.internal("explosion.png"));
		detaining_t=new Texture(Gdx.files.internal("mine_capturing_circle.png"));
		
		//-Turrets and Turret accessories-
		
		scratch_two= new Texture(Gdx.files.internal("turrets/chickenscratch_2.png"));
	    scratch_three= new Texture(Gdx.files.internal("turrets/chickenscratch_3.png"));
	    scratch_four= new Texture(Gdx.files.internal("turrets/chickenscratch_4.png"));
	    scratch_five= new Texture(Gdx.files.internal("turrets/chickenscratch_5.png"));
		
		
	}
	
	
	void load_in_sounds(){
		minesplode=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/cannon.mp3"));
	    minehitshield=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/bang.mp3"));
	    deshield=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/hurt.wav"));
	    capture=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/pick.mp3"));
	    fire=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/gunshot.mp3"));
	    laser=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/laser.wav"));
	    mistaken=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/wrong.wav"));
	    shock=Gdx.audio.newSound(Gdx.files.internal("sfx_scronched/shock.mp3"));
	}
	
	void load_in_fonts(){
		acalc_grayfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
		acalc_grayfont.setColor(new Color(0.6f, 0.6f, 0.6f, 1.0f));
		acalc_redfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
		acalc_redfont.setColor(new Color(1.0f, 0.1f, 0.1f, 1.0f));
		acalc_bluefont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
		acalc_bluefont.setColor(new Color(0.1f, 0.1f, 1.0f, 1.0f));
		acalc_greenfont=new BitmapFont(Gdx.files.internal("the_font/greenflame.fnt"));
		acalc_greenfont.setColor(new Color(0.2f, 0.6f, 0.2f, 1.0f));
	}
	
	//---level_specific_something_or_other()---
	
	void level_specific_music_setup(){
		bgm=Gdx.audio.newMusic(Gdx.files.internal("MCS_Dampen.mp3"));
		bgm.setLooping(true);
		bgm.play();
	}
	
	void level_specific_timeline(){
		   
	   }
	
	void level_specific_events(){
		
	}
	
	//---Spawning functions---
	
	void spawnExplosion(float X, float Y){
		   RT_Kaboom boom = new RT_Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;

		   boom.rect.x= X-20;
		   boom.rect.y= Y-20;
		   boom.rect.width=80;
		   boom.rect.height=80;
		   
		   boom.big=false;
		   explosions.add(boom);
	}
	
	void spawnBigExplosion(float X, float Y){
		   RT_Kaboom boom = new RT_Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;
		   
		   boom.rect.x= X-30;
		   boom.rect.y= Y-30;
		   boom.rect.width=100;
		   boom.rect.height=100;
		   
		   boom.big=true;
		   explosions.add(boom);
	}
	
	//---Useful functions---
	
	String present_float(float flo){//All the normal methods won't export to html so I have to reinvent the wheel here.
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
	   
	   void make_every_turret_work(){
		   for (Turret turret:turrets){
			   turret.does_it_work=true;
		   }
	   }
	
	   void exit_stage_whatever(Mine exiting_mine){
		   if (exiting_mine.rect.x>160){
			   exiting_mine.horz_vel=2000;
		   }
		   else{
			   exiting_mine.horz_vel=-2000;
		   }
	   }
	   
	   
	   
	   void set_up_zapping_times(){
		   
		   float q=total_time+0.05f;
		   
		   for (Vane vane: vanes){
			   if (vane.targeted){
				   q+=0.15;
				   vane.firing_time=q;
			   }
		   }
		   
		   zappy_ending_time=q+0.2f;
	   }
	   
	   
	   
	   void set_up_firing_times(){
		   
		   float q=total_time+0.05f;
		   
		   for (Turret turret: turrets){
			   if (turret.targeted){
				   q+=0.15;
				   turret.firing_time=q;
			   }
		   }
		   
		   volley_ending_time=q+0.2f;
	   }
	   
	   //---The render---
	
	   @Override
		
		public void render(float delta){
			gamey_render_predraw(delta);
			
			batch.begin();
			
			batch.setProjectionMatrix(camera.combined);
			
			gamey_render_draw_objects();
			
			gamey_render_draw_interface();
			
			batch.end();
			
			check_for_dot_mineshield_collisions();
			
			check_for_dot_mine_collisions();
			
			gamey_render_postdraw();
			
			if(Gdx.input.justTouched()){
				if (menu_button_r.contains(tp_x, tp_y)){
					game.setScreen(new TitleScreen(game, true));
					  dispose();
				}
			}
		}
	   
	//---Functions directly called by the render---
	
	   void gamey_render_predraw(float delta){
			effective_delta=(float) (delta*TIMESPEED); //If time is running slow, the delta to feed into motion calculations will be lower.
			
			   level_specific_timeline(); //Do things which are done in a given level.
			   
				status_effects(); //Change environmental variables (just timespeed rn) based on whether we're firing, waiting, etc.
				
				move_iterable_objects(effective_delta); //Update position of dots, mines, etc.
				
				time_out_explosions(); //Eventually things finish exploding.
				
				spacey_render(delta); //Do the generic rendering defined in SpaceyScreen.
				
				calculate_score(); //compute score from shields and captures and whatever else we're using this level.
				
		}
	   

		void gamey_render_draw_objects(){
			
			draw_vanes();
			
			draw_enemyships();
			
			draw_explosions();
			
			draw_mines();
			
			draw_turrets_standard();
			
			draw_dots();
			
			draw_turret_overlays();
			
			draw_turret_chickenscratch();
			
			draw_obscurities();
			
			batch.draw(shipshield_t, shield_r.x, shield_r.y);
		}
		
		void gamey_render_draw_interface(){
			
			draw_the_statusbar(); //Draw the bar at the top of the screen, and everything on it.
		    
			if (show_the_text){
				draw_textbox(the_text);
			}

			
			batch.draw(poncho_t, -640, -960); //Draw a massive object over everything to frame the game screen.
			
		}
		
		void gamey_render_postdraw(){
			kill_lost_dots(); // Remove dots which are no longer onscreen from the 'dots' array.
			
			collect_captured(); // Remove mines which are no longer onscreen from the 'mines' array.
			
			detarget_the_dead(); //Make turrets not target destroyed or captured mines.
			
			
			handle_seconds(); //Execute the events which happen at the beginning of a second.
		}
		
		void check_for_dot_mine_collisions(){
			for (RT_Dot dot:dots){
			   if (dot.target_mine!=null){
				   if (dot.rect.overlaps(dot.target_mine.rect) && dot.target_mine.actually_there){
					    	 
					   if (dot.type.equals("destroy") && !dot.target_mine.destroyproof){
						    	 dot.target_mine.actually_there=false;
							     	spawnExplosion(dot.target_mine.rect.x,dot.target_mine.rect.y);
							     	destroyed+=1;
							     	minecount-=1;
							     	minesplode.play();
							     	
							     	mines.removeValue(dot.target_mine,true);
							     	
					   }
					   if (dot.type.equals("capture") && !dot.target_mine.captureproof){
					    	 dot.target_mine.being_detained=true;
					    	 dot.target_mine.actually_there=false;
					    	 exit_stage_whatever(dot.target_mine);
					    	 capture.play();
					   }
					   dots.removeValue(dot, true);
				   }
			   }
		   }
	   }
		
		void check_for_dot_mineshield_collisions(){
			   for (RT_Dot dot:dots){
				   if (dot.target_mine!=null){
					   if (dot.type.equals("destroy")||dot.type.equals("capture")){
						   
						   if (dot.target_mine.shields==4 && dot.target_mine.shield_four.overlaps(dot.rect)){
							   deshield.play(0.2f);
							   dot.target_mine.shields-=1;
							   dots.removeValue(dot, true);
						   }
						   else if (dot.target_mine.shields==3 && dot.target_mine.shield_three.overlaps(dot.rect)){
							   deshield.play(0.25f);
							   dot.target_mine.shields-=1;
							   dots.removeValue(dot, true);
						   }
						   else if (dot.target_mine.shields==2 && dot.target_mine.shield_two.overlaps(dot.rect)){
							   deshield.play(0.3f);
							   dot.target_mine.shields-=1;
							   dots.removeValue(dot, true);
						   }
							else if (dot.target_mine.shields==1 && dot.target_mine.shield_one.overlaps(dot.rect)){
								deshield.play(0.35f);
								dot.target_mine.shields-=1;
								dots.removeValue(dot, true);
							}
					   }
				   }
			   }
		   }
	   
		
		
	   //---Functions called by the functions called by the render---
	   
	
	void kill_lost_dots(){
		   Iterator<RT_Dot> iter_dots = dots.iterator();
			while(iter_dots.hasNext()) {
				RT_Dot dot = iter_dots.next();
			     if (!dot.rect.overlaps(screen_proper)){
			    	 iter_dots.remove();
			     }

			}
	   }
	   
	   
	   
	   void collect_captured(){
		   for (Mine mine: mines){
			   if (mine.being_detained){
				   if ((mine.rect.x+mine.rect.width+20)<0 || (mine.rect.x-20)>320){					   
					   captured+=1;
					   minecount-=1;
					   mines.removeValue(mine,true);
				   }
			   }
		   }
	   }
	   
	   
	   
	   
	   
	   void calculate_score(){
		   score=0;
	   }
	   
	   void time_out_explosions(){
		   Iterator<RT_Kaboom> iterk = explosions.iterator();
		   while(iterk.hasNext()) {
		    	  RT_Kaboom boom = iterk.next();
		    	  if(total_time - boom.birthtime > 0.25) iterk.remove();
		      }
	   }
	   
	   void status_effects(){
		   if (current_status.equals("waiting")){
			   TIMESPEED=1;
			   
		   }
		   if (current_status.equals("firing")||current_status.equals("zapping")){
			   TIMESPEED=0.1;
			   
		   }
		   if (current_status.equals("targeting")){
			   TIMESPEED=0;
			   for (Turret_Standard turret_standard: turrets_standard){
				   turret_standard.shotsmade=0;
			   }
		   }
		   if (current_status.equals("bowling")){
			   boolean ok_to_speed=true;
			   if (dots.size>0){
				   ok_to_speed=false;
			   }
			   if (explosions.size>0){
				   ok_to_speed=false;
			   }
			   for (Mine mine:mines){
				   if (!mine.actually_there){
					   ok_to_speed=false;
				   }
			   }
			   
			   if (ok_to_speed){
				   TIMESPEED=1;
			   }
			   else{
				   TIMESPEED=0.1;
			   }
			   
			   for (Turret_Standard turret_standard: turrets_standard){
				   turret_standard.shotsmade=0;
			   }
			   
			   for (EnemyShip enemyship: enemyships){
				   boolean shots_to_zero=true;
				   for (Mine mine:mines){
					   if (mine.actually_there && mine.target_enemy_ship==enemyship){
						   Turret_Standard T= (Turret_Standard)enemyship.turret;
						   T.shotsmade=0;
					   }
				   }
			   }
			   
			   ship_t=ship_invisible_t;
		   }
		   else{
			   ship_t=ship_normal_t;
		   }
	   }
	   
	   void move_iterable_objects(float delta){
		   for (EnemyShip enemyship:enemyships){
			   enemyship.rect.x+=enemyship.horz_vel*delta;
			   enemyship.rect.y-=enemyship.vert_vel*delta;
			   
			   enemyship.rect.y=Math.max(enemyship.rect.y,enemyship.ylim);
			   
			   enemyship.turret.rect.x=enemyship.rect.x+10;
			   enemyship.turret.rect.y=enemyship.rect.y+10;
			   
			   enemyship.shield_r.x=enemyship.rect.x-10;
			   enemyship.shield_r.y=enemyship.rect.y-20;
		   }
		   
		   
		   for (Mine mine:mines){
			   mine.rect.x += mine.horz_vel * delta;
			   mine.rect.y -= mine.vert_vel * delta;
			     
			   mine.shield_one.x=mine.rect.x-5;
			   mine.shield_one.y=mine.rect.y-5;
			   mine.shield_two.x=mine.rect.x-10;
			   mine.shield_two.y=mine.rect.y-10;
			   mine.shield_three.x=mine.rect.x-15;
			   mine.shield_three.y=mine.rect.y-15;
			   mine.shield_four.x=mine.rect.x-20;
			   mine.shield_four.y=mine.rect.y-20;
		   }

		   for(RT_Dot dot: dots) {
		          dot.rect.x+=dot.horz_vel*delta;
		          dot.rect.y+=dot.vert_vel*delta;
		   }
	   }
	   
	   void detarget_the_dead(){
		   float decrement=0;
		   for (Turret turret:turrets){
			   turret.firing_time-=decrement;
			   if (turret.targeted==true){
				   if (turret.target_mine!=null){
					   if (!turret.target_mine.actually_there || turret.target_mine.being_detained){
						   turret.targeted=false;
						   turret.firing_time=-1;
						   decrement+=0.15;
					   }
				   }
			   }
		   }
		   volley_ending_time-=decrement;
		   
		   
		   
		   decrement=0;
		   for (Vane vane:vanes){
			   vane.firing_time-=decrement;
			   if (vane.targeted==true){
				   if (vane.target_ship!=null){
					   if (!vane.target_ship.actually_there){
						   vane.targeted=false;
						   vane.firing_time=-1;
						   decrement+=0.15;
					   }
				   }
			   }
		   }
		   zappy_ending_time-=decrement;
	   }
	
	   void draw_mines(){
		   for(Mine mine: mines) {
		       if (mine.minetype.contains("titanium")){
		    	   batch.draw(titaniummine_t, mine.rect.x-20, mine.rect.y-20);
		       }
		       else{
		    	   batch.draw(mine_t, mine.rect.x-20, mine.rect.y-20);
		    	   
		    	   //batch.draw(enemyship_t, mine.rect.x-20, mine.rect.y-20);
		       }
			   
	          
	          if (mine.being_detained){
	        	  batch.draw(detaining_t, mine.rect.x-20, mine.rect.y-20);
	          }
	          if (mine.shields>=1){
	        	  batch.draw(mine_shield_one_t, mine.shield_one.x, mine.shield_one.y);
	          }
	          if (mine.shields>=2){
	        	  batch.draw(mine_shield_two_t, mine.shield_two.x, mine.shield_two.y);
	          }
	          if (mine.shields>=3){
	        	  batch.draw(mine_shield_three_t, mine.shield_three.x, mine.shield_three.y);
	          }
	          if (mine.shields>=4){
	        	  batch.draw(mine_shield_four_t, mine.shield_four.x, mine.shield_four.y);
	          }
	          
	          if (mine.being_detained){
	        	  batch.draw(detaining_t, mine.rect.x-20, mine.rect.y-20);
	          }
		          
		   }
	   }
	   
	   void draw_enemyships(){
		   for (EnemyShip enemyship: enemyships){
			   batch.draw(enemyship_t, enemyship.rect.x-10, enemyship.rect.y);
			   batch.draw(enemyshipshield_t, enemyship.shield_r.x, enemyship.shield_r.y);
		   }
	   }
	   
	   void draw_explosions(){
		   for(RT_Kaboom boom: explosions) {
		       if (boom.big){
		    	   batch.draw(big_explosion_t, boom.rect.x, boom.rect.y);
		       }
		       else{
		    	   batch.draw(explosion_t, boom.rect.x, boom.rect.y);
		       }
		   }
	   }
	   
	   void draw_turrets_standard(){
		   for(Turret_Standard turret_standard: turrets_standard) {
				if (turret_standard.does_it_work){
					batch.draw(turret_standard.current_t, turret_standard.rect.x, turret_standard.rect.y);
				}
				else{
					batch.draw(turret_standard.dead_t, turret_standard.rect.x, turret_standard.rect.y);
				}
		       }
	   }
	   
	   void draw_dots(){
		   for(RT_Dot dot: dots) {
				if (dot.type.equals("destroy")){
					batch.draw(destroy_dot_t, dot.rect.x, dot.rect.y);
				}
				if (dot.type.equals("capture")){
					batch.draw(capture_dot_t, dot.rect.x, dot.rect.y);
				}
		   }
	   }
	   
	   void draw_turret_overlays(){
		   if (current_status.equals("firing")){
				for(Turret turret: turrets) {
					if (turret.does_it_work){
						batch.draw(turret.overlay_t, turret.rect.x, turret.rect.y);
						
			       }
				}

			}
	   }
	   
	   void draw_turret_chickenscratch(){
		   for (Turret_Standard turret_standard: turrets_standard){
				if (turret_standard.turret_level==2){
					batch.draw(scratch_two, turret_standard.rect.x+10, turret_standard.rect.y+15);
				}
				if (turret_standard.turret_level==3){
					batch.draw(scratch_three, turret_standard.rect.x+10, turret_standard.rect.y+15);
				}
				if (turret_standard.turret_level==4){
					batch.draw(scratch_four, turret_standard.rect.x+10, turret_standard.rect.y+15);
				}
				if (turret_standard.turret_level==5){
					batch.draw(scratch_five, turret_standard.rect.x+10, turret_standard.rect.y+15);
				}
			}
	   }
	   
	   void draw_obscurities(){
		   for (EnemyShip enemyship: enemyships){
			   if (enemyship.obscured){
				   batch.draw(obscurity_t, enemyship.rect.x, enemyship.rect.y);
			   }
		   }
	   }
	   
	   void draw_vanes(){
		   for (Vane vane:vanes){
			   if (current_status.equals("bowling")){
				   batch.draw(vane_outline_t, vane.rect.x, vane.rect.y);
			   }
			   else{
				   batch.draw(vane_t, vane.rect.x, vane.rect.y);
			   }
			   
		   }
	   }
	
	
	boolean should_firing_button_be_lit_up(){
		   return false;
	   }
	   
	   void draw_the_statusbar(){
		   batch.draw(statusbar_t, 0, 400);
			
			batch.draw(menu_button_t,menu_button_r.x,menu_button_r.y);
		      if (menu_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,menu_button_r.x,menu_button_r.y);
		      }
		      
		      batch.draw(fire_button_t,fire_button_r.x,fire_button_r.y);
				
		      if (should_firing_button_be_lit_up()){
		    	  batch.draw(attention_button_trim_t,fire_button_r.x,fire_button_r.y);
			   }
		      if (fire_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,fire_button_r.x,fire_button_r.y);
		      }
		      
		      draw_the_HUD();
		      
	   }
	   
	   void draw_the_HUD(){
		    	level_specific_HUD();
	   }
	
	   void level_specific_HUD(){
		   font.draw(batch, "MINES: "+minecount, 90, 464, 140, 1, true);
		   font.draw(batch, "CAPTURED: "+captured, 90, 446, 140, 1, true);
		   font.draw(batch, "DESTROYED: "+ destroyed, 90, 428, 140, 1, true);
	   }
	
	   void draw_textbox(String text){
		   draw_textbox_one(text);
	   }
	   
	   void draw_textbox_one(String text){
		   batch.draw(textbox_one_t, 20, 100);
		   if (greentext){
			   greenfont.draw(batch, text, 30, 173, 260, 1, true);
		   }
		   else{
			   blackfont.draw(batch, text, 30, 173, 260, 1, true);
		   }
	   }
	   
	   void draw_textbox_two(String text){
		   batch.draw(textbox_two_t, 20, 100);
		   if (greentext){
			   greenfont.draw(batch, text, 30, 191, 260, 1, true);
		   }
		   else{
			   blackfont.draw(batch, text, 30, 191, 260, 1, true);
		   }
	   }
	   
	
	
	   void handle_seconds(){
		   if (seconds<Math.floor(total_time)){
				seconds+=1;
				System.out.println(seconds+" s");
				level_specific_events();
			}
	   }
	
	//---Disposes---
	
	public void gamey_dispose(){
		spacey_dispose();
		bgm.stop();
		bgm.dispose();
	}
	
	@Override
	public void dispose(){
		gamey_dispose();
	}
}