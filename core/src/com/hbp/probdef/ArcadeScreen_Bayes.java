package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayes extends GameScreen_Bayes {
	
	final ProbDef game;
	
	boolean CAMPAIGN;
	
	public ArcadeScreen_Bayes(final ProbDef gam, boolean camp) {
		
		super(gam);
		
		game = gam;
		
		CAMPAIGN=camp;
		
		exit_on_shieldfail=true;
		
	}
	
	@Override
	void calculate_score(){
		score=shields+20;
		score=Math.max(score, 0);
	}
	
	@Override
	
	void exit_level(){
		if (CAMPAIGN){
			game.setScreen(new CampaignScreen(game, true));
		}
		else{
			game.setScreen(new SelectScreen_Arcade(game, true));
		}
		dispose();
	}
}