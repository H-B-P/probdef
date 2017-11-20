package com.hbp.probdef;

public class SelectorBox_Acalc extends SelectorBox {
	
	   public SelectorBox_Acalc(String ident, String prefs_name, float x, float y){
		   super(ident,prefs_name, x, y, false);
		   
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_string.equals("Off")){
			   current_nature_string="Normal";
		   }
		   else{
			   if (pnam.equals("Combination_Preferences")){
				   if (current_nature_string.equals("Normal")){
					   current_nature_string="Detail, Shield";
				   }
				   else if (current_nature_string.equals("Detail, Shield")){
					   current_nature_string="Detail, Result";
				   }
			   }
			   else{
				   if (current_nature_string.equals("Normal")){
					   current_nature_string="Detail";
				   }
			   }
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
		   else if (current_nature_string.equals("Detail, Shield")){
			   current_nature_string="Normal";
		   }
		   else if (current_nature_string.equals("Detail, Result")){
			   current_nature_string="Detail, Shield";
		   }
		   update_pref_str();
	   }
	   
}