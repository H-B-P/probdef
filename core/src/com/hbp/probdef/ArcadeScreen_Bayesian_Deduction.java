package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayesian_Deduction extends GameScreen_Bayes {
	
	final ProbDef game;
	
	boolean CAMPAIGN;
	
	int original_minecount;
	
	public ArcadeScreen_Bayesian_Deduction(final ProbDef gam, boolean camp) {
		
		super(gam);
		
		game = gam;
		
		shields=10;
		
		number_of_turret_types=3;
		
		CAMPAIGN=camp;
		
		if (CAMPAIGN){
	    	exit_on_shieldfail=true;
	    }
		
	}
	
	@Override
	
	void level_specific_waves(){
		
		
		
		
	}
	
	@Override
	void calculate_score(){
		score=shields+20;
		score=Math.max(score, 0);
	}
	
	@Override
	
	void draw_shields(){
		if (CAMPAIGN){
			if (shields>0){
				batch.draw(shipshield_t, shield_r.x, shield_r.y);
			   }
			   for (int i=0; i<(shields-1); i++){
				   batch.draw(backupshield_t, 160+i*33-(shields-1)*33/2, shield_r.y-6);
			}
		}
		else{
			batch.draw(shipshield_t, shield_r.x, shield_r.y);
			batch.draw(shipshield_t, shield_r.x, shield_r.y-6);
		}
		   
	   }
	
	@Override
	
	void exit_level(){
		if (CAMPAIGN){
			game.setScreen(new CampaignScreen_Inference(game, true));
		}
		else{
			game.setScreen(new SelectScreen_Arcade(game, true));
		}
		dispose();
	}
}