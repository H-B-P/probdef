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
	
	boolean one_done;
	boolean two_done;
	boolean three_done;
	boolean four_done;
	boolean five_done;
	boolean six_done;
	boolean seven_done;
	
	int one_rem;
	int two_rem;
	int three_rem;
	int four_rem;
	int five_rem;
	int six_rem;
	int seven_rem;

	
	
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
		
		if (!prefs.contains("one_remain")){
			prefs.putInteger("one_remain",0);
		}
		if (!prefs.contains("two_remain")){
			prefs.putInteger("two_remain",0);
		}
		if (!prefs.contains("three_remain")){
			prefs.putInteger("three_remain",0);
		}
		if (!prefs.contains("four_remain")){
			prefs.putInteger("four_remain",0);
		}
		if (!prefs.contains("five_remain")){
			prefs.putInteger("five_remain",0);
		}
		if (!prefs.contains("six_remain")){
			prefs.putInteger("six_remain",0);
		}
		if (!prefs.contains("seven_remain")){
			prefs.putInteger("seven_remain",0);
		}
		

		one_done=prefs.getBoolean("one_done");
		two_done=prefs.getBoolean("two_done");
		three_done=prefs.getBoolean("three_done");
		four_done=prefs.getBoolean("four_done");
		five_done=prefs.getBoolean("five_done");
		six_done=prefs.getBoolean("six_done");
		seven_done=prefs.getBoolean("seven_done");
		
		one_rem=prefs.getInteger("one_remain");
		two_rem=prefs.getInteger("two_remain");
		three_rem=prefs.getInteger("three_remain");
		four_rem=prefs.getInteger("four_remain");
		five_rem=prefs.getInteger("five_remain");
		six_rem=prefs.getInteger("six_remain");
		seven_rem=prefs.getInteger("seven_remain");
		
		
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
		four_r.x=90;
		four_r.y=170;
		four_r.height=60;
		four_r.width=140;
		
		five_r = new Rectangle();
		five_r.x=15;
		five_r.y=95;
		five_r.height=60;
		five_r.width=140;
		
		six_r = new Rectangle();
		six_r.x=165;
		six_r.y=95;
		six_r.height=60;
		six_r.width=140;
		
		seven_r = new Rectangle();
		seven_r.x=90;
		seven_r.y=20;
		seven_r.height=60;
		seven_r.width=140;
		
		
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
	    
	    if (one_done){blackfont.draw(batch, "shields: "+one_rem,one_r.x, one_r.y-2, one_r.width, 1, true);}
	    
	    
	    
	    batch.draw(button_blank_t, two_r.x, two_r.y);
	    buttony_font.draw(batch, "Shields", two_r.x+10, two_r.y+38, 120, 1, true);
	    
	    if (two_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, two_r.x, two_r.y);
	    }
	    if (!one_done){
	    	batch.draw(button_dead_t, two_r.x, two_r.y);
	    }
	    
	    if (two_done){blackfont.draw(batch, "shields: "+two_rem,two_r.x, two_r.y-2, two_r.width, 1, true);}
	    
	    
	    
	    batch.draw(button_blank_t, three_r.x, three_r.y);
	    buttony_font.draw(batch, "Titanium", three_r.x+10, three_r.y+38, 120, 1, true);
	    
	    if (three_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, three_r.x, three_r.y);
	    }
	    if (!two_done){
	    	batch.draw(button_dead_t, three_r.x, three_r.y);
	    }
	    
	    if (three_done){blackfont.draw(batch, "shields: "+three_rem, three_r.x, three_r.y-2, three_r.width, 1, true);}
	    
	    
	    
	    batch.draw(button_blank_t, four_r.x, four_r.y);
	    buttony_font.draw(batch, "Multishot", four_r.x+10, four_r.y+38, 120, 1, true);
	    
	    if (four_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, four_r.x, four_r.y);
	    }
	    if (!three_done){
	    	batch.draw(button_dead_t, four_r.x, four_r.y);
	    }
	    
	    if (four_done){blackfont.draw(batch, "shields: "+four_rem, four_r.x, four_r.y-2, four_r.width, 1, true);}
	    
	    
	    
	    batch.draw(button_blank_t, five_r.x, five_r.y);
	    buttony_font.draw(batch, "Shields,\nTitanium", five_r.x+10, five_r.y+38+12, 120, 1, true);
	    
	    if (five_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, five_r.x, five_r.y);
	    }
	    if (!four_done){
	    	batch.draw(button_dead_t, five_r.x, five_r.y);
	    }	    
	    
	    if (five_done){blackfont.draw(batch, "shields: "+five_rem, five_r.x, five_r.y-2, five_r.width, 1, true);}
	    
	    
	    
	    batch.draw(button_blank_t, six_r.x, six_r.y);
	    buttony_font.draw(batch, "Shielded\nTitanium", six_r.x+10, six_r.y+38+12, 120, 1, true);
	    
	    if (six_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, six_r.x, six_r.y);
	    }
	    if (!five_done){
	    	batch.draw(button_dead_t, six_r.x, six_r.y);
	    }
	    
	    if (six_done){blackfont.draw(batch, "shields: "+six_rem, six_r.x, six_r.y-2, six_r.width, 1, true);}
	    
	    
	    
	    
	    batch.draw(button_blank_t, seven_r.x, seven_r.y);
	    buttony_font.draw(batch, "Finale", seven_r.x+10, seven_r.y+38, 120, 1, true);
	    
	    if (seven_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, seven_r.x, seven_r.y);
	    }
	    if (!six_done){
	    	batch.draw(button_dead_t, seven_r.x, seven_r.y);
	    }	    
	    
	    if (seven_done){blackfont.draw(batch, "final shields: "+seven_rem, seven_r.x, seven_r.y-2, seven_r.width, 1, true);}
	    	    
	    
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
			
			if (one_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Basic_Intro_Comb(game,true));
				dispose();
			}
			
			else if (one_done &&two_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Multiple_Shields(game,true));
				dispose();
			}
			
			else if (two_done && three_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Titanium_Intro(game,true));
				dispose();
			}
			
			else if (three_done && four_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Multiple_Multishot(game,true));
				dispose();
			}
			
			else if (four_done && five_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Challenge_OR(game,true));
				dispose();
			}
			
			else if (five_done && six_r.contains(tp_x,tp_y)){
				game.setScreen(new ArcadeScreen_Prob_Challenge_AND(game,true));
				dispose();
			}
			
			else if (six_done && seven_r.contains(tp_x,tp_y)){
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