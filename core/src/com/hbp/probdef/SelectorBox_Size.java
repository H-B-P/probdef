package com.hbp.probdef;

public class SelectorBox_Size extends SelectorBox {
	int interval;
	int max;
	int min;
	
	   public SelectorBox_Size(String ident, String prefs_name, float x, float y){
		   super(ident, prefs_name, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Small")){
			   current_nature_string="Normal";
		   }
		   else if (current_nature_string.equals("Normal")){
			   current_nature_string="Large";
		   }
		   else if (current_nature_string.equals("Large")){
			   current_nature_string="Huge";
		   }
		   else if (current_nature_string.equals("Huge")){
			   current_nature_string="Giant";
		   }
		   update_pref_str();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_string.equals("Normal")){
			   current_nature_string="Small";
		   }
		   else if (current_nature_string.equals("Large")){
			   current_nature_string="Normal";
		   }
		   else if (current_nature_string.equals("Huge")){
			   current_nature_string="Large";
		   }
		   else if (current_nature_string.equals("Giant")){
			   current_nature_string="Huge";
		   }
		   update_pref_str();
	   }
	   
}