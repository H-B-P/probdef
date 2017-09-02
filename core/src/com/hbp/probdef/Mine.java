package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class Mine {
	   Rectangle rect;
	   
	   String minetype; //Currently supports "shield", "regular", and "titanium".
	   float vert_speed;
	   float horz_vel;
	   
	   int shields_rn;
	   
	   boolean captureproof; //If a captureshot hits it, will it be captured?
	   boolean destroyproof; //If a destroyshot hits it, will it be destroyed?
	   boolean shootproof; //If a turret targets it, will it be able to fire?
	   
	   boolean being_detained;
	   boolean actually_there; //Is the mine currently being dragged offstage, or otherwise incapable of interacting with things?
	   
	   public Mine(int xposn, float m_speed){
		   
		   horz_vel=0;
		   
		   captureproof=false;
		   destroyproof=false;
		   shootproof=false;
		   
		   being_detained=false;
		   
		   actually_there=true;
		   
		   shields_rn=0;
		   
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
