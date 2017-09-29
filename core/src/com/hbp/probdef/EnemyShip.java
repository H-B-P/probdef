package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class EnemyShip {
	   Rectangle rect;
	   
	   Turret turret;
	   
	   float vert_vel;
	   float horz_vel;
	   
	   float ylim;
	   
	   Rectangle shield;
	   
	   boolean actually_there;
	   boolean obscured;
	   
	   float assignedprob_one;
	   float assignedprob_two;
	   float assignedprob_three;
	   
	   public EnemyShip(int xposn, String turret_id, boolean obsc){
		   
		   horz_vel=0;
		   vert_vel=100;
		   
		   actually_there=true;
		   obscured=obsc;
		   
		   turret=new Turret_Standard(turret_id);
		   
		      rect = new Rectangle();
		      double xposn_II = (xposn*50.0+160.0)-30.0;
		      rect.x = (float) xposn_II;
		      rect.y = 480;
		      rect.width = 60;
		      rect.height = 60;
		      
		      ylim=310;
		      
		      
	   }
	   
}
