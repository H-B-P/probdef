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

public class GenericBookScreen extends ProbGameScreen {
	
	final ProbDef game;
	
	private SpriteBatch batch;
	
	int page;
	
	double page_time;
	
	Rectangle nxt_r;
	Rectangle prv_r;
	
	Texture nxt_t;
	Texture prv_t;
	
	Texture prv_trim_blue_t;
	Texture nxt_trim_blue_t;
	
	Texture prv_trim_orange_t;
	Texture nxt_trim_orange_t;
	
	int maxpages;
	
	boolean time_to_move_on;
	
	Sound arrowsound;
	
	
	public GenericBookScreen(final ProbDef gam, boolean play_the_sound) {
		
		super(gam, play_the_sound);
		
		game = gam;
		
		page=1;
		
		nxt_r = new Rectangle();
		nxt_r.x=260;
		nxt_r.y=210;
		nxt_r.height=60;
		nxt_r.width=60;
		nxt_t = new Texture(Gdx.files.internal("pobutton_right.png"));
		
		prv_r = new Rectangle();
		prv_r.x=0;
		prv_r.y=210;
		prv_r.height=60;
		prv_r.width=60;
		prv_t = new Texture(Gdx.files.internal("pobutton_left.png"));
		
		batch=new SpriteBatch();
		
		maxpages=2;
		
		prv_trim_blue_t=new Texture(Gdx.files.internal("pobutton_left_trim.png"));
		nxt_trim_blue_t=new Texture(Gdx.files.internal("pobutton_right_trim.png"));
		
		prv_trim_orange_t=new Texture(Gdx.files.internal("pobutton_left_trim_orange.png"));
		nxt_trim_orange_t=new Texture(Gdx.files.internal("pobutton_right_trim_orange.png"));
		
		
		time_to_move_on=false;
		
		arrowsound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344510__jeremysykes__select03.wav"));
	}
	
	@Override
	public void render(float delta) {
		probgame_render(delta);
		
		page_time+=effective_delta;
		
		batch.begin();
		
		if (page>1){
			batch.draw(prv_t, prv_r.x, prv_r.y);
			if (prv_r.contains(tp_x,tp_y)){
				batch.draw(prv_trim_blue_t, prv_r.x, prv_r.y);
			}
		}
		if (page<maxpages){
			batch.draw(nxt_t, nxt_r.x, nxt_r.y);
			if (time_to_move_on){
				batch.draw(nxt_trim_orange_t, nxt_r.x, nxt_r.y);
			}
			if (nxt_r.contains(tp_x,tp_y)){
				batch.draw(nxt_trim_blue_t, nxt_r.x, nxt_r.y);
			}
		}
		batch.end();
		
		if (Gdx.input.justTouched()){
			if (page>1 && prv_r.contains(tp_x,tp_y)){
				page-=1;
				total_time=0; //this is to make the stars jitter when we go back in time;
				new_page();
			}
			if (page<maxpages && nxt_r.contains(tp_x,tp_y)){
				page+=1;
				if (!time_to_move_on){total_time=0;}//make stars jitter if and only if our forward motion was premature.
				new_page();
			}
		}
	}
	
	void new_page(){
		page_time=0;
		seconds=0;
		
		arrowsound.play();
		for (Turret turret:turrets){
			turret.targeted=false;
			turret.current_t=turret.normal_t;
		}
		mines.clear();
		explosions.clear();
		
		current_status="waiting";
	}
	
	@Override
	
	   void handle_seconds(){
		   if (seconds<Math.floor(page_time)){
				seconds+=1;
				if (seconds%2==0 && mines.size>0 && !suppress_freezes){
					current_status="targeting";
					TIMESPEED=0;
					currently_active_turret_no=1;
					number_to_turret();
				}
				System.out.println(seconds+" s");
				System.out.println("page "+page);
				level_specific_events();
				
			}
	   }
	
	@Override
	
	void level_specific_turret_setup(){
		   turret_one=new Turret_Standard("triangle");
		   turret_two=new Turret_Standard("square");
		   turret_three=new Turret_Standard("square");
		   turret_four=new Turret_Standard("triangle");
		   
		   turrets_standard.add((Turret_Standard) turret_one);
		   turrets_standard.add((Turret_Standard) turret_two);
		   turrets_standard.add((Turret_Standard) turret_three);
		   turrets_standard.add((Turret_Standard) turret_four);
		   
	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			if (seconds==2){
				spawnMine(0,95);
			}
			if (seconds==5){
				time_to_move_on=true;
			}
		}
		if (page==2){
			if (seconds==4){
				spawnExplosion(200,200);
			}
		}
	}
	
	@Override
	
	void level_specific_timeline(){
		show_the_text=false;
		   suppress_freezes=false;
		if (page==1){
			   if (total_time>1 && total_time<5){
				   show_the_text=true;
					the_text="Example text 1";
			   }
		}
		if (page==2){
			   if (total_time>1 && total_time<5){
				   show_the_text=true;
					the_text="Example text 2";
			   }
		}
	}
	
	void generic_book_dispose(){
		probgame_dispose();
	}
	
	@Override
	public void dispose() {
		generic_book_dispose();
	}
}