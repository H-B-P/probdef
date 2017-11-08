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

public class OptionsScreen extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	private SelectorBox_Percentage musicVolumeSelectorBox;
	private SelectorBox_Percentage sfxVolumeSelectorBox;
	private SelectorBox acalcSelectorBox;
	private SelectorBox screenSizeSelectorBox;
	private SelectorBox tthSelectorBox;
	private SelectorBox_Percentage gamespeedSelectorBox;
	private SelectorBox flickeringSelectorBox;
	private SelectorBox backgroundSelectorBox;
	
	private Array<SelectorBox> selectorboxes;
	
	private Texture reset_t;
	private Texture reset_trim_t;
	
	private Texture TITLE_t;
	
	private Texture EBOX_t;
	
	private Texture Sbox_t;
	private Texture Sbox_forward_t;
	private Texture Sbox_backward_t;
	private Texture Sbox_trim_t;
	
	private Texture dull_trim_t;
	
	Rectangle menu_r;
	Texture menu_t;
	
	private BitmapFont font;
	
	private SpriteBatch batch;
	
	public OptionsScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(option_music_volume);
		bgm.play();
		
	    selectorboxes=new Array<SelectorBox>();
	    
	    //screenSizeSelectorBox=new SelectorBox_Size("Screen Size",170,330);
		//selectorboxes.add(screenSizeSelectorBox);
		
	    acalcSelectorBox=new SelectorBox_Acalc("Autocalc",10, 330);
		selectorboxes.add(acalcSelectorBox);
	    
		tthSelectorBox=new SelectorBox_TTH_Display("TTH Display",170, 330);
		selectorboxes.add(tthSelectorBox);
		
	    musicVolumeSelectorBox=new SelectorBox_Percentage("Music Volume",10,255);
		selectorboxes.add(musicVolumeSelectorBox);
		
		sfxVolumeSelectorBox=new SelectorBox_Percentage("SFX Volume",170,255);
		selectorboxes.add(sfxVolumeSelectorBox);
		
		gamespeedSelectorBox=new SelectorBox_Percentage("Game Speed",10,180);
		gamespeedSelectorBox.max=500;
		gamespeedSelectorBox.min=50;
		gamespeedSelectorBox.interval=50;
		selectorboxes.add(gamespeedSelectorBox);
		
		flickeringSelectorBox=new SelectorBox_Flickering("Flickering", 90, 105);
	    selectorboxes.add(flickeringSelectorBox);
		
	    backgroundSelectorBox=new SelectorBox_Background("Background",170, 180 );
	    selectorboxes.add(backgroundSelectorBox);
	    
		EBOX_t= new Texture(Gdx.files.internal("explainybox.png"));
		
		TITLE_t=new Texture(Gdx.files.internal("TITLE_OPTIONS.png"));
		
		
		Sbox_t=new Texture(Gdx.files.internal("selector_base.png"));
		Sbox_forward_t=new Texture(Gdx.files.internal("sbut_forward.png"));
		Sbox_backward_t=new Texture(Gdx.files.internal("sbut_backward.png"));
		Sbox_trim_t=new Texture(Gdx.files.internal("sbut_trim.png"));

		
		menu_r = new Rectangle();
		menu_r.x=170;
		menu_r.y=400;
		menu_r.height=60;
		menu_r.width=140;
		menu_t = new Texture(Gdx.files.internal("abutton_menu.png"));
		
		
		dull_trim_t=new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
		
		game = gam;
		
		batch= new SpriteBatch();
		
		font = new BitmapFont();
		
		
	}
	
	void explainybox_explain(){
		
	}
	
	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
		
	    font.setColor(Color.BLACK);
		
	    batch.draw(TITLE_t, 0,400);
	    
	    for (SelectorBox selectorbox:selectorboxes){
	    	batch.draw(Sbox_t, selectorbox.rect.x, selectorbox.rect.y);
	    	batch.draw(Sbox_forward_t, selectorbox.rect_forward.x, selectorbox.rect_forward.y);
	    	batch.draw(Sbox_backward_t, selectorbox.rect_back.x, selectorbox.rect_back.y);
	    	if (selectorbox.rect_forward.contains(tp_x,tp_y)){batch.draw(Sbox_trim_t, selectorbox.rect_forward.x, selectorbox.rect_forward.y);}
	    	if (selectorbox.rect_back.contains(tp_x,tp_y)){batch.draw(Sbox_trim_t, selectorbox.rect_back.x, selectorbox.rect_back.y);}
	    	blackfont.draw(batch, selectorbox.identity, selectorbox.rect.x, selectorbox.rect.y+53, 140,1,true);
	    	blackfont.draw(batch, selectorbox.displayableString(), selectorbox.rect.x+40, selectorbox.rect.y+25, 60,1,true);
	    }
		
	    
	    batch.draw(menu_t, menu_r.x, menu_r.y);
	    if (menu_r.contains(tp_x,tp_y)){
			batch.draw(dull_trim_t, menu_r.x, menu_r.y);
		}
	    
		batch.draw(EBOX_t, 10, 10);
		
		
		
		if (musicVolumeSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Set the volume at which the music plays.", 15, 64, 290,1, true);
		}

		if (sfxVolumeSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Set the volume at which sound effects play.", 15, 64, 290,1, true);
		}
		//if (screenSizeSelectorBox.rect.contains(tp_x,tp_y)){
		//	blackfont.draw(batch, "Screen the wrong size? Difficulty reading text? See if this helps.", 15, 83, 290,1, true);
		//	purplefont.draw(batch, "(if you're playing the web version browser options work better tbh)", 15, 46, 290,1, true);
		//}
		if (acalcSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Leave the autocalc Off if you want a challenge, or set it to Detail if you want more info and don't mind cluttering the screen.", 15, 83, 290,1, true);
		}
		
		if (tthSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "How we display turns until a mine hits. Normal displays in-HUD on mouseover, Below displays below the mine, Off doesn't display.", 15, 83, 290,1, true);
		}
		
		if (gamespeedSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Speed up the game to get through firing animations faster, or slow it down to savor the suspense / make it run more smoothly.", 15, 83, 290,1, true);
		}
		
		if (flickeringSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Disable/Enable all animations which could be referred to as 'flickering'. Potentially useful for people with epilepsy.", 15, 83, 290,1, true);
		}
		
		if (backgroundSelectorBox.rect.contains(tp_x,tp_y)){
			blackfont.draw(batch, "Void is pure black background, Crude is scrolling stars with parallax, Pretty is currently not implemented.", 15, 83, 290,1, true);
		}
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
				
			for (SelectorBox selectorbox:selectorboxes){
				if (selectorbox.rect_forward.contains(tp_x,tp_y)){
					selectorbox.cycle_fwd();
					update_options();
					bgm.setVolume(option_music_volume);
					arrowsound.play(option_sfx_volume);
				}
				if (selectorbox.rect_back.contains(tp_x,tp_y)){
					selectorbox.cycle_back();
					update_options();
					bgm.setVolume(option_music_volume);
					arrowsound.play(option_sfx_volume);
				}
			}
				
				
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