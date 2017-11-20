package com.hbp.probdef;

public class SelectorBox_TTH_Display extends SelectorBox {
	
	   public SelectorBox_TTH_Display(String ident, String prefs_name, float x, float y){
		   super(ident, prefs_name, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Off")){
			   current_nature_string="Normal";
		   }
		   else if (current_nature_string.equals("Normal")){
			   current_nature_string="Below";
		   }
		   update_pref_str();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_string.equals("Normal")){
			   current_nature_string="Off";
		   }
		   else if (current_nature_string.equals("Below")){
			   current_nature_string="Normal";
		   }
		   update_pref_str();
	   }
	   
}