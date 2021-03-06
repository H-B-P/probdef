package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This is the root of gameplay that's based on standard turrets (i.e. not Bayes). Unextended, it doubles as the tutorial.
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

public class GameScreen_Prob extends GameScreen {
	
	final ProbDef game;
   
   
   
   private Mine the_selected_mine;
   
   Turret turret_one;
   Turret turret_two;
   Turret turret_three;
   Turret turret_four;
   
   Turret currently_active_turret;
   
   int currently_active_turret_no; //this is one hell of an overburdened variable and probably needs refactoring.
   //When 0, means no turret has targeted or had the chance to. When 5, means all turrets have had a chance to target.
   	
   private Texture indicate_turrets_t;
   private Texture indicate_mines_t;
   
   
	
	public GameScreen_Prob(final ProbDef gam) {
		
		super(gam);
		game=gam;
		
		bayesian=false;
		
		batch= new SpriteBatch();
	      
		indicate=false;
	      indicate_turrets_t=new Texture(Gdx.files.internal("indicate_turrets.png"));
	      indicate_mines_t=new Texture(Gdx.files.internal("indicate_mines.png"));
	      
	      writing_symbol_visible=false;
	      
	      captured=0;
	      destroyed=0;
	      
	      
	      minecount=20;
	      shields=100;
	      
	      timeouting=true;
	      
	      turret_setup();
	      
	      infuriatingly_specific_bool=false; //so infuriating
	      
	      purpletext=false;
	      
	      mine_trim_t=new Texture(Gdx.files.internal("mine_trim.png"));
	      
	      attention_button_trim_t=orange_button_trim_t;
	      
	}
	
	//---Level-specific functions---
	
	//--Level-specific_setup--
	
	void level_specific_turret_setup(){
		if (hardcoded_opt_packagename.equals("Combination")){
			turret_one=new Turret_Standard("circle");
			turret_two=new Turret_Standard("triangle");
			turret_three=new Turret_Standard("square");
			turret_four=new Turret_Standard("pentagon");
		}
		else{
			turret_one=new Turret_Standard("triangle");
			turret_two=new Turret_Standard("square");
			turret_three=new Turret_Standard("pentagon");
			turret_four=new Turret_Standard("hexagon");
		   
		}
	   turrets_standard.add((Turret_Standard) turret_one);
	   turrets_standard.add((Turret_Standard) turret_two);
	   turrets_standard.add((Turret_Standard) turret_three);
	   turrets_standard.add((Turret_Standard) turret_four);
		   
	}
	   
	//--What actually happens in the level?--
	
	@Override
	
	   void level_specific_events(){
		   if (seconds==2){
				spawnMine(-1, 65);
				spawnMine(1, 65);
				
			}
			if (seconds==8){
				spawnMine(-3,65);
				spawnMine(3,65);
			}
			if (seconds==14){
				spawnMine(-2,65);
				spawnMine(0,65);
				spawnMine(2,65);
			}
			if (seconds==20){
				spawnMine(-2,45);
				spawnMine(0,100);
				spawnMine(2,65);
				
			}
			if (seconds==28){
				spawnMine(-3, 45);
				spawnMine(0,65);
				spawnMine(2,65);
			}
			if (seconds==34){
				spawnMine(-3,45);
				spawnMine(-1, 45);
				spawnMine(1,45);
				spawnMine(3, 45);
			}
			if (seconds==40){
				spawnMine(-3,100);
				spawnMine(-1, 100);
				spawnMine(2,100);
			}
			
	   }
	   
