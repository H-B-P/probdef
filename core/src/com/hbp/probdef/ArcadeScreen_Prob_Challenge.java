package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob_Challenge extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	public ArcadeScreen_Prob_Challenge(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	}
	
	void OR_eight_wave_alternating(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(1,180/(normal_ops+1),shields);
			spawnTitaniumMine(3,180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1),shields);
			spawnTitaniumMine(-1,180/(normal_ops+1));
		}
	}
	
	void OR_eight_wave_paired(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(1,180/(normal_ops+1+1),shields);
			spawnTitaniumMine(3,180/(normal_ops-1+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops-1+1),shields);
			spawnTitaniumMine(-1,180/(normal_ops+1+1));
		}
	}
	
	void OR_ten_wave_shield_majority(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-3,180/(normal_ops+1),shields-1);
			spawnShieldMine(-1,180/(normal_ops+1),shields);
			spawnTitaniumMine(2,180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1),shields);
			spawnShieldMine(-1,180/(normal_ops+1),shields-1);
			
		}
	}
	
	void OR_ten_wave_titanium_majority(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnTitaniumMine(-3,180/(normal_ops+1));
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnShieldMine(2,180/(normal_ops+1), shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnTitaniumMine(-3,180/(normal_ops+1));
			spawnTitaniumMine(-1,180/(normal_ops+1));
			
		}
	}
	
	void OR_ten_wave_hybrid(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-3,180/(normal_ops+1), shields-1);
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnMine(2,180/(normal_ops-1+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnTitaniumMine(-3,180/(normal_ops+1));
			spawnShieldMine(-1,180/(normal_ops+1), shields);
		}
	}
	
	void OR_twelve_wave_boring(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnTitaniumMine(-2,180/(normal_ops+1));
			spawnShieldMine(0,180/(normal_ops+1), shields);
			spawnTitaniumMine(2,180/(normal_ops+1));
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-2,180/(normal_ops+1), shields);
			spawnTitaniumMine(0,180/(normal_ops+1));
			spawnShieldMine(2,180/(normal_ops+1), shields);
		}
	}
	
	void OR_twelve_wave_boxy(int start_second, int normal_ops, int shields_fang, int shields_corner){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-3,180/(normal_ops+1), shields_fang);
			spawnShieldMine(3,180/(normal_ops+1), shields_fang);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1), shields_corner);
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnTitaniumMine(1,180/(normal_ops+1));
			spawnShieldMine(3,180/(normal_ops+1), shields_corner);
		}
	}
	
	void challenge_twelve_wave_askew(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-2,180/(normal_ops+1),shields-1);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields+1);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-2,180/(normal_ops+1),shields-1);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields+1);
		}
	}
	
	//eight-wave of altern, eight-wave of fasts and slows, ten-wave of majority x, ten-wave of majority y, ten-wave with pairs and fast normals, twelve-wave normie, twelve-wave boxy?
	
	void challenge_OR_set(int sec, boolean easy){
		wave_number_update(sec-1,1);
		
		if (!easy){
			OR_eight_wave_alternating(sec, 2, 3);
		}
		else{
			OR_eight_wave_alternating(sec, 3, 3);
		}
		
		
		wave_number_update(sec+20-1,2);
		
		if (!easy){
			OR_eight_wave_paired(sec+20, 2, 3);
		}
		else{
			OR_eight_wave_paired(sec+20, 3, 3);
		}
		
		wave_number_update(sec+20*2-1,3);
		
		if (!easy){
			OR_ten_wave_shield_majority(sec+20*2, 2,2);
		}
		else{
			OR_ten_wave_shield_majority(sec+20*2, 3,2);
		}

		
		wave_number_update(sec+20*3-1,4);
		
		if (!easy){
			OR_ten_wave_titanium_majority(sec+20*3, 2,2);
		}
		else{
			OR_ten_wave_titanium_majority(sec+20*3, 3,2);
		}
		
		wave_number_update(sec+20*4-1,5);
		
		if (!easy){
			OR_ten_wave_hybrid(sec+20*4, 2,2);
		}
		else{
			OR_ten_wave_hybrid(sec+20*4, 3,2);
		}
		
		wave_number_update(sec+20*5-1,6);
		
		if (!easy){
			OR_twelve_wave_boring(sec+20*5, 2, 1);
		}
		else{
			OR_twelve_wave_boring(sec+20*5, 3, 1);
		}
		
		wave_number_update(sec+20*6-1,7);
		
		if (!easy){
			OR_twelve_wave_boxy(sec+20*6, 3, 3, 2);
		}
		else{
			OR_twelve_wave_boxy(sec+20*6, 3, 2, 1);
		}
		
		
	}
	
	
	//---AND---
	
	void AND_four_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(-2,180/(normal_ops+1), shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(2,180/(normal_ops+1), shields);
		}
	}
	
	void AND_six_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(-2,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(2,180/(normal_ops-1+1), shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(0,180/(normal_ops+1), shields);
		}
	}
	
	void AND_eight_wave_alternating(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(1,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(3,180/(normal_ops+1), shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(-3,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(-1,180/(normal_ops+1), shields);
		}
	}
	
	void AND_eight_wave_paired(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(1,180/(normal_ops+1+1), shields);
			spawnShieldedTitaniumMine(3,180/(normal_ops-1+1), shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(-3,180/(normal_ops-1+1), shields);
			spawnShieldedTitaniumMine(-1,180/(normal_ops+1+1), shields);
		}
	}
	
	void AND_eight_wave_askew(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(1,180/(normal_ops+1), shields+1);
			spawnShieldedTitaniumMine(3,180/(normal_ops+1), shields-1);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(-3,180/(normal_ops+1), shields+1);
			spawnShieldedTitaniumMine(-1,180/(normal_ops+1), shields-1);
		}
	}
	
	void AND_twelve_wave_askew(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldedTitaniumMine(-2,180/(normal_ops+1), shields+1);
			spawnShieldedTitaniumMine(0,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(2,180/(normal_ops+1), shields-1);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldedTitaniumMine(-2,180/(normal_ops+1), shields+1);
			spawnShieldedTitaniumMine(0,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(2,180/(normal_ops+1), shields-1);
		}
	}
	
	//total thirty: four-easy, six, four-hard, eight-askew, eight-slip
	
	void challenge_AND_set(int sec){
		wave_number_update(sec-1,1);
		
		AND_four_wave(sec, 1, 1);
		
		wave_number_update(sec+20-1,2);
		
		AND_six_wave(sec+20, 3, 2);
		
		wave_number_update(sec+20*2-1,3);
		
		AND_four_wave(sec+20*2, 2, 4);
		
		wave_number_update(sec+20*3-1,4);
		
		AND_eight_wave_askew(sec+20*3, 4, 2);
		
		wave_number_update(sec+20*4-1,5);
		
		AND_eight_wave_paired(sec+20*4, 3, 2);
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
	
	void shieldy_twelve_wave_even(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-2,180/(normal_ops+1),shields);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-2,180/(normal_ops+1),shields);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields);
		}
	}
	
	void shieldy_twelve_wave_askew(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-2,180/(normal_ops+1),shields-1);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields+1);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-2,180/(normal_ops+1),shields-1);
			spawnShieldMine(0,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields+1);
		}
	}
	
	void finale_twelve_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(-3,180/(normal_ops+1));
			spawnShieldedTitaniumMine(3,180/(normal_ops+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3,180/(normal_ops+1));
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnTitaniumMine(1,180/(normal_ops+1));
			spawnTitaniumMine(3,180/(normal_ops+1));
		}
	}
	
	void finale_sixteen_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnMine(-3,180/(normal_ops+1));
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnShieldMine(1,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(3,180/(normal_ops+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnMine(-3,180/(normal_ops+1));
			spawnTitaniumMine(-1,180/(normal_ops+1));
			spawnShieldMine(1,180/(normal_ops+1), shields);
			spawnShieldedTitaniumMine(3,180/(normal_ops+1),shields);
		}
	}
	
	//16+12+12+12+12+8=72
	
	//add a 16 and 12 to fini!
	
	void finale_set(int sec, int kspd){
		wave_number_update(sec-1,1);
		
		sixteen_wave_boring(sec,kspd);
		
		wave_number_update(sec+20-1,2);
		
		twelve_four_wave_early(sec+20,kspd);
		
		wave_number_update(sec+20*2-1,3);
		
		twelve_four_wave_late(sec+20*2,kspd);
		
		wave_number_update(sec+20*3-1,4);
		
		shieldy_twelve_wave_askew(sec+20*3, kspd+1, 2);

		wave_number_update(sec+20*4-1,5);
		
		OR_twelve_wave_boring(20*4, kspd,1);
		
		wave_number_update(sec+20*5-1,6);

		AND_eight_wave_paired(sec+20*5, kspd,2);
		
		wave_number_update(sec+20*6-1,7);
		
		finale_twelve_wave(sec+20*6, kspd,2);
		
		wave_number_update(sec+20*7-1,8);
		
		finale_sixteen_wave(sec+20*7, 2,1);
	}
	

	@Override
	public void dispose() {
		probgame_dispose();
	}
}