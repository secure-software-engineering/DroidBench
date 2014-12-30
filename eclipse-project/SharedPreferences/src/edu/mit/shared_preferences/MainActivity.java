package edu.mit.shared_preferences;

import edu.mit.shared_preferences.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.content.SharedPreferences;

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
