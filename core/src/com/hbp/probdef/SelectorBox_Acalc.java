package com.hbp.probdef;

public class SelectorBox_Acalc extends SelectorBox {
	int interval;
	int max;
	int min;
	
	   public SelectorBox_Acalc(String ident, float x, float y){
		   super(ident, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Off")){
			   current_nature_string="Normal";
		   }
		   else if (current_nature_string.equals("Normal")){
			   current_nature_string="Detail";
		   }
		   update_pref_str();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_string.equals("Normal")){
			   current_nature_string="Off";
		   }
		   else if (current_nature_string.equals("Detail")){
			   current_nature_string="Normal";
		   }
		   update_pref_str();
	   }
	   
}