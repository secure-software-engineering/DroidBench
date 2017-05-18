package edu.mit.shared_preferences;

import android.content.SharedPreferences;

public class write {
	   public void write_pref(SharedPreferences s,String im)
	    {
	    	SharedPreferences.Editor editor = s.edit();
	    editor.putString("imei", im);
	    editor.commit();
	    	
	    }

}
