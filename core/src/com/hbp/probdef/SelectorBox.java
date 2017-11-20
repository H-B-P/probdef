package com.hbp.probdef;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;

public class SelectorBox {
	Rectangle rect;
	Rectangle rect_forward;
	Rectangle rect_back;
	
	String identity;
	
	String current_nature_string;
	int current_nature_int;
	
	Preferences prefs;
	
	boolean is_it_numeric;
	
	String pnam;
	
	SelectorBox(String ident, String prefs_name, float x, float y, boolean num){
		is_it_numeric=num;
		
		identity=ident;
		
		pnam=prefs_name;
		
		//prefs = Gdx.app.getPreferences("galen_preferences_II");
		
		//prefs = Gdx.app.getPreferences("Combination_Preferences");
		
		prefs = Gdx.app.getPreferences(prefs_name);
		
		current_nature_string="broken";
		
		if (is_it_numeric){
			retrieve_pref_num();
		}
		else{
			current_nature_string=prefs.getString(identity);
		}
		
		rect=new Rectangle(x,y, 140,60);
		rect_forward=new Rectangle(x+100,y,40,40);
		rect_back=new Rectangle(x,y,40,40);
	}
	void cycle_fwd(){
		
	}
	void cycle_back(){
		
	}
	String displayableString(){
		return current_nature_string;
	}
	void reset_pref(){
		
	}
	void retrieve_pref_num(){
		current_nature_int=prefs.getInteger(identity);
	}
	void update_pref_num(){
		prefs.putInteger(identity, current_nature_int);
		prefs.flush();
	}
	void update_pref_str(){
		prefs.putString(identity, current_nature_string);
		prefs.flush();
	}
}
