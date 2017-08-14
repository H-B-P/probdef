package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Turret {
	   Rectangle rect;
	   
	   Texture normal_t;
	   Texture firing_t;
	   Texture selected_t;
	   Texture dead_t;
	   Texture overlay_t;
	   
	   Texture current_t;
	   
	   Texture target_t;
		Mine target_mine;
		
		float firing_time;
	   
	   String ident;
	   
	   boolean does_it_work;
	   boolean targeted;
	   
	   public Turret(String id){
		   ident=id;
		   
		   rect=new Rectangle();
		   rect.width=40;
		   rect.height=40;
		   
		   does_it_work=true;
		   targeted=false;
	   }
	   
	   String determine_output(){
		   return "destroy";
	   }
	   
}
