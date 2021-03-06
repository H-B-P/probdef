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

public class TitleScreen extends MetaScreen {
	
	final ProbDef game;
		
	private Rectangle LIBRARY_r;
	private Texture LIBRARY_t;
	
	private Rectangle TUTORIAL_r;
	private Texture TUTORIAL_t;
	
	private Rectangle ARCADE_r;
	private Texture ARCADE_t;
	
	private Rectangle CAMPAIGN_r;
	private Texture CAMPAIGN_t;
	
	private Rectangle OPTIONS_r;
	private Texture OPTIONS_t;
	
	private Rectangle CREDITS_r;
	private Texture CREDITS_t;
	
	private Texture PROBDEF_t;
	
	private Texture EBOX_t;
	
	private Texture TRIM_t;

	
	private BitmapFont font;
	
	private SpriteBatch batch;
	
	public TitleScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		System.out.print(camera);
		
		
		bgm=Gdx.audio.newMusic(Gdx.files.internal("Menu.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(option_music_volume);
		bgm.play();
		
		
		TUTORIAL_r = new Rectangle();
		TUTORIAL_r.x=10;
		TUTORIAL_r.y=300;
		TUTORIAL_r.height=60;
		TUTORIAL_r.width=140;
		TUTORIAL_t = new Texture(Gdx.files.internal("abutton_tutorial.png"));
		
		
		
		
		
		CAMPAIGN_r = new Rectangle();
		CAMPAIGN_r.x=170;
		CAMPAIGN_r.y=300;
		CAMPAIGN_r.height=60;
		CAMPAIGN_r.width=140;
		CAMPAIGN_t = new Texture(Gdx.files.internal("abutton_campaign.png"));
		
		LIBRARY_r = new Rectangle();
		LIBRARY_r.x=10;
		LIBRARY_r.y=210;
		LIBRARY_r.height=60;
		LIBRARY_r.width=140;
		LIBRARY_t = new Texture(Gdx.files.internal("abutton_library.png"));
		
		
		ARCADE_r = new Rectangle();
		ARCADE_r.x=170;
		ARCADE_r.y=210;
		ARCADE_r.height=60;
		ARCADE_r.width=140;
		ARCADE_t = new Texture(Gdx.files.internal("abutton_arcade.png"));
		
		
		
		
		
		
		
		
		
		OPTIONS_r = new Rectangle();
		OPTIONS_r.x=10;
		OPTIONS_r.y=120;
		OPTIONS_r.height=60;
		OPTIONS_r.width=140;
		OPTIONS_t = new Texture(Gdx.files.internal("abutton_options.png"));
		
		CREDITS_r = new Rectangle();
		CREDITS_r.x=170;
		CREDITS_r.y=120;
		CREDITS_r.height=60;
		CREDITS_r.width=140;
		CREDITS_t = new Texture(Gdx.files.internal("abutton_credits.png"));
		
		EBOX_t= new Texture(Gdx.files.internal("explainybox.png"));
		
		if (hardcoded_opt_packagename.equals("Combination")){
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF_Combination.png"));
		}
		else if (hardcoded_opt_packagename.equals("Bayesian")){
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF_Bayesian.png"));
		}
		else{
			PROBDEF_t=new Texture(Gdx.files.internal("PROBDEF.png"));
		}
		
		
		
		TRIM_t = new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
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
		
	    batch.draw(PROBDEF_t, 40,405);
	    
	    batch.draw(TUTORIAL_t, TUTORIAL_r.x, TUTORIAL_r.y);
	    batch.draw(LIBRARY_t, LIBRARY_r.x, LIBRARY_r.y);
	    
		batch.draw(ARCADE_t, ARCADE_r.x, ARCADE_r.y);
		batch.draw(CAMPAIGN_t, CAMPAIGN_r.x, CAMPAIGN_r.y);
		
		batch.draw(OPTIONS_t, OPTIONS_r.x, OPTIONS_r.y);
		batch.draw(CREDITS_t, CREDITS_r.x, CREDITS_r.y);
		
		batch.draw(EBOX_t, 10, 10);
		
		
		
		if (TUTORIAL_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, TUTORIAL_r.x, TUTORIAL_r.y);
			if (hardcoded_opt_packagename.equals("Combination")){
				blackfont.draw(batch, "Learn the controls and the basic game mechanics. Do this first.", 10, 83, 300,1, true);
				purplefont.draw(batch, "(unless you played the first ProbDef, in which case skip it)", 10, 48, 300,1, true);
			}
			else if (hardcoded_opt_packagename.equals("Bayesian")){
				blackfont.draw(batch, "Learn the controls and the basic game mechanics. Do this first.", 10, 83, 300,1, true);
				purplefont.draw(batch, "(unless you finished the first ProbDef, in which case skip it)", 10, 48, 300,1, true);
			}
			else{
				blackfont.draw(batch, "Learn the controls and the basic game mechanics.\nDo this first.", 10, 75, 300,1, true);
			}
		}
		
		if (ARCADE_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, ARCADE_r.x, ARCADE_r.y);
			blackfont.draw(batch, "Explore a collection of scenarios with no lose condition.\nTry to improve your highscores.", 10, 75, 300,1, true);
		}
		
