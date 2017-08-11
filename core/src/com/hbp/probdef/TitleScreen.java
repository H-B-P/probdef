package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.viewport.*;
import com.hbp.probdef.ProbDef;

public class TitleScreen extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	private Rectangle EXPLORABLES_r;
	private Texture EXPLORABLES_t;
	
	private Rectangle TUTORIAL_r;
	private Texture TUTORIAL_t;
	
	private Rectangle ARCADE_r;
	private Texture ARCADE_t;
	
	private Texture PROBDEF_t;
	
	private Texture TRIM_t;

	
	private BitmapFont font;

	
	private float tp_x;
	private float tp_y;
	
	boolean are_instructions_visible;
	
	private Sound hellosound;
	
	private boolean wastouched;
	
	private ScreenViewport viewport;
	
	private SpriteBatch batch;
	
	public TitleScreen(final ProbDef gam, boolean play_the_sound, boolean is_android_on) {
		
		super(gam, play_the_sound, is_android_on);
		
		System.out.print(camera);
		
		ANDROID=is_android_on;
		
		wastouched=false;
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.play();
		
				
		//preferred_mode=prefs.getString("MODE");
		//preferred_topic=prefs.getString("TOPIC");
		
		

		
		
		
		
		EXPLORABLES_r = new Rectangle();
		EXPLORABLES_r.x=60;
		EXPLORABLES_r.y=170;
		EXPLORABLES_r.height=60;
		EXPLORABLES_r.width=200;
		EXPLORABLES_t = new Texture(Gdx.files.internal("abutton_long_explorables.png"));
		
		ARCADE_r = new Rectangle();
		ARCADE_r.x=60;
		ARCADE_r.y=70;
		ARCADE_r.height=60;
		ARCADE_r.width=200;
		ARCADE_t = new Texture(Gdx.files.internal("abutton_long_arcade.png"));
		
		TUTORIAL_r = new Rectangle();
		TUTORIAL_r.x=60;
		TUTORIAL_r.y=270;
		TUTORIAL_r.height=60;
		TUTORIAL_r.width=200;
		TUTORIAL_t = new Texture(Gdx.files.internal("abutton_long_tutorial.png"));
		
		PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF.png"));
		
		TRIM_t = new Texture(Gdx.files.internal("abutton_long_trim.png"));
		
		game = gam;
		
		batch= new SpriteBatch();
		
		font = new BitmapFont();
		
		
		hellosound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/341250__jeremysykes__select01.wav"));
		if (play_the_sound){
			hellosound.play();
		}
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Vector3 scr_vec= new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
		Vector3 irl_vec=camera.unproject(scr_vec);
		tp_x=irl_vec.x;
		tp_y=irl_vec.y;
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
		
	    font.setColor(Color.BLACK);
		
	    batch.draw(TUTORIAL_t, TUTORIAL_r.x, TUTORIAL_r.y);
		batch.draw(ARCADE_t, ARCADE_r.x, ARCADE_r.y);
		batch.draw(EXPLORABLES_t, EXPLORABLES_r.x, EXPLORABLES_r.y);
		
		
		
		if (EXPLORABLES_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, EXPLORABLES_r.x, EXPLORABLES_r.y);
		}
		
		if (ARCADE_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, ARCADE_r.x, ARCADE_r.y);
		}
		
		if (TUTORIAL_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, TUTORIAL_r.x, TUTORIAL_r.y);
		}
		
		batch.draw(PROBDEF_t, 20,380);
		
		batch.end();
		
		//tp_x=Gdx.input.getX();
		//tp_y=Gdx.input.getY();
		
		if (Gdx.input.justTouched()) {
				
				if (EXPLORABLES_r.contains(tp_x,tp_y)){
					//game.setScreen(new CampaignSelectScreen(game, ANDROID));
					//dispose();
				}
				
				if (TUTORIAL_r.contains(tp_x,tp_y)){
					game.setScreen(new TutorialScreen(game, true, ANDROID));
		            dispose();
				}
				
				if (ARCADE_r.contains(tp_x,tp_y)){
		            
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
		
		TUTORIAL_t.dispose();
		EXPLORABLES_t.dispose();
		ARCADE_t.dispose();
		PROBDEF_t.dispose();

		TRIM_t.dispose();

	}
}