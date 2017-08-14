package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class Mine {
	   Rectangle rect;
	   float vert_speed;
	   float horz_vel;
	   boolean captureproof;
	   boolean destroyproof;
	   boolean shootproof;
	   
	   boolean being_detained;
	   
	   boolean actually_there;
	   
	   public Mine(){
		   
		   horz_vel=0;
		   
		   captureproof=false;
		   destroyproof=false;
		   shootproof=false;
		   
		   being_detained=false;
		   
		   actually_there=true;
	   }
}
