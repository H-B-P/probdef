package com.hbp.probdef;

public class SelectorBox_Percentage extends SelectorBox {
	int interval;
	int max;
	int min;
	
	   public SelectorBox_Percentage(String ident, float x, float y){
		   super(ident, x, y, true);
		   
		   interval=20;
		   max=100;
		   min=0;
	   }
	   
	   @Override
	   void cycle_fwd(){
		   if (current_nature_int<max){
			   current_nature_int+=interval;
		   }
		   current_nature_int=Math.min(current_nature_int, max);
		   update_pref_num();
	   }
	   
	   @Override
	   void cycle_back(){
		   if (current_nature_int>min){
			   current_nature_int-=interval;
		   }
		   current_nature_int=Math.max(current_nature_int, min);
		   update_pref_num();
	   }
	   
	   @Override
	   String displayableString(){
		   return current_nature_int+"%";
	   }
	   
	   @Override
	   void update_pref_num(){
			prefs.putFloat(identity, current_nature_int*0.01f);
			prefs.flush();
		}
	   @Override
	   void retrieve_pref_num(){
			current_nature_int=Math.round(prefs.getFloat(identity)*100f);
		}
}