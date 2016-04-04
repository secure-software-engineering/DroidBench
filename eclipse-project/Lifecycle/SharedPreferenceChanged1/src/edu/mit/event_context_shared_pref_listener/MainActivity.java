package edu.mit.event_context_shared_pref_listener;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Event-Context-Shared-Pref-Listener
 * 
 * @description Test that an event from the runtime is called with the appropriate context (argument)
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - In this case, the change listener has to be called with the shared preferences 
 * that are changed.
 */
public class MainActivity extends Activity implements SharedPreferences.OnSharedPreferenceChangeListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();

        
        SharedPreferences settings = getSharedPreferences("settings", 0);
        settings.registerOnSharedPreferenceChangeListener(this);
        
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imei", imei);
        editor.commit();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String imei = sharedPreferences.getString(key, "");
        Log.i("DroidBench", imei);
    }
}
