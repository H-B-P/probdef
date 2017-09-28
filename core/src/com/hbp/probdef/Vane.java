package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Vane {
	   Rectangle rect;
	   
	   
	   boolean does_it_work;
	   boolean targeted;
	   
	   String current_energy;
	   
	   EnemyShip target_ship;
	   
	   float firing_time;
	   
	   public Vane(){
		   
		   rect=new Rectangle();
		   rect.width=60;
		   rect.height=60;
		   
		   does_it_work=true;
		   targeted=false;
		   
		   current_energy="none";
	   }
	   
}
