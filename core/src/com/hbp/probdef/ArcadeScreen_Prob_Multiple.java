package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Prob_Multiple extends ArcadeScreen_Prob {
	
	final ProbDef game;
	
	public ArcadeScreen_Prob_Multiple(final ProbDef gam, boolean camp) {
		
		super(gam, camp);
		
		game = gam;
		
	}
	
	void shieldy_four_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-2,180/(normal_ops+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(2,180/(normal_ops+1),shields);
		}
	}
	
	void shieldy_six_wave(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-2,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops-1+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(0,180/(normal_ops+1),shields);
		}
	}
	
	void shieldy_eight_wave_even(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(1,180/(normal_ops+1),shields);
			spawnShieldMine(3,180/(normal_ops+1),shields);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1),shields);
			spawnShieldMine(-1,180/(normal_ops+1),shields);
		}
	}
	
	void shieldy_eight_wave_askew(int start_second, int normal_ops, int shields){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(1,180/(normal_ops+1),shields+1);
			spawnShieldMine(3,180/(normal_ops+1),shields-1);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1),shields+1);
			spawnShieldMine(-1,180/(normal_ops+1),shields-1);
		}
	}
	
	void shieldy_ten_wave_askew(int start_second, int normal_ops, int shields, int shields_special){
		if (seconds==start_second || (seconds==start_second+4)){
			spawnShieldMine(-3,180/(normal_ops+1),shields);
			spawnShieldMine(-1,180/(normal_ops+1),shields);
			spawnShieldMine(2,180/(normal_ops+1),shields_special);
		}
		if (seconds==(start_second+2) || (seconds==start_second+6)){
			spawnShieldMine(-3,180/(normal_ops+1),shields);
			spawnShieldMine(-1,180/(normal_ops+1),shields);
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
	
	void shieldy_intro_set(int sec, boolean slowed){
		wave_number_update(sec-1,1);
		
		if (slowed){
			shieldy_four_wave(sec, 3,1);
		}
		else{
			shieldy_four_wave(sec, 2,1);
		}
		wave_number_update(sec+22-1,2);
		
		if (slowed){
			shieldy_six_wave(sec+22, 5,2);
		}
		else{
			shieldy_six_wave(sec+22, 4,2);
		}
		
		
		
		wave_number_update(sec+22*2-1,3);
		
		if (slowed){
			shieldy_eight_wave_even(sec+22*2, 6, 2);
		}
		else{
			shieldy_eight_wave_even(sec+22*2, 4, 2);
		}
		
		wave_number_update(sec+22*3-1, 4);
		
		if (slowed){
			shieldy_four_wave(sec+22*3, 4, 3);
		}
		else{
			shieldy_four_wave(sec+22*3, 3, 3);

		}
		
		
		wave_number_update(sec+22*4-1, 5);
		
		if (slowed){
			shieldy_eight_wave_askew(sec+22*4, 6, 2);
		}
		else{
			shieldy_eight_wave_askew(sec+22*4, 4, 2);
		}
		
	}
	
	void square_multishot_set(int sec){
		
		wave_number_update(sec-1,1);
		
		shieldy_eight_wave_even(sec, 3, 3);
		
		wave_number_update(sec+20-1,2);
		
		shieldy_twelve_wave_even(sec+20, 2, 1);
		
		wave_number_update(sec+20*2-1,3);
		
		
		shieldy_eight_wave_askew(sec+20*2, 3, 3);
		
		wave_number_update(sec+20*3-1,4);

		
		shieldy_twelve_wave_askew(sec+20*3, 3, 2);
	}
	
	void shieldy_maincourse_set(int sec){
		wave_number_update(sec-1,1);
		
		shieldy_eight_wave_even(sec, 3, 2);
		
		wave_number_update(sec+20-1,2);

		shieldy_eight_wave_askew(sec+20, 3, 2);
		
		wave_number_update(sec+20*2-1,3);

		
		shieldy_ten_wave_askew(sec+20*2, 3, 1, 3);
		
		wave_number_update(sec+20*3-1,4);

		
		shieldy_twelve_wave_even(sec+20*3, 3, 1);
		
		wave_number_update(sec+20*4-1,5);

		
		shieldy_twelve_wave_askew(sec+20*4, 3, 1);

		
	}
	
	@Override
	public void dispose() {
		probgame_dispose();
	}
}