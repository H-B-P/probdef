package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class Dot {
	   Rectangle rect;
	   String type;
	   float vert_vel;
	   float horz_vel;
	   Mine target_mine;
	   public Dot(Rectangle origin_r, Mine destination_m, float dotspeed, String typ){
		   
		   type=typ;
		   target_mine=destination_m;
		   
		   
		   
		   float origin_x=origin_r.x+origin_r.width/2;
		   float origin_y=origin_r.y+origin_r.height;
		   rect=new Rectangle();
		   
		   rect.height=19;
		   rect.width=19;
		   
		   rect.setCenter(origin_x, origin_y);
		   
		   float dest_x=destination_m.rect.x+destination_m.rect.width/2;
		   float dest_y=destination_m.rect.y+destination_m.rect.height/2;
		   
		   double jump_x=dest_x-origin_x;
		   double jump_y=dest_y-origin_y;
		   
		   double jump_mag=Math.sqrt(jump_x*jump_x+jump_y*jump_y);
		   
		   horz_vel=(float) (jump_x/jump_mag)*dotspeed;
		   vert_vel=(float) (jump_y/jump_mag)*dotspeed;

		   
	   }
}
