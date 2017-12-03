package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob extends GameScreen_Prob {
	
	final ProbDef game;

	String ordinary_minetype;
	
	int decoyfreq;
	int ordinary_shields_no;
	int whichwave;
	
	int wave_number;
	int wave_number_total;
	
	int extra_mines;
		
	boolean CAMPAIGN;
	
	public ArcadeScreen_Prob(final ProbDef gam, boolean camp) {
		
		super(gam);
		
		game = gam;
		
		CAMPAIGN=camp;
		
		captured=0;
	    destroyed=0;
	    
	    if (hardcoded_opt_packagename.equals("Combination")){
	    	shields=5;
	    }
	    else{
	    	shields=3;
	    }
	    
	    score=0;
	    
	    ordinary_minetype="generic";
	    decoyfreq=0;
	    
	    whichwave=0;
	    
	    wave_number=0;
	    wave_number_total=6;
	    
	    if (CAMPAIGN){
	    	exit_on_shieldfail=true;
	    }
	    
	    extra_mines=0;
	    
	    timeouting=true;
	}
	
	@Override
	
	void exit_level(){
		if (CAMPAIGN){
			if (hardcoded_opt_packagename.equals("Combination")){
				game.setScreen(new CampaignScreen_Combination(game, true));
				dispose();
			}
			else{
				game.setScreen(new CampaignScreen_Inference(game, true));
				dispose();
			}
			
		}
		else{
			game.setScreen(new SelectScreen_Arcade(game, true));
			dispose();
		}
	}
	
	void wave_number_update(int target_seconds, int target_wave_number){
		if (seconds>=target_seconds && wave_number<target_wave_number){
			wave_number=target_wave_number;
		}
	}
	
	void four_wave (int start_second, int normal_ops){
		if (seconds==start_second){
			spawn_formation_mine(-2,normal_ops);
		}
		if (seconds==(start_second+2)){
			spawn_formation_mine(2,normal_ops);
		}
		if (seconds==(start_second+4)){
			spawn_formation_mine(-2,normal_ops);
		}
		if (seconds==(start_second+6)){
			spawn_formation_mine(2,normal_ops);
		}
	}
	
	void six_wave_single_slow (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-2,normal_ops-1);
			spawn_formation_mine(2,normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(0,normal_ops);
		}
	}
	
	void six_wave_single_fast (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-2,normal_ops);
			spawn_formation_mine(2,normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(0,normal_ops-1);
		}
	}
	
	void eight_wave_lopsided (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1,normal_ops);
			spawn_formation_mine(1,normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(3,normal_ops);
		}
	}
	
	void eight_wave_pair (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(1,normal_ops+1);
			spawn_formation_mine(3,normal_ops-1);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops-1);
			spawn_formation_mine(-1,normal_ops+1);
		}
	}
	
	void eight_wave_alternating (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(1,normal_ops);
			spawn_formation_mine(3,normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1,normal_ops);
		}
	}
	
	void ten_wave_freeway (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1,normal_ops);
			spawn_formation_mine(2,normal_ops-1);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1,normal_ops);
		}
	}
	
	void ten_wave_slow_and_fast (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops-1);
			spawn_formation_mine(3, normal_ops+1);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops-1);
			spawn_formation_mine(0,normal_ops);
			spawn_formation_mine(3, normal_ops+1);
		}
	}
	
	void twelve_wave_boring (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-2,normal_ops);
			spawn_formation_mine(0, normal_ops);
			spawn_formation_mine(2, normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-2,normal_ops);
			spawn_formation_mine(0, normal_ops);
			spawn_formation_mine(2, normal_ops);
		}
	}
	
	void twelve_wave_boxy (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(3, normal_ops);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1, normal_ops);
			spawn_formation_mine(1, normal_ops);
			spawn_formation_mine(3, normal_ops);
		}
	}
	
	void twelve_wave_trios (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops+1);
			spawn_formation_mine(0, normal_ops);
			spawn_formation_mine(3, normal_ops-1);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops+1);
			spawn_formation_mine(0, normal_ops);
			spawn_formation_mine(3, normal_ops-1);
		}
	}
	
	void twelve_wave_wavy (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+4)){
			spawn_formation_mine(-3,normal_ops+1);
			spawn_formation_mine(-1, normal_ops);
			spawn_formation_mine(1, normal_ops);
			spawn_formation_mine(3, normal_ops-1);
		}
		if (seconds==(start_second+2) || seconds==(start_second+6)){
			spawn_formation_mine(-1,normal_ops);
			spawn_formation_mine(1, normal_ops);
		}
	}
	
	void sixteen_wave_boring (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+2) ||seconds==(start_second+4) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1, normal_ops);
			spawn_formation_mine(1, normal_ops);
			spawn_formation_mine(3, normal_ops);
		}
	}
	
	void sixteen_wave_curtain (int start_second, int normal_ops){
		if (seconds==start_second || seconds==(start_second+2) ||seconds==(start_second+4) || seconds==(start_second+6)){
			spawn_formation_mine(-3,normal_ops);
			spawn_formation_mine(-1, normal_ops-1);
			spawn_formation_mine(1, normal_ops);
			spawn_formation_mine(3, normal_ops+1);
		}
	}
	
	void random_six_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			six_wave_single_slow(start_second, normal_ops);
		}
		else if (whichwave==2){
			six_wave_single_fast(start_second, normal_ops);
		}
	}
	
	void random_eight_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			eight_wave_pair(start_second, normal_ops);
		}
		else if (whichwave==2){
			eight_wave_alternating(start_second, normal_ops);
		}
	}
	
	void random_ten_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			ten_wave_freeway(start_second, normal_ops);
		}
		else if (whichwave==2){
			ten_wave_slow_and_fast(start_second, normal_ops);
		}
	}
	
	void random_twelve_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,4);
		}
		if (whichwave==1){
			twelve_wave_boring(start_second, normal_ops);
		}
		else if (whichwave==2){
			twelve_wave_boxy(start_second, normal_ops);
		}
		else if (whichwave==3){
			twelve_wave_trios(start_second, normal_ops);
		}
		else if (whichwave==4){
			twelve_wave_wavy(start_second, normal_ops);
		}
	}
	
	void random_dull_twelve_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			twelve_wave_boring(start_second, normal_ops);
		}
		else if (whichwave==2){
			twelve_wave_boxy(start_second, normal_ops);
		}
	}
	
	void random_weird_twelve_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			twelve_wave_trios(start_second, normal_ops);
		}
		else if (whichwave==2){
			twelve_wave_wavy(start_second, normal_ops);
		}
	}
	
	void random_sixteen_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			sixteen_wave_boring(start_second, normal_ops);
		}
		else if (whichwave==2){
			sixteen_wave_curtain(start_second, normal_ops);
		}
	}
	
	
	
	void spawn_formation_mine(int xposn, int shoot_opportunities){
		if (ordinary_minetype.equals("generic")){
			spawnMine(xposn, 180/(shoot_opportunities+1));
		}
		if (ordinary_minetype.equals("decoy")){
			spawnDecoyProbablistic(xposn, 180/(shoot_opportunities+1),decoyfreq);
		}
		if (ordinary_minetype.equals("titanium")){
			spawnTitaniumMine(xposn,180/(shoot_opportunities+1));
		}
		
	}
	
	
	
	void basic_set(int sec){
		
		wave_number_update(sec-1,1);
		
		four_wave(sec, 2);
		
		wave_number_update(sec+16-1,2);
		
		random_six_wave(sec+16, 2);
		
		wave_number_update(sec+16*2-1,3);

		random_ten_wave(sec+16*2, 2);
		
		wave_number_update(sec+16*3-1,4);
		
		random_weird_twelve_wave(sec+16*3, 2);
		
		wave_number_update(sec+16*4-1,5);
		
		random_dull_twelve_wave(sec+16*4, 2);
		
		wave_number_update(sec+16*5-1,6);
		
		sixteen_wave_boring(sec+16*5,3);
	}
	
	void basic_set_shortened(int sec){
		
		wave_number_update(sec-1,1);
		
		four_wave(sec, 2);
		
		wave_number_update(sec+16-1,2);
		
		eight_wave_alternating(sec+16, 2);
		
		wave_number_update(sec+16*2-1,3);
		
		random_twelve_wave(sec+16*2, 2);
		
		wave_number_update(sec+16*3-1,4);
		
		sixteen_wave_boring(sec+16*3,2);
	}
	
	void basic_set_shortened_slow_end(int sec){
		
		wave_number_update(sec-1,1);
		
		four_wave(sec, 2);
		
		wave_number_update(sec+16-1,2);
		
		eight_wave_alternating(sec+16, 2);
		
		wave_number_update(sec+16*2-1,3);
		
		random_twelve_wave(sec+16*2, 2);
		
		wave_number_update(sec+16*3-1,4);
		
		sixteen_wave_boring(sec+16*3,3);
	}
	
	void decoy_set(int sec){
		
		wave_number_update(sec-1,1);
		
		eight_wave_alternating(sec, 2);
		
		wave_number_update(sec+16-1,2);

		twelve_wave_boring(sec+16, 3);
		
		wave_number_update(sec+16+18-1,3);
		
		ten_wave_freeway(sec+16+18,3);
		
		wave_number_update(sec+16+18*2-1,4);
		
		eight_wave_pair(sec+16+18*2, 2);
		
		wave_number_update(sec+16+18*2+16-1,5);
		
		twelve_wave_boxy(sec+16+18*2+16,3);
	}
	
	
	
	@Override
	void level_specific_HUD(){
		//font.draw(batch, "MINES: "+minecount, 90, 472, 140, 1, true);
		//font.draw(batch, "CAPTURED: "+captured, 90, 455, 140, 1, true);
		//font.draw(batch, "DESTROYED: "+ destroyed, 90, 437, 140, 1, true);
		//font.draw(batch, "SCORE: "+score, 90, 420, 140, 1, true);
		
		if (!CAMPAIGN){
			font.draw(batch, "WAVE: "+wave_number+"/"+wave_number_total, 90, 455, 140, 1, true);
			font.draw(batch, "SCORE: "+ score, 90, 437, 140, 1, true);
		}
		if (CAMPAIGN){
			if (hardcoded_opt_packagename.equals("Combination")){
				font.draw(batch, "WAVE: "+wave_number+"/"+wave_number_total, 90, 455, 140, 1, true);
				font.draw(batch, "SHIELDS: "+ shields, 90, 437, 140, 1, true);
			}
			else{
				font.draw(batch, "WAVE: "+wave_number+"/"+wave_number_total, 90, 464, 140, 1, true);
				font.draw(batch, "SHIELDS: " + shields, 90, 446, 140, 1, true);
				font.draw(batch, "CAPTURED: " + (captured+extra_mines), 90, 428, 140, 1, true);
			}
			
		}
	}
	
	@Override
	void calculate_score(){
		if (hardcoded_opt_packagename.equals("Combination")){
			score=captured+shields*4;
		}
		else{
			score=captured+shields*4+8;
		}
		
		score=Math.max(score, 0);
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}