package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
public class ArcadeScreen_Bayes_Deduction_Intro extends GameScreen_Bayes {
	
	final ProbDef game;
	
	public ArcadeScreen_Bayes_Deduction_Intro(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
	}
	
	@Override
	
	void level_specific_bayesian_update(String dot_type, EnemyShip enemyship){
		if (dot_type.equals("destroy")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.4f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.8f;
		}
		if (dot_type.equals("capture")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.1f;
		}
		if (dot_type.equals("fail")){
			enemyship.assignedprob_one=enemyship.assignedprob_one*0.3f;
			enemyship.assignedprob_two=enemyship.assignedprob_two*0.1f;
		}
		normalize(enemyship);
	}
	
}