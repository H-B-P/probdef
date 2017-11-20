package com.hbp.probdef;

public class SelectorBox_Background extends SelectorBox {
	
	   public SelectorBox_Background(String ident, String prefs_name, float x, float y){
		   super(ident, prefs_name, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Void")){
			   current_nature_string="Crude";
		   }
		   else if (current_nature_string.equals("Crude")){
			   current_nature_string="Pretty";
		   }
		   update_pref_str();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_string.equals("Crude")){
			   current_nature_string="Void";
		   }
		   else if (current_nature_string.equals("Pretty")){
			   current_nature_string="Crude";
		   }
		   update_pref_str();
	   }
	   
}