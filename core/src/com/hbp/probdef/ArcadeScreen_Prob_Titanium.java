package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob_Titanium extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	public ArcadeScreen_Prob_Titanium(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	}
	
	void eight_two_wave_centre(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(-1, 180/(normal_ops+1));
			spawnMine(1, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(3, 180/(normal_ops+1));
		}
	}
	
	void eight_two_wave_fringe(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
			spawnMine(1, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnTitaniumMine(3, 180/(normal_ops+1));
		}
	}
	
	void eight_four_wave_matching(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnTitaniumMine(1, 180/(normal_ops+1));
			spawnTitaniumMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
		}
	}
	
	void eight_four_wave_heterogenous(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(1, 180/(normal_ops+1));
			spawnTitaniumMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(-1, 180/(normal_ops+1));
		}
	}
	
	void ten_two_wave_freeway(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
			spawnTitaniumMine(2, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
		}
	}
	
	void ten_two_wave_uneven(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(0, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1-1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1-1));
		}
	}
	
	void ten_four_wave_paired(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnMine(-1, 180/(normal_ops+1));
			spawnTitaniumMine(1, 180/(normal_ops+1));
			spawnTitaniumMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
			
		}
	}
	
	void ten_four_wave_column(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnTitaniumMine(-1, 180/(normal_ops+1));
			spawnMine(1, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(-1, 180/(normal_ops+1));
			
		}
	}
	
	void twelve_two_wave_early(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnMine(-2, 180/(normal_ops+1));
			spawnTitaniumMine(0, 180/(normal_ops+1));
			spawnMine(2, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-2, 180/(normal_ops+1));
			spawnMine(0, 180/(normal_ops+1));
			spawnMine(2, 180/(normal_ops+1));
			
		}
	}
	
	void twelve_two_wave_late(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnMine(-2, 180/(normal_ops+1));
			spawnMine(0, 180/(normal_ops+1));
			spawnMine(2, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-2, 180/(normal_ops+1));
			spawnTitaniumMine(0, 180/(normal_ops+1));
			spawnMine(2, 180/(normal_ops+1));
			
		}
	}
	
	void twelve_four_wave_early(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnTitaniumMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(-1, 180/(normal_ops+1));
			spawnMine(1, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1));
			
		}
	}

	void twelve_four_wave_late(int start_second, int normal_ops){
		if (seconds==start_second || (seconds==start_second+4)){
			
			spawnMine(-3, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3, 180/(normal_ops+1));
			spawnTitaniumMine(-1, 180/(normal_ops+1));
			spawnTitaniumMine(1, 180/(normal_ops+1));
			spawnMine(3, 180/(normal_ops+1));
			
		}
	}
	
	void random_eight_two_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			eight_two_wave_centre(start_second, normal_ops);
		}
		else if (whichwave==2){
			eight_two_wave_fringe(start_second, normal_ops);
		}
	}
	
	void random_eight_four_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			eight_four_wave_matching(start_second, normal_ops);
		}
		else if (whichwave==2){
			eight_four_wave_heterogenous(start_second, normal_ops);
		}
	}
	
	void random_ten_two_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			ten_two_wave_freeway(start_second, normal_ops);
		}
		else if (whichwave==2){
			ten_two_wave_uneven(start_second, normal_ops);
		}
	}
	
	void random_ten_four_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			ten_four_wave_paired(start_second, normal_ops);
		}
		else if (whichwave==2){
			ten_four_wave_column(start_second, normal_ops);
		}
	}
	
	void random_twelve_two_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			twelve_two_wave_early(start_second, normal_ops);
		}
		else if (whichwave==2){
			twelve_two_wave_late(start_second, normal_ops);
		}
	}
	
	void random_twelve_four_wave(int start_second, int normal_ops){
		if (seconds==start_second){
			whichwave=MathUtils.random(1,2);
		}
		if (whichwave==1){
			twelve_four_wave_early(start_second, normal_ops);
		}
		else if (whichwave==2){
			twelve_four_wave_late(start_second, normal_ops);
		}
	}
	
	
	void titanium_set(int sec){
		
		wave_number_update(sec-1,1);
		
		four_wave(sec, 3);
		
		wave_number_update(sec+16-1,2);

		six_wave_single_fast(sec+16, 4);
		
		wave_number_update(sec+16+18-1,3);
		
		six_wave_single_slow(sec+16+18, 4);
		
		wave_number_update(sec+16+18*2-1,4);
		
		eight_wave_lopsided(sec+16+18*2, 4);
		
		wave_number_update(sec+16+18*3-1,5);
		
		eight_wave_pair(sec+16+18*3, 4);
		
		wave_number_update(sec+16+18*4-1,6);
		
		eight_wave_alternating(sec+16+18*4, 4);
	}
	
	void mixed_set(int sec){
		
		wave_number_update(sec-1,1);
		
		random_eight_two_wave(sec, 3);
		
		wave_number_update(sec+16-1,2);

		random_eight_four_wave(sec+16, 3);
		
		wave_number_update(sec+16*2-1,3);
		
		random_ten_two_wave(sec+16*2, 3);
		
		wave_number_update(sec+16*3-1,4);
		
		random_ten_four_wave(sec+16*3, 3);
		
		wave_number_update(sec+18*4-1,5);
		
		random_twelve_two_wave(sec+16*4, 3);
		
		wave_number_update(sec+16*5-1,6);
		
		random_twelve_four_wave(sec+16*5, 3);
	}
	
	@Override
	void level_specific_HUD(){
		
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