	   void level_specific_timeline(){
		   show_the_text=false;
		   suppress_freezes=false;
		   purpletext=false;
		   indicate=false;
			   if (total_time<5){   
				   if(TIMESPEED==0){
					   indicate=true;
					   show_the_text=true;
					   if (!turret_one.targeted){
						   the_text="Mines threaten your ship. Every second, time freezes. Click on a mine while time is frozen to target it.";
					   }
					   else{
						   the_text="Keep clicking on mines until every turret is targeted.";
					   }
					   if (turret_one.targeted && turret_two.targeted){
						   if (!turret_one.target_mine.equals(turret_two.target_mine)){
							   purpletext=true;
							   the_text="(btw you can target a mine with more than one turret in case that wasn't obvious)";
						   }
						   else{
							   purpletext=true;
							   the_text="(btw you probably shouldn't target all your turrets on the same mine)";
						   }
					   }
					   if (currently_active_turret_no==5){
						   purpletext=false;
						   the_text="Once you're done targeting, click the fire button at the top of the screen to launch a volley of attacks.";
					   }
				   }
			   }
			   if (total_time>10 && total_time<11 && TIMESPEED==0){
				   show_the_text=true;
				   if(hardcoded_opt_packagename.equals("Combination")){
					   if (currently_active_turret_no==1){
						   the_text="Circle Turrets fail 50% of the time, and capture 50% of the time.";
					   }
					   else if (currently_active_turret_no==2){
						   the_text="Triangle Turrets fail 30% of the time, capture 30% of the time, and destroy 40% of the time.";
					   }
					   else if (currently_active_turret_no==3){
						   the_text="Square Turrets fail 20% of the time, capture 20% of the time, and destroy 60% of the time.";
					   }
					   else if (currently_active_turret_no==4){
						   the_text="Pentagon Turrets fail 10% of the time, capture 10% of the time, and destroy 80% of the time.";
					   }
					   else if (currently_active_turret_no==5){
						   the_text="You can remind yourself of these probabilities at any time by hovering your mouse over a turret.";
					   }
				   }
				   else{
					   if (currently_active_turret_no==1){
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
						   the_text="You can remind yourself of these probabilities at any time by hovering your mouse over a turret.";
					   }
				   }
			   }
			   if (total_time>16 && total_time<17 && TIMESPEED==0){
				   show_the_text=true;
				   the_text="The percentage below a mine shows the probability it will remain after the volley.";
				   if (turret_one.targeted){
					   the_text="Notice how this changes as you target turrets.";
				   }
				   if (turret_two.targeted){
					   infuriatingly_specific_bool=true;
				   }
				   if (infuriatingly_specific_bool){
					   the_text="If you change your mind before firing, you can click on the relevant turret to re-target it.";
				   }
				   if (currently_active_turret_no==5){
					   purpletext=true;
					   the_text="(jsyk you can skip over some turrets if you want but it's usually a good idea to target them all)";
				   }
			   }
			   if (total_time>22 && total_time<23){
				   show_the_text=true;
				   the_text="Mines won't always have the same speed, so prioritise.\nHover over a mine to see how many turns it will take to hit.";
			   }
			   
			   if (total_time>30 && total_time<31){
				   if (TIMESPEED==0){
					   show_the_text=true;
					   the_text="If you prefer, you can use left/right arrow keys and the spacebar to select mines.";
					   if (turret_one.targeted){
						   purpletext=true;
						   the_text="(you can also use asdf or 1234 to select turrets if for some reason that seems like a good idea to you)";
					   }
					   if (turret_two.targeted){
						   purpletext=false;
						   the_text="And if you want to skip a turret via the keyboard, press space with no mine selected.";
						   
					   }
					   if (currently_active_turret_no==5){
						   purpletext=false;
						   the_text="Similarly: when you're done targeting, you can use the spacebar to fire.";
					   }
				   }
				   
				   
			   }
	   }
	   
	   @Override
	   
	   void level_specific_HUD(){
		   font.draw(batch, "MINES: "+minecount, 90, 464, 140, 1, true);
		   font.draw(batch, "CAPTURED: "+captured, 90, 446, 140, 1, true);
		   font.draw(batch, "DESTROYED: "+ destroyed, 90, 428, 140, 1, true);
	   }
	   //--Autocalc--
	   
