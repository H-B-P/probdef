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
	
	public ArcadeScreen_Prob(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		captured=0;
	    destroyed=0;
	    shields=5;
	    score=0;
	    
	    ordinary_minetype="generic";
	    decoyfreq=0;
	    
	    whichwave=0;
	    
	    wave_number=0;
	    wave_number_total=6;
	    
	    exit_to="arcade";
	}
	
	void wave_number_update(int target_seconds, int target_wave_number){
		if (seconds>=target_seconds && wave_number<target_wave_number){
			wave_number=target_wave_number;
		}
	}
	
	void four_wave (int start_second, int normal_ops){
		if (seconds==start_second){
			spawn_formation_mine(-3,normal_ops);
		}
		if (seconds==(start_second+2)){
			spawn_formation_mine(-1,normal_ops);
		}
		if (seconds==(start_second+4)){
			spawn_formation_mine(1,normal_ops);
		}
		if (seconds==(start_second+6)){
			spawn_formation_mine(3,normal_ops);
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
		
		wave_number_update(sec+14-1,2);
		
		random_six_wave(sec+14, 2);
		
		wave_number_update(sec+14*2-1,3);

		random_ten_wave(sec+14*2, 2);
		
		wave_number_update(sec+14*3-1,4);
		
		random_weird_twelve_wave(sec+14*3, 2);
		
		wave_number_update(sec+14*4-1,5);
		
		random_dull_twelve_wave(sec+14*4, 2);
		
		wave_number_update(sec+14*5-1,6);
		
		sixteen_wave_boring(sec+14*5,3);
	}
	
	void decoy_set(int sec){
		
		wave_number_update(sec-1,1);
		
		eight_wave_alternating(sec, 2);
		
		wave_number_update(sec+14-1,2);

		twelve_wave_boring(sec+14, 3);
		
		wave_number_update(sec+14+16-1,3);
		
		eight_wave_pair(sec+14+16, 2);
		
		wave_number_update(sec+14+16+14-1,4);
		
		twelve_wave_boxy(sec+14+16+14,3);
	}
	
	void titanium_set(int sec){
		
		wave_number_update(sec-1,1);
		
		four_wave(sec, 3);
		
		wave_number_update(sec+16-1,2);

		six_wave_single_fast(sec+16, 4);
		
		wave_number_update(sec+16+18-1,3);
		
		six_wave_single_slow(sec+16+18, 4);
		
		wave_number_update(sec+16+18*2-1,4);
		
		eight_wave_alternating(sec+16+18*2, 5);
		
		wave_number_update(sec+16+18*3-1,5);
		
		eight_wave_pair(sec+16+18*3, 4);
	}
	
	@Override
	void level_specific_HUD(){
		//font.draw(batch, "MINES: "+minecount, 90, 472, 140, 1, true);
		//font.draw(batch, "CAPTURED: "+captured, 90, 455, 140, 1, true);
		//font.draw(batch, "DESTROYED: "+ destroyed, 90, 437, 140, 1, true);
		//font.draw(batch, "SCORE: "+score, 90, 420, 140, 1, true);
		
		font.draw(batch, "WAVE: "+wave_number+"/"+wave_number_total, 90, 455, 140, 1, true);
		font.draw(batch, "SCORE: "+ score, 90, 437, 140, 1, true);
	   }
	
	@Override
	void calculate_score(){
		score=captured+shields*4;
		score=Math.max(score, 0);
	}

	@Override
	public void dispose() {
		probgame_dispose();
	}
}