package com.hbp.probdef;



import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.*;
import com.hbp.probdef.Kaboom;
import com.hbp.probdef.Mine;
import com.hbp.probdef.Turret;

import com.hbp.probdef.ProbDef;

public class TutorialScreen extends SpaceyScreen {
	
	final ProbDef game;
	
	//private Music bgm;
	
	private Rectangle menu_button_r;
	private Texture menu_button_t;
	private Rectangle fire_button_r;

	private Texture fire_button_t;
	private Texture blue_button_trim_t;
   private Texture orange_button_trim_t;
   
   
   private Texture mine_t;
   
   private Array<Mine> mines;
   private Array<Kaboom> explosions;
   private Array<Turret> turrets;
   public Array<Dot> dots;
   
   private Texture destroy_dot_t;
   private Texture capture_dot_t;

   

   private Texture explosion_t;
   
   private Turret turret_one;
   private Turret turret_two;
   private Turret turret_three;
   private Turret turret_four;
   
   private Turret currently_active_turret;
   
   private int currently_active_turret_no;
   
   private float effective_delta;
	
   private Texture pause_t;
   
   private Texture detaining_t;
   
   private int captured;
   private int destroyed;
   private int shields;
   
   private float end_time;
   
	private SpriteBatch batch;
	
	private Rectangle screen_proper;
	
	public TutorialScreen(final ProbDef gam, boolean play_the_sound, boolean is_android_on) {
		
		super(gam, play_the_sound, is_android_on);
		game=gam;
		
		batch= new SpriteBatch();
		
	      menu_button_r=new Rectangle(220,420,100,40);
	      fire_button_r=new Rectangle(20,420,100,40);

		menu_button_t=new Texture(Gdx.files.internal("ingame_button_menu.png"));
		fire_button_t=new Texture(Gdx.files.internal("ingame_button_fire.png"));

	      blue_button_trim_t=new Texture(Gdx.files.internal("ingame_blue_button_trim.png"));
	      orange_button_trim_t=new Texture(Gdx.files.internal("ingame_orange_button_trim.png"));

		   explosion_t = new Texture(Gdx.files.internal("explosion.png"));

	      
	      mines = new Array<Mine>();
	      explosions = new Array<Kaboom>();
	      turrets= new Array<Turret>();
	      dots = new Array<Dot>();
	      
	      mine_t=new Texture(Gdx.files.internal("mine.png"));
	      
	      turret_one=new Turret_Standard("triangle");
	      turret_one.rect.x=50;
	      turret_two=new Turret_Standard("square");
	      turret_two.rect.x=110;
	      turret_three=new Turret_Standard("pentagon");
	      turret_three.rect.x=170;
	      turret_four=new Turret_Standard("hexagon");
	      turret_four.rect.x=230;
	      
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
	      
	      
	}
	
	   private void spawnMine(int xposn, float m_speed) {
		      Mine mine = new Mine();
		      mine.rect = new Rectangle();
		      double xposn_II = (xposn*40.0+160.0)-20.0;
		      mine.rect.x = (float) xposn_II;
		      mine.rect.y = 440;
		      mine.rect.width = 40;
		      mine.rect.height = 40;
		      
		      mine.vert_speed = m_speed;
		      
		      
		      mines.add(mine);
		      
	   }
	   
	   private void spawnExplosion(float X, float Y){
		   Kaboom boom = new Kaboom();
		   boom.rect= new Rectangle();
		   boom.birthtime=total_time;
		   boom.rect.x= X;
		   boom.rect.y= Y;
		   explosions.add(boom);
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
	   
	   private void draw_the_statusbar(){
		   batch.draw(statusbar_t, 0, 400);
			
			batch.draw(menu_button_t,menu_button_r.x,menu_button_r.y);
		      if (menu_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,menu_button_r.x,menu_button_r.y);
		      }
		      
		      batch.draw(fire_button_t,fire_button_r.x,fire_button_r.y);
		      
		      font.draw(batch, "CAPTURED:", 100, 472, 120,1, true);
				font.draw(batch, ""+captured, 100, 455, 120, 1, true);
				font.draw(batch, "DESTROYED:", 100, 437, 120, 1, true);
				font.draw(batch, ""+destroyed, 100, 420, 120, 1, true);
		      
		      if (current_status.equals("targeting") && currently_active_turret_no==5){
		    	  batch.draw(orange_button_trim_t,fire_button_r.x,fire_button_r.y);
			   }
		      if (fire_button_r.contains(tp_x, tp_y)){
		    	  batch.draw(blue_button_trim_t,fire_button_r.x,fire_button_r.y);
		      }
		      
	   }
	   
