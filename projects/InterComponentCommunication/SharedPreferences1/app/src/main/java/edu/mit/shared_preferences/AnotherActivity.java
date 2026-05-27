package edu.mit.shared_preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class AnotherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore preferences
       SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);
       String imei = settings.getString("imei", "");
       Log.i("DroidBench", imei); //sink, leak
    }
}
