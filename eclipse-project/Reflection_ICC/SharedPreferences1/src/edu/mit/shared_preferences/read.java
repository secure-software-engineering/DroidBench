package edu.mit.shared_preferences;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class read {
	  public String leak(SharedPreferences at )
    {
    	 
         String imei = at.getString("imei", "");
         Log.i("DroidBench", imei); //sink, leak
		return imei;
         
        
    }
}
