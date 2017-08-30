package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class Mine {
	   Rectangle rect;
	   
	   String minetype;
	   float vert_speed;
	   float horz_vel;
	   boolean captureproof;
	   boolean destroyproof;
	   boolean shootproof;
	   
	   boolean being_detained;
	   
	   boolean actually_there;
	   
	   public Mine(int xposn, float m_speed){
		   
		   horz_vel=0;
		   
		   captureproof=false;
		   destroyproof=false;
		   shootproof=false;
		   
		   being_detained=false;
		   
		   actually_there=true;
		   
		   minetype="regular";
		   
		      rect = new Rectangle();
		      double xposn_II = (xposn*40.0+160.0)-20.0;
		      rect.x = (float) xposn_II;
		      rect.y = 440;
		      rect.width = 41;
		      rect.height = 41;
		      
		      vert_speed = m_speed;
	   }
}
