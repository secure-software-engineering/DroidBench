package edu.mit.shared_preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name SharedPreferences
 * 
 * @description Test modeling of SharedPreferences
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - Modeling of SharedPreferences
 */
public class MainActivity extends Activity {    
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();

        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imei", imei);
        
        // Commit the edits!
        editor.commit();
    }
        
}
