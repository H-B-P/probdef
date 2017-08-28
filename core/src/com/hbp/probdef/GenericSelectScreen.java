package com.hbp.probdef;

/*~SUMMARY~
 * 
 * This screen acts as a base for menus: library, levels, etc.
 * 
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GenericSelectScreen extends MetaScreen {
    final ProbDef game;
	
	private Music bgm;
	
	
	private Rectangle nxt_r;
	private Texture nxt_t;	
	
	private Rectangle prv_r;
	private Texture prv_t;
	
	private Texture banner_example_one_t;
	private Texture banner_example_two_t;
	private Texture banner_example_three_t;
	
	Texture banner_t;
	
	
	
	Rectangle menu_r;
	Texture menu_t;
	
	
	
	Rectangle one_r;
	Texture one_t;
	
	Rectangle two_r;
	Texture two_t;
	
	Rectangle three_r;
	Texture three_t;
	
	Rectangle four_r;
	Texture four_t;
	
	Rectangle five_r;
	Texture five_t;
	
	Rectangle six_r;
	Texture six_t;
	
	
	
	String TOPIC;
	String first_topic;
	String last_topic;
	
	private boolean is_it_first;
	private boolean is_it_last;
	
	private Rectangle banner_r;
	
	Preferences prefs;
	
	private BitmapFont font;
	
	private Texture dull_trim_t;
	private Texture prv_trim_t;
	private Texture nxt_trim_t;
	
	int NUMBER_OF_LEVELS;
	
	Texture blank_t;
	
	public Sound arrowsound;
	
	private SpriteBatch batch;
	
	public GenericSelectScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		prefs=Gdx.app.getPreferences("probdef_preferences");
		
		if (!prefs.contains("example_topic")){
			prefs.putString("example_topic", "example_1");
		}
		TOPIC=prefs.getString("example_topic");
		
		
		is_it_first=false;
		is_it_last=false;
		
		
		first_topic="example_1";
		last_topic="example_3";
		bgm=Gdx.audio.newMusic(Gdx.files.internal("LevelSelect.mp3"));
		bgm.setLooping(true);
		   bgm.play();
		   
		dull_trim_t=new Texture(Gdx.files.internal("abutton_trim_boring.png"));
		
		prv_trim_t=new Texture(Gdx.files.internal("pobutton_left_trim.png"));
		nxt_trim_t=new Texture(Gdx.files.internal("pobutton_right_trim.png"));
		
		blank_t=new Texture(Gdx.files.internal("abutton_blank.png"));
		
		one_t=blank_t;
		two_t=blank_t;
		three_t=blank_t;
		four_t=blank_t;
		five_t=blank_t;
		six_t=blank_t;
		
		nxt_r = new Rectangle();
		nxt_r.x=240;
		nxt_r.y=310;
		nxt_r.height=60;
		nxt_r.width=60;
		nxt_t = new Texture(Gdx.files.internal("pobutton_right.png"));
		
		prv_r = new Rectangle();
		prv_r.x=20;
		prv_r.y=310;
		prv_r.height=60;
		prv_r.width=60;
		prv_t = new Texture(Gdx.files.internal("pobutton_left.png"));

		
		menu_r = new Rectangle();
		menu_r.x=170;
		menu_r.y=400;
		menu_r.height=60;
		menu_r.width=140;
		menu_t = new Texture(Gdx.files.internal("abutton_menu.png"));
		
		load_in_button_textures();
		load_in_banner_textures();
		
		banner_r = new Rectangle();
		banner_r.x=90;
		banner_r.y=310;
		banner_r.height=60;
		banner_r.width=140;
		banner_t= new Texture(Gdx.files.internal("banner_blank.png"));
		
		
		batch=new SpriteBatch();
		
		game = gam;
		
		
		
		font = new BitmapFont();
		
		arrowsound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344510__jeremysykes__select03.wav"));		
		
		adjustToTopic();
		check_first_last();
		set_up_level_button_positions(NUMBER_OF_LEVELS);
	}
	
	void set_up_level_button_positions(int how_many){
		if (how_many==1){
			
			one_r = new Rectangle();
			one_r.x=90;
			one_r.y=230;
			one_r.height=60;
			one_r.width=140;
			
		}
		if (how_many>1){
			one_r = new Rectangle();
			one_r.x=10;
			one_r.y=230;
			one_r.height=60;
			one_r.width=140;
			
			two_r = new Rectangle();
			two_r.x=170;
			two_r.y=230;
			two_r.height=60;
			two_r.width=140;
			
		}
		
		if (how_many==3){
		
			three_r = new Rectangle();
			three_r.x=90;
			three_r.y=140;
			three_r.height=60;
			three_r.width=140;
		}
		
		if (how_many>3){
			three_r = new Rectangle();
			three_r.x=10;
			three_r.y=140;
			three_r.height=60;
			three_r.width=140;
			
			four_r = new Rectangle();
			four_r.x=170;
			four_r.y=140;
			four_r.height=60;
			four_r.width=140;
		}
		
		if (how_many==5){
			
			five_r = new Rectangle();
			five_r.x=90;
			five_r.y=50;
			five_r.height=60;
			five_r.width=140;
		}
		
		if (how_many>5){
		
			five_r = new Rectangle();
			five_r.x=10;
			five_r.y=50;
			five_r.height=60;
			five_r.width=140;
			
			six_r = new Rectangle();
			six_r.x=170;
			six_r.y=50;
			six_r.height=60;
			six_r.width=140;
		}
	}
	
	void load_in_banner_textures(){
		banner_example_one_t=new Texture(Gdx.files.internal("banner_example_1.png"));
		banner_example_two_t=new Texture(Gdx.files.internal("banner_example_2.png"));
		banner_example_three_t=new Texture(Gdx.files.internal("banner_example_3.png"));
	}
	
	void load_in_button_textures(){
		
	}
	
	public void check_first_last(){
		if(TOPIC.equals(first_topic)){
			is_it_first=true;
		}
		else{
			is_it_first=false;
		}
		if(TOPIC.equals(last_topic)){
			is_it_last=true;
		}
		else{
			is_it_last=false;
		}
	}
	
	public void adjustToTopic(){
		
		prefs.putString("example", TOPIC);
		if(TOPIC.equals("example_1")){
			banner_t=banner_example_one_t;
			NUMBER_OF_LEVELS=5;
		}
		if(TOPIC.equals("example_2")){
			banner_t=banner_example_two_t;
			NUMBER_OF_LEVELS=3;
		}
		if(TOPIC.equals("example_3")){
			banner_t=banner_example_three_t;
			NUMBER_OF_LEVELS=6;
		}
		
	}
	
	public void draw_level_buttons(){
		if (NUMBER_OF_LEVELS>=1){batch.draw(one_t, one_r.x, one_r.y);}
		if (NUMBER_OF_LEVELS>=2){batch.draw(two_t, two_r.x, two_r.y);}
		if (NUMBER_OF_LEVELS>=3){batch.draw(three_t, three_r.x, three_r.y);}
		if (NUMBER_OF_LEVELS>=4){batch.draw(four_t, four_r.x, four_r.y);}
		if (NUMBER_OF_LEVELS>=5){batch.draw(five_t, five_r.x, five_r.y);}
		if (NUMBER_OF_LEVELS>=6){batch.draw(six_t, six_r.x, six_r.y);}

	}
	
	private void draw_banner_and_arrows(){
		batch.draw(banner_t, banner_r.x, banner_r.y);
		if (!is_it_first){
			batch.draw(prv_t, prv_r.x, prv_r.y);
		}
		if (!is_it_last){
			batch.draw(nxt_t, nxt_r.x, nxt_r.y);
		}
	}
	
	private void draw_trims(){
		
		if (menu_r.contains(tp_x,tp_y)){
			batch.draw(dull_trim_t, menu_r.x, menu_r.y);
		}
		
		if (prv_r.contains(tp_x,tp_y) && !is_it_first){
			batch.draw(prv_trim_t, prv_r.x,prv_r.y);
		}
		if (nxt_r.contains(tp_x,tp_y) && !is_it_last){
			batch.draw(nxt_trim_t, nxt_r.x,nxt_r.y);
		}
		
		if (NUMBER_OF_LEVELS>=1){
			if (one_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, one_r.x, one_r.y);
			}
		}
		if (NUMBER_OF_LEVELS>=2){
			if (two_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, two_r.x, two_r.y);
			}
		}
		if (NUMBER_OF_LEVELS>=3){
			if (three_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, three_r.x, three_r.y);
			}
		}
		if (NUMBER_OF_LEVELS>=4){
			if (four_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, four_r.x, four_r.y);
			}
		}
		if (NUMBER_OF_LEVELS>=5){
			if (five_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, five_r.x, five_r.y);
			}
		}
		if (NUMBER_OF_LEVELS>=6){
			if (six_r.contains(tp_x,tp_y)){
				batch.draw(dull_trim_t, six_r.x, six_r.y);
			}
		}
	}
	
	void go_forward(){
		
		arrowsound.play();
		if (TOPIC.equals("example_1")){
			TOPIC="example_2";
			
		}
		else if (TOPIC.equals("example_2")){
			TOPIC="example_3";
			
		}
	}
	
	void go_back(){
		
		arrowsound.play();
		if (TOPIC.equals("example_2")){
			TOPIC="example_1";
			
		}
		else if (TOPIC.equals("example_3")){
			TOPIC="example_2";
			
		}
	}
	
	private void check_for_exits(){
		if (Gdx.input.justTouched()){
			if (menu_r.contains(tp_x,tp_y)){
				game.setScreen(new TitleScreen(game, true));
				dispose();
			}
		}
	}
	
	public void generic_render(){
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		meta_render();
		
		
		
		batch.begin();
		
		batch.setProjectionMatrix(camera.combined);
		draw_level_buttons();
		draw_banner_and_arrows();
		batch.draw(menu_t, menu_r.x, menu_r.y);
		draw_trims();
		
		batch.end();
		
		if (Gdx.input.justTouched()){
			if (nxt_r.contains(tp_x, tp_y) && !is_it_last){
				go_forward();
				adjustToTopic();
				check_first_last();
				set_up_level_button_positions(NUMBER_OF_LEVELS);

			}
			if (prv_r.contains(tp_x, tp_y) && !is_it_first){
				go_back();
				adjustToTopic();
				check_first_last();
				set_up_level_button_positions(NUMBER_OF_LEVELS);

			}
		}
		
		check_for_exits();
	}
	
	@Override
	public void render(float delta) {
		
		generic_render();
		
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
	
	void generic_select_dispose(){
		
		meta_dispose();
		
		bgm.stop();
		   bgm.dispose();
		
		nxt_t.dispose();
		
		prv_t.dispose();
	}
	
	@Override
	public void dispose() {

		generic_select_dispose();
		
		
	}
}