	   private void draw_iterable_objects(){
		   for(Mine mine: mines) {
		          batch.draw(mine_t, mine.rect.x-20, mine.rect.y-20);
		          if (mine.being_detained){
		        	  batch.draw(detaining_t, mine.rect.x-20, mine.rect.y-20);
		          }
		       }
			for(Kaboom boom: explosions) {
		          batch.draw(explosion_t, boom.rect.x-20, boom.rect.y-20);
		       }
			for(Turret turret: turrets) {
				batch.draw(turret.current_t, turret.rect.x, turret.rect.y);
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
					batch.draw(turret.overlay_t, turret.rect.x, turret.rect.y);
			       }
			}
			
	   }
	   
	   private void move_mines(float delta){
		   Iterator<Mine> iter_mines = mines.iterator();
			while(iter_mines.hasNext()) {
			     Mine mine = iter_mines.next();
			     mine.rect.x += mine.horz_vel * delta;
			     mine.rect.y -= mine.vert_speed * delta;
			}
	   }
	   
	   private void move_dots(float delta){
		   for(Dot dot: dots) {
		          dot.rect.x+=dot.horz_vel*delta;
		          dot.rect.y+=dot.vert_vel*delta;
		       }
	   }
	   
	   private void check_for_shipshield_mine_collisions(){
		   Iterator<Mine> iter_mines = mines.iterator();
			while(iter_mines.hasNext()) {
			     Mine mine = iter_mines.next();
				if(mine.rect.overlaps(shield_r)) {
			     	spawnExplosion(mine.rect.x,mine.rect.y);
			        //iters.remove();
			         	iter_mines.remove();
			         	//deadyet=true;
			         	shipshield_t=shipshield_flicker_t;
			            //lives-=1;
			            //hitship_sound.play();
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
					   mines.removeValue(mine, true);
					   captured+=1;
				   }
			   }
		   }
	   }
	   
	   private void check_for_dot_mine_collisions(){
		   Iterator<Dot> iter_dots = dots.iterator();
			while(iter_dots.hasNext()) {
			     Dot dot = iter_dots.next();
			     if (dot.rect.overlaps(dot.target_mine.rect)){
			    	 
			    	 if (dot.type.equals("destroy") && !dot.target_mine.destroyproof){
				    	 mines.removeValue(dot.target_mine, true);
				    	 dot.target_mine.actually_there=false;
					     	spawnExplosion(dot.target_mine.rect.x,dot.target_mine.rect.y);
					     	destroyed+=1;
			    	 }
			    	 if (dot.type.equals("capture") && !dot.target_mine.captureproof){
				    	 dot.target_mine.being_detained=true;
				    	 exit_stage_whatever(dot.target_mine);
			    	 }
			    	 iter_dots.remove();
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
			   //batch.draw(pause_t,0,0);
		   }
		   
	   }
	   
	   private void level_specific_events(){
		   if (seconds==2){
				spawnMine(-2, 50);
				spawnMine(2, 50);

			}
		   if (seconds==4){
				spawnMine(1, 50);
				spawnMine(-1, 50);
			}
			if (seconds==6){
				spawnMine(2,50);
			}
			if (seconds==8){
				spawnMine(1,50);
			}
			if (seconds==10){
				spawnMine(0,50);
			}
	   }
	   
	   private void handle_seconds(){
		   if (seconds<Math.floor(total_time)){
				seconds+=1;
				if (seconds%2==0 && mines.size>0){
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
			   if (mine.rect.contains(tp_x, tp_y)){
				   return mine;
			   }
		   }
		return null;
	   }
	   
	   private void number_to_turret(){
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
		   if (turret_one.targeted){
			   q+=0.15;
			   turret_one.firing_time=q;
		   }
		   if (turret_two.targeted){
			   q+=0.15;
			   turret_two.firing_time=q;

		   }
		   if (turret_three.targeted){
			   q+=0.15;
			   turret_three.firing_time=q;

		   }
		   if (turret_four.targeted){
			   q+=0.15;
			   turret_four.firing_time=q;

		   }
		   end_time=q+0.2f;
	   }

	   private void do_firing_things(){
		   for(Turret turret: turrets) {
			   if (!turret.target_mine.being_detained && turret.target_mine.actually_there){
				   if (turret.targeted && turret.firing_time<total_time){
					   Dot dot=new Dot(turret.rect, turret.target_mine, 3000, turret.determine_output());
					   dots.add(dot);
					   turret.targeted=false;
				   }
				   if (turret.firing_time<total_time && (turret.firing_time+0.04)>total_time){
					   turret.current_t=turret.firing_t;
				   }
				   else{
					   turret.current_t=turret.normal_t;
				   }
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
					   if (!currently_active_turret.targeted){
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
				if (turret_one.rect.contains(tp_x, tp_y)){
					if (currently_active_turret_no<5){
						currently_active_turret.current_t=currently_active_turret.normal_t;
					}
					turret_one.targeted=false;
					currently_active_turret_no=1;
					number_to_turret();
				}
				if (turret_two.rect.contains(tp_x, tp_y)){
					if (currently_active_turret_no<5){
						currently_active_turret.current_t=currently_active_turret.normal_t;
					}
					turret_two.targeted=false;
					currently_active_turret_no=2;
					number_to_turret();
				}
				if (turret_three.rect.contains(tp_x, tp_y)){
					if (currently_active_turret_no<5){
						currently_active_turret.current_t=currently_active_turret.normal_t;
					}
					turret_three.targeted=false;
					currently_active_turret_no=3;
					number_to_turret();
				}
				if (turret_four.rect.contains(tp_x, tp_y)){
					if (currently_active_turret_no<5){
						currently_active_turret.current_t=currently_active_turret.normal_t;
					}
					turret_four.targeted=false;
					currently_active_turret_no=4;
					number_to_turret();
				}
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
	   
	   private void detarget_the_dead(){
		   float decrement=0;
		   for (Turret turret:turrets){
			   turret.firing_time-=decrement;
			   if (turret.targeted==true){
				   if (!turret.target_mine.actually_there || turret.target_mine.being_detained){
					   turret.targeted=false;
					   decrement+=0.15;
				   }
			   }
		   }
		   end_time-=decrement;
	   }
	
	@Override
	public void render(float delta) {
		
		effective_delta=(float) (delta*TIMESPEED);
		
		status_effects();
		
		move_mines(effective_delta);
		
		move_dots(effective_delta);
		
		time_out_explosions();
		
		
		
		
		
		if (current_status.equals("firing")){
			do_firing_things();
		}
		
		if (current_status.equals("targeting")){
			do_targeting_things();
		}
		
		
		spacey_render(delta);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		draw_iterable_objects();
		
		//draw_turrets();
		
	    batch.draw(shipshield_t, shield_r.x, shield_r.y);
	    
	    if (current_status.equals("targeting") && currently_active_turret_no<5 && currently_active_turret_no>0){
	    	batch.draw(currently_active_turret.target_t,tp_x-30,tp_y-30);
	    }
	    
	    draw_targeting_symbols();
		
		draw_the_statusbar();    
	    
		

		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		check_for_dot_mine_collisions();
		
		check_for_shipshield_mine_collisions();
		
		kill_lost_dots();
		
		collect_captured();
		
		detarget_the_dead();
		
		//System.out.println(total_time+" s");
		
		handle_seconds();
		
		if (total_time>end_time){
			if (current_status.equals("firing")){
				current_status="waiting";
			}
		}
		
		
		
		
		
		shipshield_t=shipshield_normal_t;
		
		if(Gdx.input.justTouched()){
			if (menu_button_r.contains(tp_x, tp_y)){
	    			  game.setScreen(new TitleScreen(game, ANDROID, true));
	    			  dispose();
			}
		}
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

	@Override
	public void dispose() {
		spacey_dispose();
		//bgm.stop();
		//bgm.dispose();
	}
}