		if (CAMPAIGN_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, CAMPAIGN_r.x, CAMPAIGN_r.y);
			blackfont.draw(batch, "Play through a series of levels which build on the premise.\nYou should probably try this before Library or Arcade.", 10, 83, 300,1, true);
		}
		
		if (LIBRARY_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, LIBRARY_r.x, LIBRARY_r.y);
			blackfont.draw(batch, "Use an archive of interactive explanations to understand the math behind the gameplay.\nBring pen and paper.", 10, 83, 300,1, true);
		}
		
		if (OPTIONS_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, OPTIONS_r.x, OPTIONS_r.y);
			blackfont.draw(batch,"Adjust the display settings, the audio settings, the game speed, and the accessibility features.", 10, 75, 300,1, true);
		}
		
		if (CREDITS_r.contains(tp_x,tp_y)){
			batch.draw(TRIM_t, CREDITS_r.x, CREDITS_r.y);
			blackfont.draw(batch,"Who did this?\nWhy?", 10, 64, 300,1, true);

		}
		
		
		
		
		batch.draw(poncho_t, -640, -960);
		
		batch.end();
		
		if (Gdx.input.justTouched()) {
				
				if (LIBRARY_r.contains(tp_x,tp_y)){
					game.setScreen(new SelectScreen_Library(game, true));
		            dispose();
				}
				
				if (TUTORIAL_r.contains(tp_x,tp_y)){
					if (hardcoded_opt_packagename.equals("Bayesian")){
						game.setScreen(new GameScreen_Bayes(game));
					}
					else{
						game.setScreen(new GameScreen_Prob(game));
					}
		            dispose();
				}
				
				if (CREDITS_r.contains(tp_x,tp_y)){
					game.setScreen(new CreditsScreen(game, true));
		            dispose();
				}
				
				if (CAMPAIGN_r.contains(tp_x,tp_y)){
					if (hardcoded_opt_packagename.equals("Combination")){
						game.setScreen(new CampaignScreen_Combination(game, true));
					}
					else if (hardcoded_opt_packagename.equals("Bayesian")){
						game.setScreen(new CampaignScreen_Inference(game, true));
					}
					else{
						game.setScreen(new CampaignScreen_Inference(game, true));
					}
		            dispose();
				}
				
				if (ARCADE_r.contains(tp_x,tp_y)){
					game.setScreen(new SelectScreen_Arcade(game, true));
		            dispose();
				}
				
				if (OPTIONS_r.contains(tp_x,tp_y)){
					game.setScreen(new OptionsScreen(game, true));
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
		
		TUTORIAL_t.dispose();
		LIBRARY_t.dispose();
		ARCADE_t.dispose();
		PROBDEF_t.dispose();

		TRIM_t.dispose();
		batch.dispose();
	}
}