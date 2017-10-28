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

public class CampaignScreen extends MetaScreen {
	
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
	Rectangle nine_r;
	
	Texture final_score_box_t;
	
	private SpriteBatch batch;
	
	BitmapFont buttony_font;

	
	public CampaignScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
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
		one_r.x=15;
		one_r.y=320;
		one_r.height=60;
		one_r.width=140;
		
		two_r = new Rectangle();
		two_r.x=165;
		two_r.y=320;
		two_r.height=60;
		two_r.width=140;
		
		three_r = new Rectangle();
		three_r.x=15;
		three_r.y=245;
		three_r.height=60;
		three_r.width=140;
		
		four_r = new Rectangle();
		four_r.x=165;
		four_r.y=245;
		four_r.height=60;
		four_r.width=140;
		
		five_r = new Rectangle();
		five_r.x=15;
		five_r.y=170;
		five_r.height=60;
		five_r.width=140;
		
		six_r = new Rectangle();
		six_r.x=165;
		six_r.y=170;
		six_r.height=60;
		six_r.width=140;
		
		seven_r = new Rectangle();
		seven_r.x=15;
		seven_r.y=95;
		seven_r.height=60;
		seven_r.width=140;
		
		eight_r = new Rectangle();
		eight_r.x=165;
		eight_r.y=95;
		eight_r.height=60;
		eight_r.width=140;
		
		nine_r = new Rectangle();
		nine_r.x=90;
		nine_r.y=20;
		nine_r.height=60;
		nine_r.width=140;
		
		
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
	    blackfont.draw(batch, "mines: 0",one_r.x, one_r.y-2, one_r.width, 1, true);
	    
	    batch.draw(button_blank_t, two_r.x, two_r.y);
	    buttony_font.draw(batch, "Circles", two_r.x+10, two_r.y+38, 120, 1, true);
	    if (two_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, two_r.x, two_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, two_r.x, two_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, three_r.x, three_r.y);
	    buttony_font.draw(batch, "Reversed", three_r.x+10, three_r.y+38, 120, 1, true);
	    if (three_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, three_r.x, three_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, three_r.x, three_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, four_r.x, four_r.y);
	    buttony_font.draw(batch, "Intro\n(Decoys)", four_r.x+10, four_r.y+38+12, 120, 1, true);
	    
	    if (four_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, four_r.x, four_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, four_r.x, four_r.y);
	    }
	    
	    batch.draw(button_blank_t, five_r.x, five_r.y);
	    buttony_font.draw(batch, "Minority\nDecoy", five_r.x+10, five_r.y+38+12, 120, 1, true);
	    
	    if (five_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, five_r.x, five_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, five_r.x, five_r.y);
	    }
	    
	    
	    batch.draw(button_blank_t, six_r.x, six_r.y);
	    buttony_font.draw(batch, "Majority\nDecoy", six_r.x+10, six_r.y+38+12, 120, 1, true);
	    
	    if (six_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, six_r.x, six_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, six_r.x, six_r.y);
	    }
	    
	    batch.draw(button_blank_t, seven_r.x, seven_r.y);
	    buttony_font.draw(batch, "Intro\n(Deduction)", seven_r.x+10, seven_r.y+38+12, 120, 1, true);
	    
	    if (seven_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, seven_r.x, seven_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, seven_r.x, seven_r.y);
	    }
	    
	    batch.draw(button_blank_t, eight_r.x, eight_r.y);
	    buttony_font.draw(batch, "Blatant\nEvidence", eight_r.x+10, eight_r.y+38+12, 120, 1, true);
	    
	    if (eight_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, eight_r.x, eight_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, eight_r.x, eight_r.y);
	    }
	    
	    batch.draw(button_blank_t, nine_r.x, nine_r.y);
	    buttony_font.draw(batch, "Finale", nine_r.x+10, nine_r.y+38, 120, 1, true);
	    
	    if (nine_r.contains(tp_x,tp_y)){
	    	batch.draw(dull_trim_t, nine_r.x, nine_r.y);
	    }
	    if (true){
	    	batch.draw(button_dead_t, nine_r.x, nine_r.y);
	    }
	    
	    
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {			
			if (menu_r.contains(tp_x,tp_y)){
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