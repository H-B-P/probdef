package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class EnemyShip {
	   Rectangle rect;
	   
	   Turret turret;
	   
	   float vert_vel;
	   float horz_vel;
	   
	   float ylim;
	   
	   boolean actually_there;
	   boolean obscured;
	   
	   float assignedprob_one;
	   float assignedprob_two;
	   float assignedprob_three;
	   
	   Rectangle shield_r;
	   boolean flicker;
	   
	   char front;
	   char back;
	   char left_engine;
	   char right_engine;
	   
	   public EnemyShip(int xposn, String turret_id, boolean obsc){
		   
		   horz_vel=0;
		   vert_vel=100;
		   
		   actually_there=true;
		   obscured=obsc;
		   
		   front='a';
		   back='a';
		   left_engine='a';
		   right_engine='a';
		   
		   turret=new Turret_Standard(turret_id);
		   
		      rect = new Rectangle();
		      double xposn_II = (xposn*50.0+160.0)-30.0;
		      rect.x = (float) xposn_II;
		      rect.y = 480;
		      rect.width = 60;
		      rect.height = 60;
		      
		      ylim=310;
		   
		      handle_shield();
		      
		      flicker=false;
	   }
	   
	   public EnemyShip(int xposn, String turret_id, boolean obsc, char f, char b, char e){
		   this(xposn, turret_id, obsc);
		   front=f;
		   back=b;
		   left_engine=e;
		   right_engine=e;
		   
	   }
	   
	   public EnemyShip(int xposn, String turret_id, boolean obsc, char f, char b, char l_e, char r_e){
		   this(xposn, turret_id, obsc);
		   front=f;
		   back=b;
		   left_engine=l_e;
		   right_engine=r_e;
		   
	   }
	   
	   void handle_shield(){
		   shield_r=new Rectangle(rect.x-10, rect.y-18, 80, 3);
	   }
	   
}
