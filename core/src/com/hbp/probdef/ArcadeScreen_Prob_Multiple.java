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
	
	void shieldy_intro_set(int sec){
		wave_number_update(sec-1,1);
		
		shieldy_four_wave(sec, 2,1);
		
		wave_number_update(sec+20-1,2);

		shieldy_six_wave(sec+20, 4,2);
		
		wave_number_update(sec+20*2-1,3);
		
		shieldy_eight_wave_even(sec+20*2, 5, 2);
		
		wave_number_update(sec+20*3-1, 4);
		
		shieldy_four_wave(sec+20*3, 3, 3);
		
		wave_number_update(sec+20*4-1, 5);
		
		shieldy_eight_wave_askew(sec+20*4, 4, 2);
	}
	
	
	@Override
	void level_specific_HUD(){
		
		font.draw(batch, "WAVE: "+wave_number+"/"+wave_number_total, 90, 455, 140, 1, true);
		font.draw(batch, "SCORE: "+ score, 90, 437, 140, 1, true);
	   }

	@Override
	public void dispose() {
		probgame_dispose();
	}
}