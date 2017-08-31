package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Turret_Standard extends Turret {
	float capture_percent;
	float destroy_percent;
	float fail_percent;
	
	int turret_level;
	int shotsmade;
	
	   public Turret_Standard(String id){
		   super(id);
		   
		   turret_type="standard";
		   
		   shotsmade=0;
		   
		   turret_level=1;
		   
		   normal_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+".png"));
		   selected_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"selected.png"));
		   firing_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"firing.png"));
		   
		   dead_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"silhouette.png"));
		   
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
		   
		   handle_lines();
		   
	   }
	   
	   public Turret_Standard(String id, int level){
		   this(id);
		   turret_level=level;
		   if (turret_level>1){
			   selected_t=new Texture (Gdx.files.internal("turrets/turret_"+ident+"_"+"selected_multi.png"));
			   target_t=new Texture (Gdx.files.internal("turrets/target_"+ident+"_multi.png"));
		   }
}
	   
	   
	   private void handle_lines(){
		   
		   lines_no=4;
		   
		   line_one="=="+ident.toUpperCase()+"==";
		   line_two="FAIL: "+(int)fail_percent+"%";
		   line_three="DESTROY: "+(int)destroy_percent+"%";
		   line_four="CAPTURE: "+(int)capture_percent+"%";
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
