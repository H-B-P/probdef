package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Turret_Standard extends Turret {
	float capture_percent;
	float destroy_percent;
	float fail_percent;
	
	   public Turret_Standard(String id){
		   super(id);
		   normal_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+".png"));
		   selected_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"selected.png"));
		   firing_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"firing.png"));
		   
		   overlay_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_emptytop.png"));
		   
		   target_t=new Texture (Gdx.files.internal("turrets/target_"+ident+".png"));
		   
		   if (ident.equals("circle")){
			   
			   capture_percent=50;
			   destroy_percent=0;
			   fail_percent=50;
		   }
		   if (ident.equals("triangle")){
			   
			   capture_percent=30;
			   destroy_percent=40;
			   fail_percent=30;
		   }
		   if (ident.equals("square")){
			   
			   capture_percent=20;
			   destroy_percent=60;
			   fail_percent=20;
		   }
		   if (ident.equals("pentagon")){
			   
			   capture_percent=10;
			   destroy_percent=80;
			   fail_percent=10;
		   }
		   if (ident.equals("hexagon")){
			   
			   capture_percent=0;
			   destroy_percent=100;
			   fail_percent=0;
		   }
		   current_t=normal_t;
		   
		   System.out.println("wut");
	   }
	   
	   @Override
	   
	   public String determine_output(){
		   double roll=Math.random()*100;
		   
		   if (roll<fail_percent){
			   return "fail";
		   }
		   else if (roll<(fail_percent+destroy_percent)){
			   return "destroy";
		   }
		   else{
			   return "capture";
		   }
	   }
	   
}