	   void autocalc_and_display(String displaywhat){
		   for (Mine mine:mines){
			   if (screen_proper.overlaps(mine.rect)){
				   float four=0f;
				   float three=0f;
				   float two=0f;
				   float one=0f;
				   float zero=0f;
				   float destroy=0f;
				   float destroy_extra=0f;
				   float capture=0f;
				   float capture_extra=0f;
				   
				   if (mine.shields==4){
					   four=1f;
				   }
				   if (mine.shields==3){
					   three=1f;
				   }
				   if (mine.shields==2){
					   two=1f;
				   }
				   if (mine.shields==1){
					   one=1f;
				   }
				   if (mine.shields==0){
					   zero=1f;
				   }
				   
				   for (Turret_Standard turret_standard:turrets_standard){
					   if (turret_standard.targeted){
						   if (turret_standard.target_mine!=null){
							   if (turret_standard.target_mine.equals(mine)){
								   for (int i=0; i<turret_standard.turret_level;i++){
									   destroy_extra=0;
									   capture_extra=0;
									   if (!mine.destroyproof){
										   destroy_extra=zero*turret_standard.destroy_percent/100.0f;
										   destroy= destroy + destroy_extra;
									   }
										   
									   if (!mine.captureproof){
										   capture_extra=zero*turret_standard.capture_percent/100.0f;
										   capture= capture + capture_extra;
									   }
									   zero= (zero-destroy_extra-capture_extra) + one*(1f-turret_standard.fail_percent/100.0f);
									   one= one*turret_standard.fail_percent/100.0f + two*(1f-turret_standard.fail_percent/100.0f);
									   two= two*turret_standard.fail_percent/100.0f + three*(1f-turret_standard.fail_percent/100.0f);
									   three=three*turret_standard.fail_percent/100.0f + four*(1f-turret_standard.fail_percent/100.0f);
									   four=four*turret_standard.fail_percent/100.0f;
								   }
							   }
						   }
					   }
				   }
				   
				   float survival=zero+one+two+three+four;
				   if (!option_turns_to_hit_display.equals("Below")){
				   
					   if (displaywhat.equals("result")){
						   if (mine.destroyproof){
							   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
						   }
						   else{
							   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_redfont.draw(batch, " "+present_float(destroy*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
						   }
						   
					   }
					   else if (displaywhat.equals("shields")){
						   if (mine.shields==0){
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
						   }
						   else if (mine.shields==1){
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);

						   }
						   else if (mine.shields==2){
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
						   }
						   else if (mine.shields==3){
							   acalc_grayfont.draw(batch, "3:"+present_float(three*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
						   }
						   else if (mine.shields==4){
							   acalc_grayfont.draw(batch, "4:"+present_float(four*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
							   acalc_grayfont.draw(batch, "3:"+present_float(three*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-80, 81, 1, true);
						   }
					   }
					   else if (displaywhat.equals("destroy")){
						   acalc_redfont.draw(batch, " "+present_float(destroy*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
					   }
					   else if (displaywhat.equals("capture")){
						   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
					   }
					   else if (displaywhat.equals("nothing")){
						   
					   }
					   else{
						   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
					   }
				   }
				   else{
					   acalc_greenfont.draw(batch, mine.turns_to_hit+" TTH", mine.rect.x-20, mine.rect.y-20, 81, 1, true);
					   if (displaywhat.equals("result")){
						   if (mine.destroyproof){
							   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
						   }
						   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
						   acalc_redfont.draw(batch, " "+present_float(destroy*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
						   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
					   }
					   else if (displaywhat.equals("shields")){
						   if (mine.shields==0){
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
						   }
						   else if (mine.shields==1){
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);

						   }
						   else if (mine.shields==2){
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
						   }
						   else if (mine.shields==3){
							   acalc_grayfont.draw(batch, "3:"+present_float(three*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-80, 81, 1, true);
						   }
						   else if (mine.shields==4){
							   acalc_grayfont.draw(batch, "4:"+present_float(four*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
							   acalc_grayfont.draw(batch, "3:"+present_float(three*100.0f)+"%", mine.rect.x-20, mine.rect.y-50, 81, 1, true);
							   acalc_grayfont.draw(batch, "2:"+present_float(two*100.0f)+"%", mine.rect.x-20, mine.rect.y-65, 81, 1, true);
							   acalc_grayfont.draw(batch, "1:"+present_float(one*100.0f)+"%", mine.rect.x-20, mine.rect.y-80, 81, 1, true);
							   acalc_grayfont.draw(batch, "0:"+present_float(zero*100.0f)+"%", mine.rect.x-20, mine.rect.y-95, 81, 1, true);
						   }
					   }
					   else if (displaywhat.equals("destroy")){
						   acalc_redfont.draw(batch, " "+present_float(destroy*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
					   }
					   else if (displaywhat.equals("capture")){
						   acalc_bluefont.draw(batch, " "+present_float(capture*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
					   }
					   else if (displaywhat.equals("nothing")){
						   
					   }
					   else{
						   acalc_grayfont.draw(batch, " "+present_float(survival*100.0f)+"%", mine.rect.x-20, mine.rect.y-35, 81, 1, true);
					   }
				   }
			   }
		   }
	   }
	   
	   void autocalc_and_display_dummy(){
		   if (option_acalc.equals("Off")){
			   autocalc_and_display("nothing");
		   }
		   if (option_acalc.equals("Normal")){
			   autocalc_and_display("survive");
		   }
		   if (option_acalc.equals("Detail") || option_acalc.contains("Result")){
			   autocalc_and_display("result");
		   }
		   if (option_acalc.contains("Shield")){
			   autocalc_and_display("shields");
		   }
		   
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
		   mines.add(mine);
		         
	   }
	   
	   void spawnShieldMine(int xposn, float m_speed, int shields) {
		   Mine mine= new Mine(xposn, m_speed, shields);
		   mines.add(mine);
	   }
	   
	   void spawnDecoyMine(int xposn, float m_speed) {
		   Mine mine= new Mine(xposn, m_speed, "decoy");
		   mines.add(mine);
	   }
	   
	   void spawnTitaniumMine(int xposn, float m_speed) {
		   Mine mine= new Mine(xposn, m_speed, "titanium");
		   mines.add(mine);
	   }
	   
	   void spawnShieldedTitaniumMine(int xposn, float m_speed, int shields) {
		   Mine mine= new Mine(xposn, m_speed, "titanium", shields);
		   mines.add(mine);
	   }
	   
	   void spawnDecoyProbablistic(int xposn, float m_speed, int percentage_chance_of_decoy){
		   if (Math.random()*100.0<percentage_chance_of_decoy){
			   spawnDecoyMine(xposn, m_speed);
		   }
		   else{
			   spawnMine(xposn, m_speed);
		   }
	   }
	   
	   
	   
	   //---Drawing functions---
	   
	   @Override
	   
	   boolean should_firing_button_be_lit_up(){
		   if (current_status.equals("targeting") && currently_active_turret_no==5){
			   return true;
		   }
		   return false;
	   }
	   
	   @Override
	   
	   void draw_the_HUD(){
		   boolean torrit=false;
		   boolean mien=false;
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
		    	  
		    	  if (option_turns_to_hit_display.equals("Normal") && current_status.equals("targeting")){
			    	  for (Mine mine:mines){
			    		  if (mine.rect.contains(tp_x,tp_y) && screen_proper.overlaps(mine.rect)){
			    			  mien=true;
			    			  if (!mine.destroyproof){
			    				  font.draw(batch, "==MINE==", 90, 455, 140, 1, true);
			    			  }
			    			  else{
			    				  font.draw(batch, "==T MINE==", 90, 455, 140, 1, true);
			    			  }
			    			  if (mine.turns_to_hit==1){
				    			  font.draw(batch, mine.turns_to_hit+" turn to hit", 90, 437, 140, 1, true);
			    			  }
			    			  else{
				    			  font.draw(batch, mine.turns_to_hit+" turns to hit", 90, 437, 140, 1, true);
			    			  }
			    		  }
			    	  }
			    	  if (!mien){
			    		  if (the_selected_mine!=null){
			    			  mien=true;
			    			  if (!the_selected_mine.destroyproof){
			    				  font.draw(batch, "==MINE==", 90, 455, 140, 1, true);
			    			  }
			    			  else{
			    				  font.draw(batch, "==T MINE==", 90, 455, 140, 1, true);
			    			  }
			    			  if (the_selected_mine.turns_to_hit==1){
				    			  font.draw(batch, the_selected_mine.turns_to_hit+" turn to hit", 90, 437, 140, 1, true);
			    			  }
			    			  else{
				    			  font.draw(batch, the_selected_mine.turns_to_hit+" turns to hit", 90, 437, 140, 1, true);
			    			  }
			    		  }
			    	  }
		    	  }
		    	  
		    	  if (!mien){
		    		  level_specific_HUD();
		    	  }
		      }
	   }
	   
	   
	   void draw_mine_trim(){
		   if (the_selected_mine!=null && currently_active_turret_no<5){
			   batch.draw(mine_trim_t, the_selected_mine.rect.x-20, the_selected_mine.rect.y-20);
		   }
	   }
	   
	   
	   
	   private void draw_targeting_symbols(){
		   if (turret_one.targeted && turret_one.target_mine!=null){
			   batch.draw(turret_one.target_t, turret_one.target_mine.rect.x-25, turret_one.target_mine.rect.y+5);
			   //batch.draw(turret_one.target_t, turret_one.target_mine.rect.x-10, turret_one.target_mine.rect.y-10);
			   draw_orange_dotted_line(turret_one.rect.x+turret_one.rect.width/2f, turret_one.rect.y+turret_one.rect.height*3f/4f, turret_one.target_mine.rect.x+turret_one.target_mine.rect.width/2-15, turret_one.target_mine.rect.y+turret_one.target_mine.rect.height/2+15,  10);
		   }
		   if (turret_two.targeted && turret_two.target_mine!=null){
			   batch.draw(turret_two.target_t, turret_two.target_mine.rect.x+5, turret_two.target_mine.rect.y+5);
			   //batch.draw(turret_two.target_t, turret_two.target_mine.rect.x-10, turret_two.target_mine.rect.y-10);
			   draw_orange_dotted_line(turret_two.rect.x+turret_two.rect.width/2f, turret_two.rect.y+turret_two.rect.height*3f/4f,  turret_two.target_mine.rect.x+turret_two.target_mine.rect.width/2+15, turret_two.target_mine.rect.y+turret_two.target_mine.rect.height/2+15, 10);
		   }
		   if (turret_three.targeted&& turret_three.target_mine!=null){
			   //batch.draw(turret_three.target_t, turret_three.target_mine.rect.x-10, turret_three.target_mine.rect.y-10);
			   draw_orange_dotted_line(turret_three.rect.x+turret_three.rect.width/2f, turret_three.rect.y+turret_three.rect.height*3f/4f, turret_three.target_mine.rect.x+turret_three.target_mine.rect.width/2-15, turret_three.target_mine.rect.y+turret_three.target_mine.rect.height/2-15,  10);
			   batch.draw(turret_three.target_t, turret_three.target_mine.rect.x-25, turret_three.target_mine.rect.y-25);
		   }
		   if (turret_four.targeted && turret_four.target_mine!=null){
			   //batch.draw(turret_four.target_t, turret_four.target_mine.rect.x-10, turret_four.target_mine.rect.y-10);
			   draw_orange_dotted_line(turret_four.rect.x+turret_four.rect.width/2f, turret_four.rect.y+turret_four.rect.height*3f/4f, turret_four.target_mine.rect.x+turret_four.target_mine.rect.width/2+15, turret_four.target_mine.rect.y+turret_four.target_mine.rect.height/2-15, 10);
			   batch.draw(turret_four.target_t, turret_four.target_mine.rect.x+5, turret_four.target_mine.rect.y-25);
		   }
	   }
	   
	   
	   private void draw_orange_dotted_line(float start_x, float start_y, float finish_x, float finish_y, int number_of_divs){
		   for (int q=1; q<number_of_divs; q++){
			   float centre_x=start_x+((float)q/(float)number_of_divs)*(finish_x-start_x);
			   float centre_y=start_y+((float)q/(float)number_of_divs)*(finish_y-start_y);
			   batch.draw(orange_dot_t, centre_x-1, centre_y-1);
		   }
	   }
	   
	   //---Generally useful functions---
	   
	   
	   
	   private void check_for_shipshield_mine_collisions(){
		   for (Mine mine: mines){
				//if(mine.rect.overlaps(shield_r)) {
			   if(mine.rect.overlaps(shield_r) || mine.rect.y<shield_r.y) {
					minecount-=1;
					if (!mine.shootproof){
				     	spawnExplosion(mine.rect.x,mine.rect.y);
				        shields-=1;
				        minehitshield.play(option_sfx_volume*0.4f);
				        minesplode.play(option_sfx_volume);
				        if (option_flicker){
				        	shipshield_t=shipshield_flicker_t;
				        }
					}
					mines.removeValue(mine,true);
			     }
			}
	   }
	   
	   
	   @Override
	   void handle_seconds(){
		   if (seconds<Math.floor(total_time)){
				seconds+=1;
				if (seconds%2==0 && any_targetable_mines() && !suppress_freezes){
					current_status="targeting";
					TIMESPEED=0;
					currently_active_turret_no=1;
					number_to_turret();
				}
				System.out.println(seconds+" s");
				
				level_specific_events();
				
				
				
			}
	   }
	   
	   boolean any_targetable_mines(){
		   for (Mine mine:mines){
			   if (mine.rect.overlaps(screen_proper)){
				   return true;
			   }
		   }
		   return false;
	   }
	   
	   private Mine pick_a_mine(){
		   for(Mine mine: mines) {
			   if (mine.rect.overlaps(screen_proper)){
				   if (mine.rect.contains(tp_x, tp_y) && tp_y<440){
					   return mine;
				   }
				   if (mine.shields>=1){
					   if (mine.shield_one.contains(tp_x,tp_y)){
						   return mine;
					   }
				   }
				   if (mine.shields>=2){
					   if (mine.shield_two.contains(tp_x,tp_y)){
						   return mine;
					   }
				   }
				   if (mine.shields>=3){
					   if (mine.shield_three.contains(tp_x,tp_y)){
						   return mine;
					   }
				   }
				   if (mine.shields>=4){
					   if (mine.shield_four.contains(tp_x,tp_y)){
						   return mine;
					   }
				   }
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
	   
	   
	   
	   private void skip_through_turrets(){
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
	   
	   private void hand_over_to_firing(){
		   current_status="firing";
			set_up_firing_times();
			turret_one.current_t=turret_one.normal_t;
			turret_two.current_t=turret_two.normal_t;
			turret_three.current_t=turret_three.normal_t;
			turret_four.current_t=turret_four.normal_t;
			the_selected_mine=null;
			
	   }
	   
	   
	   
	   
	   

	   
	   
	   //--Function for hotkeys--
	   
	   private void check_for_keypresses(){
		   
		   //handle the final firing.
		   if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			   if(currently_active_turret_no>4){
				   hand_over_to_firing();
			   }
			   else{
				   
				   if (the_selected_mine!=null){
					   currently_active_turret.targeted=true;
					   currently_active_turret.target_mine=the_selected_mine;
				   }
				   currently_active_turret.current_t=currently_active_turret.normal_t;
				   currently_active_turret_no+=1;
				   number_to_turret();
				   skip_through_turrets();
			   }
		   }
		   //pick turrets using ASDF or 1234.
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_1) || Gdx.input.isKeyJustPressed(Keys.A)){
			   if (currently_active_turret_no<5){
				   currently_active_turret.current_t=currently_active_turret.normal_t;
			   }
			   currently_active_turret_no=1;
			   turret_one.targeted=false;
			   turret_one.target_mine=null;
			   number_to_turret();
		   }
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_2) || Gdx.input.isKeyJustPressed(Keys.S)){
			   if (currently_active_turret_no<5){
				   currently_active_turret.current_t=currently_active_turret.normal_t;
			   }
			   currently_active_turret_no=2;
			   turret_two.targeted=false;
			   turret_two.target_mine=null;
			   number_to_turret();
		   }
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_3) || Gdx.input.isKeyJustPressed(Keys.D)){
			   if (currently_active_turret_no<5){
				   currently_active_turret.current_t=currently_active_turret.normal_t;
			   }
			   currently_active_turret_no=3;
			   turret_three.targeted=false;
			   turret_three.target_mine=null;
			   number_to_turret();
		   }
		   if (Gdx.input.isKeyJustPressed(Keys.NUM_4) || Gdx.input.isKeyJustPressed(Keys.F)){
			   if (currently_active_turret_no<5){
				   currently_active_turret.current_t=currently_active_turret.normal_t;
			   }
			   currently_active_turret_no=4;
			   turret_four.targeted=false;
			   turret_four.target_mine=null;
			   number_to_turret();
		   }
		   //Arrow keys to select
		   if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			   cycle_mines_forward();
		   }
		   if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
			   cycle_mines_back();
		   }
	   }
	   
	   private void cycle_mines_forward(){
		   if (the_selected_mine==null){
			   the_selected_mine=mines.first();
			   if (!screen_proper.overlaps(the_selected_mine.rect)){
				   cycle_mines_forward();
			   }
		   }
		   else if (the_selected_mine==mines.peek()){
			   the_selected_mine=null;
		   }
		   else{
			   int q=mines.indexOf(the_selected_mine, true);
			   int j=(q+1)%mines.size;
			   the_selected_mine=mines.get(j);
			   if (!screen_proper.overlaps(the_selected_mine.rect)){
				   cycle_mines_forward();
			   }
		   }
	   }
	   
	   private void cycle_mines_back(){
		   if (the_selected_mine==null){
			   the_selected_mine=mines.peek();
			   if (!screen_proper.overlaps(the_selected_mine.rect)){
				   cycle_mines_back();
			   }
		   }
		   else if (the_selected_mine==mines.first()){
			   the_selected_mine=null;
		   }
		   else{
			   int q=mines.indexOf(the_selected_mine, true);
			   int j=(q+mines.size-1)%mines.size;
			   the_selected_mine=mines.get(j);
			   if (!screen_proper.overlaps(the_selected_mine.rect)){
				   cycle_mines_back();
			   }
		   }
	   }
	   
	   
	   //--Do things depending on the current game status--
	   
	   private void do_firing_things(){
		   for(Turret turret: turrets) {
			   if (turret.target_mine!=null){
				   if (!turret.target_mine.being_detained && turret.target_mine.actually_there){
					   if (turret.turret_type.equals("standard")){
						   Turret_Standard T=(Turret_Standard)turret;
						   if (T.targeted && (T.firing_time+0.01f*T.shotsmade)<total_time){
							   if (T.shotsmade==0){fire.play(option_sfx_volume*0.3f);}
							   T.shotsmade+=1;
							   if (!T.target_mine.shootproof){
								   RT_Dot dot=new RT_Dot(turret.rect, turret.target_mine, 3000, turret.determine_output());
								   dots.add(dot);
							   }
						   }
						   
						   if (T.shotsmade>=T.turret_level){
							   T.targeted=false;
						   }
						   
					   }
					   
					   
				   }
				   else{
					   turret.targeted=false;
				   }
			   }
			   if (turret.firing_time<total_time && (turret.firing_time+0.1f)>total_time){
				   turret.current_t=turret.firing_t;
			   }
			   else{
					turret.current_t=turret.normal_t;
			   }
		   }
		   
		   if (total_time>volley_ending_time){
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
			   
			   skip_through_turrets();
		   }
		   
		   
		   if (Gdx.input.justTouched()){
				if (fire_button_r.contains(tp_x, tp_y)){
					if (current_status.equals("targeting")){
						hand_over_to_firing();
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
	   
	   
	   
	   public void probgame_render(float delta){
		   gamey_render_predraw(delta); //Handle everything which happens before the actual drawing.
			
		   for (Mine mine:mines){
			   float t=(mine.rect.y-(shield_r.y+shield_r.height))/mine.vert_vel;
				mine.turns_to_hit=(int)t/2 + 1;
			}
		   
			batch.begin();
			
			batch.setProjectionMatrix(camera.combined);
			
			   gamey_render_draw_objects(); //Draw material objects: mines, shields, etc.

		    
		    if (current_status.equals("targeting")){
				autocalc_and_display_dummy();
				draw_mine_trim();
			}
		    
		    if (current_status.equals("targeting") && currently_active_turret_no<5 && currently_active_turret_no>0){
		    	batch.draw(currently_active_turret.target_t,tp_x-30,tp_y-30);
		    	draw_orange_dotted_line(currently_active_turret.rect.x+currently_active_turret.rect.width/2, currently_active_turret.rect.y+currently_active_turret.rect.height*3/4,tp_x,tp_y,10);
		    }
		    
		    draw_targeting_symbols(); //You know, the oddly-shaped crosshair things? Draw them.
			
			gamey_render_draw_interface();
			
			if (timeouting_rn){
				if (shields>0){
					batch.draw(complete_box_t, 80, 200);
				}
				else{
					batch.draw(failed_box_t, 80, 200);
				}
				
			}
			
			if (indicate){
				   batch.draw(indicate_turrets_t, 50, 5);
				   batch.draw(indicate_mines_t, 100, 375);
			}
			
			if (writing_symbol_visible){
				   batch.draw(writey_symbol_t, 10, 330);
			}
			
			batch.end();
			
			//Do appropriate things!
			
			if (current_status.equals("firing")){
				do_firing_things();
			}
			
			if (current_status.equals("targeting")){
				check_for_keypresses();
				do_targeting_things();
			}
			
			shipshield_t=shipshield_normal_t; // If the shield is flickering, we want it to flicker for only one frame; we reset it here.
			
			//check for collisions between physical objects which interact.
			
			
			
			check_for_dot_mineshield_collisions();
			
			check_for_dot_mine_collisions();
			
			
			
			check_for_shipshield_mine_collisions();
			
			gamey_render_postdraw();
			
			
			
			
			if (current_status.equals("firing") && total_time>volley_ending_time){
				current_status="waiting";
			}
			
			
			
			
			
			
			
			
			if (minecount==0 && explosions.size==0 && timeouting && !timeouting_rn){
				timeouting_rn=true;
				timeout_time=player_time+3;
			}
			
			if (shields<=0 && !timeouting_rn){
				timeouting_rn=true;
				timeout_time=player_time+3;
			}
			
			//---Handle all the exits!---
			
			//if (shields<=0 && exit_on_shieldfail){
			//	exit_level();
			//}
			if (timeouting_rn && player_time>timeout_time){
				if (shields>0){
					update_score_on_exit();
				}
				exit_level();
			}
			else if(Gdx.input.justTouched()){
				if (menu_button_r.contains(tp_x, tp_y)){
					if (timeouting && timeouting_rn){
						update_score_on_exit();
					}
					exit_level();
				}
			}
	   }
	   
	   
	   
	@Override
	public void render(float delta) {
		
		probgame_render(delta);
		
	}
	
	public void probgame_dispose(){
		gamey_dispose();
		
	}
	
	@Override
	public void dispose() {
		probgame_dispose();
		
	}
}