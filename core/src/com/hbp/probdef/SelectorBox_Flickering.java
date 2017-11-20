package com.hbp.probdef;

public class SelectorBox_Flickering extends SelectorBox {
	
	   public SelectorBox_Flickering(String ident, String prefs_name, float x, float y){
		   super(ident, prefs_name, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Off")){
			   current_nature_string="On";
		   }
		   else if (current_nature_string.equals("On")){
			   current_nature_string="Off";
		   }
		   update_pref_str();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_string.equals("On")){
			   current_nature_string="Off";
		   }
		   else if (current_nature_string.equals("Off")){
			   current_nature_string="On";
		   }
		   update_pref_str();
	   }
	   
}