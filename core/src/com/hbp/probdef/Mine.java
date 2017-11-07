package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class Mine {
	   Rectangle rect;
	   
	   String minetype; //Currently supports "decoy", "regular", "titanium", and "decoytitanium".
	   
	   int turns_to_hit;
	   
	   float vert_vel;
	   float horz_vel;
	   
	   Rectangle shield_one;
		Rectangle shield_two;
		Rectangle shield_three;
		Rectangle shield_four;
	   
	   int shields; //How many shields does it have, at this moment?
	   //There is no difference between a mine which lost all its shields and a mine which started with none.
	   
	   boolean captureproof; //If a captureshot hits it, will it be captured?
	   boolean destroyproof; //If a destroyshot hits it, will it be destroyed?
	   boolean shootproof; //If a turret targets it, will it be able to fire?
	   
	   boolean being_detained;
	   boolean actually_there; //Is the mine currently being dragged offstage, or otherwise incapable of interacting with things?
	   //NOT to be confused with the shootproofness of decoy mines.
	   
	   EnemyShip target_enemy_ship;
	   
	   public Mine(int xposn, float m_speed){
		   
		   horz_vel=0;
		   
		   turns_to_hit=(int)(180f/m_speed);
		   
		   captureproof=false;
		   destroyproof=false;
		   shootproof=false;
		   
		   being_detained=false;
		   
		   actually_there=true;
		   
		   shields=0;
		   
		   minetype="regular";
		   
		      rect = new Rectangle();
		      double xposn_II = (xposn*40.0+160.0)-20.0;
		      rect.x = (float) xposn_II;
		      rect.y = 440;
		      rect.width = 41;
		      rect.height = 41;
		      
		      vert_vel = m_speed;
		      

			   set_up_shields();
		      
	   }
	   
	   public Mine(EnemyShip enemyship){
		   this(0,-100);
		   rect.x=enemyship.turret.rect.x;
		   rect.y=-50;
		   
		   target_enemy_ship=enemyship;
	   }
	   
	   public Mine(int xposn, float m_speed, String m_type){
		   this(xposn, m_speed);
		   minetype=m_type;
		   if (minetype.contains("decoy")){
			   shootproof=true;
		   }
		   if (minetype.contains("titanium")){
			   destroyproof=true;
		   }
	   }
	   
	   public Mine(int xposn, float m_speed, int m_shields){
		   this(xposn, m_speed);
		   shields=m_shields;
	   }
	   
	   public Mine(int xposn, float m_speed, String m_type, int m_shields){
		   this(xposn,m_speed,m_type);
		   shields=m_shields;
	   }
	   
	   void set_up_shields(){
		   shield_one=new Rectangle();
		   shield_one.width=51;
		   shield_one.height=51;
		   shield_one.x=rect.x-5;
		   shield_one.y=rect.y-5;
		   
		   shield_two=new Rectangle();
		   shield_two.width=61;
		   shield_two.height=61;
		   shield_two.x=rect.x-10;
		   shield_two.y=rect.y-10;
		   
		   shield_three=new Rectangle();
		   shield_three.width=71;
		   shield_three.height=71;
		   shield_three.x=rect.x-15;
		   shield_three.y=rect.y-15;
		   
		   shield_four=new Rectangle();
		   shield_four.width=81;
		   shield_four.height=81;
		   shield_four.x=rect.x-20;
		   shield_four.y=rect.y-20;
	   }
}
