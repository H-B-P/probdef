package com.hbp.probdef;

import com.badlogic.gdx.math.Rectangle;

public class ShieldMine extends Mine {
	
	Rectangle shield_one;
	Rectangle shield_two;
	Rectangle shield_three;
	Rectangle shield_four;
	
	public ShieldMine(int xposn, float m_speed, int shields){
		   
		   super(xposn, m_speed);
		   
		   minetype="shield";
		   
		   shields_rn=shields;
		   
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
