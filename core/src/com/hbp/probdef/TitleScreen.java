package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class TitleScreen extends MetaScreen {
	
	final ProbDef game;
	
	private Music bgm;
	
	private Rectangle LIBRARY_r;
	private Texture LIBRARY_t;
	
	private Rectangle TUTORIAL_r;
	private Texture TUTORIAL_t;
	
	private Rectangle ARCADE_r;
	private Texture ARCADE_t;
	
	private Texture PROBDEF_t;
	
	private Texture TRIM_t;

	
	private BitmapFont font;
	
	boolean are_instructions_visible;
	
	private Sound hellosound;
	
	private SpriteBatch batch;
	
	public TitleScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.play();
		
		
		
		
		
		LIBRARY_r = new Rectangle();
		LIBRARY_r.x=60;
		LIBRARY_r.y=170;
		LIBRARY_r.height=60;
		LIBRARY_r.width=200;
		LIBRARY_t = new Texture(Gdx.files.internal("abutton_long_library.png"));
		
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
		
		
	}

	@Override
	public void render(float delta) {
		
		meta_render();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		
		
		
	    font.setColor(Color.BLACK);
		
	    batch.draw(PROBDEF_t, 20,380);
	    
	    batch.draw(TUTORIAL_t, TUTORIAL_r.x, TUTORIAL_r.y);
		batch.draw(ARCADE_t, ARCADE_r.x, ARCADE_r.y);
		batch.draw(LIBRARY_t, LIBRARY_r.x, LIBRARY_r.y);
		
		
		
		if (LIBRARY_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, LIBRARY_r.x, LIBRARY_r.y);
		}
		
		if (ARCADE_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, ARCADE_r.x, ARCADE_r.y);
		}
		
		if (TUTORIAL_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, TUTORIAL_r.x, TUTORIAL_r.y);
		}
		
		
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
				
				if (LIBRARY_r.contains(tp_x,tp_y)){
					game.setScreen(new LibrarySelectScreen(game, true));
		            dispose();
				}
				
				if (TUTORIAL_r.contains(tp_x,tp_y)){
					game.setScreen(new TutorialScreen(game, true));
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
		LIBRARY_t.dispose();
		ARCADE_t.dispose();
		PROBDEF_t.dispose();

		TRIM_t.dispose();
		batch.dispose();
	}
}