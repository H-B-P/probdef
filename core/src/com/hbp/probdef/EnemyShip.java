package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class EnemyShip {
	   Rectangle rect;
	   
	   Turret turret;
	   
	   float vert_vel;
	   float horz_vel;
	   
	   Rectangle shield;
	   
	   
	   public EnemyShip(int xposn, String turret_id){
		   
		   horz_vel=0;
		   vert_vel=40;
		   
		   turret=new Turret_Standard(turret_id);
		   
		      rect = new Rectangle();
		      double xposn_II = (xposn*30.0+160.0)-30.0;
		      rect.x = (float) xposn_II;
		      rect.y = 420;
		      rect.width = 60;
		      rect.height = 60;
		      
		      
	   }
	   
}