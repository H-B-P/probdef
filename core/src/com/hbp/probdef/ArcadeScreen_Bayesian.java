package com.hbp.probdef;

import com.hbp.probdef.ProbDef;

public class ArcadeScreen_Bayesian extends GameScreen_Bayes {
	
	final ProbDef game;
	
	boolean CAMPAIGN;
	
	int original_minecount;
	
	public ArcadeScreen_Bayesian(final ProbDef gam, boolean camp) {
		
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
		
		suppress_phasing=false;
		
		if (shipwave==1){
			ship_two_spawn_enemy_ship(-2, turret_type_one, false);
			ship_one_spawn_enemy_ship(0, turret_type_two, false);
			ship_three_spawn_enemy_ship(2, turret_type_three, false);
			suppress_phasing=true;
		}
		if (shipwave==2){
			ship_one_spawn_random(0,true);
		}
		if (shipwave==3){
			ship_one_spawn_random(-1,true);
			ship_two_spawn_random(1,true);
		}
		if (shipwave==4){
			ship_two_spawn_random(-1,true);
			ship_three_spawn_random(1,true);
		}
		if (shipwave==5){
			ship_three_spawn_random(-1, true);
			ship_one_spawn_random(1, true);
		}
		if (shipwave==6){
			ship_one_spawn_random(-2, true);
			ship_two_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		if (shipwave==7){
			ship_two_spawn_random(-2, true);
			ship_one_spawn_random(0, true);
			ship_two_spawn_random(2, true);
		}
		if (shipwave==8){
			ship_three_spawn_random(-2, true);
			ship_one_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		if (shipwave==9){
			ship_two_spawn_random(-2, true);
			ship_two_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		if (shipwave==10){
			ship_three_spawn_random(-2, true);
			ship_two_spawn_random(0, true);
			ship_three_spawn_random(2, true);
		}
		
		
	}
	
	@Override
	void calculate_score(){
		score=shields+dead_ships;
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