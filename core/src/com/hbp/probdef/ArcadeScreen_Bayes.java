package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes extends GameScreen_Bayes {
	
	final ProbDef game;
	
	public ArcadeScreen_Bayes(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	}
	
}