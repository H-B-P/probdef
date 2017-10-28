package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes extends GameScreen_Bayes {
	
	final ProbDef game;
	
	boolean CAMPAIGN;
	
	public ArcadeScreen_Bayes(final ProbDef gam, boolean camp) {
		
		super(gam);
		
		game = gam;
		
		CAMPAIGN=camp;
		
		exit_to="arcade";
		
	}
	
	@Override
	void calculate_score(){
		score=shields+20;
		score=Math.max(score, 0);
	}
}