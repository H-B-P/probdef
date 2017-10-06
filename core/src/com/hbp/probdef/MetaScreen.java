package com.hbp.probdef;

/*~SUMMARY~
 * 
 * The most basic screen. Every other screen is an extension of this.
 * 
 * Its job is to handle the camera, load in the 'poncho', and play the sound that happens when you start the game.
 * 
 * All the actual content is left for its children.
 * 
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.audio.Sound;

import com.hbp.probdef.ProbDef;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MetaScreen implements Screen { //Regarding implementing vs extending, I want all screens to be playable for debugging and education purposes.
	//In other words, if you want to see what MetaScreen or SpaceyScreen do, you can just load them up and see them in action.
	
	final ProbDef game;
	OrthographicCamera camera;
	
	public boolean ANDROID; // this variable is Very Important
	
	private Sound hellosound;
	
	public float tp_x; //These two lines between them give the True Position of the mouse on the screen.
	public float tp_y; //This is as opposed to the position in whatever grid we're using in a level.
						//(Measured in (unscaled) pixels from the bottom left corner of the screen)
	
	public Texture poncho_t;
	
	public BitmapFont blackfont;
	public BitmapFont purplefont;
	
	public MetaScreen(final ProbDef gam, boolean play_the_sound) {
		
		ANDROID=false;// If this is true, we're running on an Android device. If not, it's a PC or HTML thing.
		//Necessary because of slight differences in gameplay depending on whether people use a mouse or a finger.
		//(for example, you can't hover over things when all you have is a finger)
		
		
		game=gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 320, 480);
		
		blackfont=new BitmapFont(Gdx.files.internal("regular_font/russo.fnt"));
		blackfont.setColor(new Color(0f, 0f, 0f, 1.0f));
		purplefont=new BitmapFont(Gdx.files.internal("regular_font/russo.fnt"));
		purplefont.setColor(new Color(0.6f, 0.2f, 0.6f, 1.0f));
		
		hellosound=Gdx.audio.newSound(Gdx.files.internal("js_sfx/341250__jeremysykes__select01.wav"));
		if (play_the_sound){
			hellosound.play();
		}
		
		poncho_t = new Texture(Gdx.files.internal("blackbar_poncho.png"));
	}
	
	//This function contains the things every screen has to do every step.
	
	public void meta_render() {
		
		if (!ANDROID){Gdx.graphics.setWindowedMode(320, 480);} //At 1:1 scale, the expected screen is 320px wide and 480px tall.
		
		//Comment out the above line if you want to try stretching the screen and confirming this would work on Android.
		
		camera.update();
		
		Vector3 scr_vec= new Vector3(Gdx.input.getX(), Gdx.input.getY(),0); //Get the position of the player's touch.
		Vector3 irl_vec=camera.unproject(scr_vec); // 'Unproject' the position (scale, translate, etc) to get the mouse position in the game world.
		tp_x=irl_vec.x; //extract the x component of mouse position (in pixels)
		tp_y=irl_vec.y; //extract the y component of mouse position (in pixels)
		
		
	}
	
	//All children override this. It's so adorably pathetic.
	
	@Override
	public void render(float delta){
		meta_render();
	}
	
	/*The point of resize is to handle the possibility of a variable screensize.
	 * 
	 *Scaling up to fill an entire screen leads to games looking stretched and messed up.
	 *And scaling by non-integer factors mucks with the artwork.
	 *
	 *So we work out the largest integer scale factor we can use, then place an appropriately-sized screen at the centre of the device.
	 */
	@Override
	public void resize(int width, int height) {
		float scale=0f; //Harsh but fair: if their screen is less than 160px wide or less than 240px tall, give up.
		if (width>=160 && height>=240){
			scale=0.5f; //Special indulgence for scaling down by a factor of two. It actually doesn't look awful, and better impossibly small than literally invisible.
		}
		if (width>=320 && height>=480){
			scale=1f; //"If the screen is screen-sized, size the screen to be the size of the screen."
		}
		while (width>=(320*(scale+1)) && height>=(480*(scale+1))){
			scale+=1f; //For all n, we see if we could scale up by a factor of n and still fit on the device's screen.
		}
		
		System.out.println("Target scale is: "+ scale); //Useful for debugging.
		
		camera.setToOrtho(false, (float)width/(float)scale, (float)height/(float)scale); //Set the screen to the right size.
		camera.translate(-((float)width/(float)scale-320)/2f, -((float)height/(float)scale-480)/2f); //Center the screen.
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
	
	public void meta_dispose(){
		hellosound.dispose();
		poncho_t.dispose();
	}
	@Override
	public void dispose() {
		meta_dispose();
	}
}