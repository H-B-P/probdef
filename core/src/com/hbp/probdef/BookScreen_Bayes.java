package com.hbp.probdef;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hbp.probdef.ProbDef;

public class BookScreen_Bayes extends GameScreen_Bayes {
	
	final ProbDef game;
	
	int page;
	
	double page_time;
	
	Rectangle nxt_r;
	Rectangle prv_r;
	
	Texture nxt_t;
	Texture prv_t;
	
	Texture prv_trim_blue_t;
	Texture nxt_trim_blue_t;
	
	Texture prv_trim_purple_t;
	Texture nxt_trim_purple_t;
	
	int maxpages;
	
	boolean time_to_move_on;
	
	Sound arrowsound;
	
	int pages_done;
	
	String bookname;
	
	public BookScreen_Bayes(final ProbDef gam) {
		
		super(gam);
		
		enemyship_engine_a_t=new Texture(Gdx.files.internal("ship_bases/engine_zigzag_a.png"));
		enemyship_engine_b_t=new Texture(Gdx.files.internal("ship_bases/engine_zigzag_b.png"));
		enemyship_engine_c_t=new Texture(Gdx.files.internal("ship_bases/engine_zigzag_c.png"));
		
		
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
		
		number_of_turret_types=3;
		
		prv_trim_blue_t=new Texture(Gdx.files.internal("pobutton_left_trim.png"));
		nxt_trim_blue_t=new Texture(Gdx.files.internal("pobutton_right_trim.png"));
		
		prv_trim_purple_t=new Texture(Gdx.files.internal("pobutton_left_trim_orange.png"));
		nxt_trim_purple_t=new Texture(Gdx.files.internal("pobutton_right_trim_orange.png"));
		
		
		time_to_move_on=false;
		suppress_exits=true;
		
		shields=10;
		
		set_book_name();
		
		pages_done=prefs.getInteger(bookname);
		
		arrowsound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/344510__jeremysykes__select03.wav"));
	}
	
	void set_book_name(){
		bookname="example_bayes";
	}
	
	void generic_book_render(float delta){
		bayesgame_render(delta);
		
		page_time+=effective_delta;
		
		if (page==(pages_done+1) &&time_to_move_on){
			pages_done=page;
			prefs.putInteger(bookname, pages_done);
			prefs.flush();
		}
		
		batch.begin();
		
		if (page>1){
			batch.draw(prv_t, prv_r.x, prv_r.y);
			if (prv_r.contains(tp_x,tp_y)){
				batch.draw(prv_trim_blue_t, prv_r.x, prv_r.y);
			}
		}
		if (page<maxpages && page<=pages_done){
			batch.draw(nxt_t, nxt_r.x, nxt_r.y);
			//if (time_to_move_on && seconds%2==1){
			//	batch.draw(nxt_trim_purple_t, nxt_r.x, nxt_r.y);
			//}
			if (nxt_r.contains(tp_x,tp_y)){
				batch.draw(nxt_trim_blue_t, nxt_r.x, nxt_r.y);
			}
		}
		
		if (option_flicker){
			if (page==maxpages && time_to_move_on && (seconds/2)%2==1){
				batch.draw(green_button_trim_t, menu_button_r.x, menu_button_r.y);
			}
		}
		else{
			if (page==maxpages && time_to_move_on){
				batch.draw(green_button_trim_t, menu_button_r.x, menu_button_r.y);
			}
		}
		
		batch.end();
		
		if (Gdx.input.justTouched()){
			if (page>1 && prv_r.contains(tp_x,tp_y)){
				page-=1;
				total_time=0; //this is to make the stars jitter when we go back in time;
				new_page();
			}
			if (page<maxpages && nxt_r.contains(tp_x,tp_y) && page<=pages_done){
				page+=1;
				if (!time_to_move_on){total_time=0;}//make stars jitter if and only if our forward motion was premature.
				new_page();
			}
		}
	}
	
	@Override
	public void render(float delta) {
		generic_book_render(delta);
	}
	
	
	@Override
	void level_specific_music_setup(){
		bgm=Gdx.audio.newMusic(Gdx.files.internal("MCS_Detective.mp3"));
		bgm.setLooping(true);
		bgm.play();
	}
	
	void new_page(){
		page_time=0;
		seconds=0;
		
		shipwave=0;
		
		shields=10;
		
		arrowsound.play();
		
		mines.clear();
		
		explosions.clear();
		turrets.clear();
		turrets_standard.clear();
		
		enemyships.clear();
		
		vane_one.targeted=false;
		vane_two.targeted=false;
		vane_one.target_ship=null;
		vane_two.target_ship=null;
		currently_active_vane=null;
		
		current_status="waiting";
	}
	
	
	
//	@Override
//	
//	void level_specific_turret_setup(){
//		   turret_one=new Turret_Standard("triangle");
//		   turret_two=new Turret_Standard("square");
//		   turret_three=new Turret_Standard("square");
//		   turret_four=new Turret_Standard("triangle");
//		   
//		   turrets_standard.add((Turret_Standard) turret_one);
//		   turrets_standard.add((Turret_Standard) turret_two);
//		   turrets_standard.add((Turret_Standard) turret_three);
//		   turrets_standard.add((Turret_Standard) turret_four);
//		   
//	   }
	
	@Override
	
	void level_specific_events(){
		if (page==1){
			if (seconds==2){
			}
			if (seconds==5){
				time_to_move_on=true;
			}
		}
		if (page==2){
			if (seconds==4){
				spawnExplosion(200,200);
			}
			if (seconds==7){
				time_to_move_on=true;
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
	
@Override
	
	void handle_seconds(){
	   if (seconds<Math.floor(page_time)){
			seconds+=1;
			
			if (seconds%2==0 && !current_status.equals("bowling")){
				if(any_interesting_ships()){
					round+=1;
					hand_over_to_targeting();
				}
				else{
					shipwave+=1;
					round=0;
					level_specific_waves();
					if (!suppress_phasing){
						hand_over_to_bowling();
						if (shipwave<total_shipwaves){
							shock.play(option_sfx_volume);
						}
					}
				}
			}
			
			System.out.println(seconds+" s");
			level_specific_events();
			
			
			
		}
	}

	@Override
	
	void level_specific_probability_display(){
		for (EnemyShip enemyship:enemyships){
			if (enemyship.obscured){
				acalc_greenfont.draw(batch, "C: "+present_float(enemyship.assignedprob_one*100.0f)+"%\nT: "+present_float(enemyship.assignedprob_two*100.0f)+"%\nP: "+present_float(enemyship.assignedprob_three*100.0f)+"%", enemyship.rect.x-20, enemyship.rect.y-30, 100, 1, true);
			}
		}
	}
	
	@Override
	void exit_level(){
		   game.setScreen(new SelectScreen_Library(game, true));
		   dispose();
	}
	
	void generic_book_dispose(){
		gamey_dispose();
	}
	
	@Override
	public void dispose() {
		generic_book_dispose();
	}
}