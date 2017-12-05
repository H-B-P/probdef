package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.utils.Array;

public class CampaignScreen_Combination extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	private Texture TITLE_t;
	
	private Texture dull_trim_t;
	
	Rectangle menu_r;
	Texture menu_t;
	
	Texture button_blank_t;
	
	Texture button_dead_t;
	
	Rectangle one_r;	
	Rectangle two_r;
	Rectangle three_r;
	Rectangle four_r;
	Rectangle five_r;
	Rectangle six_r;
	Rectangle seven_r;
	Rectangle eight_r;
	
	boolean one_done;
	boolean two_done;
	boolean three_done;
	boolean four_done;
	boolean five_done;
	boolean six_done;
	boolean seven_done;
	boolean eight_done;

	
	Texture final_score_box_t;
	
	private SpriteBatch batch;
	
	BitmapFont buttony_font;

	
	public CampaignScreen_Combination(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		if (!prefs.contains("one_done")){
			prefs.putBoolean("one_done",false);
		}
		if (!prefs.contains("two_done")){
			prefs.putBoolean("two_done",false);
		}
		if (!prefs.contains("three_done")){
			prefs.putBoolean("three_done",false);
		}
		if (!prefs.contains("four_done")){
			prefs.putBoolean("four_done",false);
		}
		if (!prefs.contains("five_done")){
			prefs.putBoolean("five_done",false);
		}
		if (!prefs.contains("six_done")){
			prefs.putBoolean("six_done",false);
		}
		if (!prefs.contains("seven_done")){
			prefs.putBoolean("seven_done",false);
		}
		if (!prefs.contains("eight_done")){
			prefs.putBoolean("eight_done",false);
		}
		

		one_done=prefs.getBoolean("one_done");
		two_done=prefs.getBoolean("two_done");
		three_done=prefs.getBoolean("three_done");
		four_done=prefs.getBoolean("four_done");
		five_done=prefs.getBoolean("five_done");
		six_done=prefs.getBoolean("six_done");
		seven_done=prefs.getBoolean("seven_done");
		eight_done=prefs.getBoolean("eight_done");
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(option_music_volume);
		bgm.play();
				
		TITLE_t=new Texture(Gdx.files.internal("TITLE_CAMPAIGN.png"));
		
		menu_r = new Rectangle();
		menu_r.x=170;
		menu_r.y=400;
		menu_r.height=60;
		menu_r.width=140;
		menu_t = new Texture(Gdx.files.internal("abutton_menu.png"));
		
		button_blank_t=new Texture(Gdx.files.internal("abutton_blank.png"));
		
		button_dead_t=new Texture(Gdx.files.internal("abutton_black.png"));
		
		dull_trim_t=new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
		buttony_font = new BitmapFont(Gdx.files.internal("regular_font/gravity_20.fnt"));
		buttony_font.setColor(Color.BLACK);
		
		
		one_r = new Rectangle();
		one_r.x=90;
		one_r.y=320;
		one_r.height=60;
		one_r.width=140;
		
		two_r = new Rectangle();
		two_r.x=15;
		two_r.y=245;
		two_r.height=60;
		two_r.width=140;
		
		three_r = new Rectangle();
		three_r.x=165;
		three_r.y=245;
		three_r.height=60;
		three_r.width=140;
		
		four_r = new Rectangle();
		four_r.x=15;
		four_r.y=170;
		four_r.height=60;
		four_r.width=140;
		
		five_r = new Rectangle();
		five_r.x=165;
		five_r.y=170;
		five_r.height=60;
		five_r.width=140;
		
		six_r = new Rectangle();
		six_r.x=15;
		six_r.y=95;
		six_r.height=60;
		six_r.width=140;
		
		seven_r = new Rectangle();
		seven_r.x=165;
		seven_r.y=95;
		seven_r.height=60;
		seven_r.width=140;
		
		eight_r = new Rectangle();
		eight_r.x=90;
		eight_r.y=20;
		eight_r.height=60;
		eight_r.width=140;
		
		
		game = gam;
		
		batch= new SpriteBatch();
		
		
	}
	
	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
	    batch.draw(TITLE_t, 0,400);
	    
		
	    
	    batch.draw(menu_t, menu_r.x, menu_r.y);
	    
	    if (menu_r.contains(tp_x,tp_y)){
			batch.draw(dull_trim_t, menu_r.x, menu_r.y);
		}
		
	    
	    batch.draw(button_blank_t, one_r.x, one_r.y);
	    buttony_font.draw(batch, "Intro", one_r.x+10, one_r.y+38, 120, 1, true);
	    
	    if (one_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, one_r.x, one_r.y);
	    }	    
	    
	    batch.draw(button_blank_t, two_r.x, two_r.y);
	    buttony_font.draw(batch, "Titanium", two_r.x+10, two_r.y+38, 120, 1, true);
	    
	    if (two_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, two_r.x, two_r.y);
	    }
	    if (!one_done){
	    	batch.draw(button_dead_t, two_r.x, two_r.y);
	    }
	    	    
	    
	    batch.draw(button_blank_t, three_r.x, three_r.y);
	    buttony_font.draw(batch, "Tradeoff", three_r.x+10, three_r.y+38, 120, 1, true);
	    
	    if (three_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, three_r.x, three_r.y);
	    }
	    if (!two_done){
	    	batch.draw(button_dead_t, three_r.x, three_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, four_r.x, four_r.y);
	    buttony_font.draw(batch, "Shields", four_r.x+10, four_r.y+38, 120, 1, true);
	    
	    if (four_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, four_r.x, four_r.y);
	    }
	    if (!three_done){
	    	batch.draw(button_dead_t, four_r.x, four_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, five_r.x, five_r.y);
	    buttony_font.draw(batch, "Multishot", five_r.x+10, five_r.y+38, 120, 1, true);
	    
	    if (five_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, five_r.x, five_r.y);
	    }
	    if (!four_done){
	    	batch.draw(button_dead_t, five_r.x, five_r.y);
	    }	    
	    
	    batch.draw(button_blank_t, six_r.x, six_r.y);
	    buttony_font.draw(batch, "Shields,\nTitanium", six_r.x+10, six_r.y+38+12, 120, 1, true);
	    
	    if (six_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, six_r.x, six_r.y);
	    }
	    if (!five_done){
	    	batch.draw(button_dead_t, six_r.x, six_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, seven_r.x, seven_r.y);
	    buttony_font.draw(batch, "Shielded\nTitanium", seven_r.x+10, seven_r.y+38+12, 120, 1, true);
	    
	    if (seven_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, seven_r.x, seven_r.y);
	    }
	    if (!six_done){
	    	batch.draw(button_dead_t, seven_r.x, seven_r.y);
	    }	    
	    
	    batch.draw(button_blank_t, eight_r.x, eight_r.y);
	    buttony_font.draw(batch, "Finale", eight_r.x+10, eight_r.y+38, 120, 1, true);
	    
	    if (eight_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, eight_r.x, eight_r.y);
	    }
	    if (!seven_done){
	    	batch.draw(button_dead_t, eight_r.x, eight_r.y);
	    }	    
	    
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
			
			if (one_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Basic_Intro_Comb(game,true));
				dispose();
			}
			
			else if (one_done &&two_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Titanium_Simplified(game,true));
				dispose();
			}
			
			else if (two_done && three_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Titanium_Intro(game,true));
				dispose();
			}
			
			else if (three_done && four_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Multiple_Shields(game,true));
				dispose();
			}
			
			else if (four_done && five_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Multiple_Multishot(game,true));
				dispose();
			}
			
			else if (five_done && six_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Challenge_OR(game,true));
				dispose();
			}
			
			else if (six_done && seven_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Challenge_AND(game,true));
				dispose();
			}
			
			else if (seven_done && eight_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Challenge_Finale(game,true));
				dispose();
			}
			
			else if (menu_r.contains(tp_x,tp_y)){
				game.setScreen(new TitleScreen(game, true));
	            dispose();
			}
		}
		
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		bgm.stop();
		bgm.dispose();
		batch.dispose();
	}
}