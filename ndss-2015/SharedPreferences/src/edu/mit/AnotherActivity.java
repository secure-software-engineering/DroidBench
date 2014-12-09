package edu.mit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.content.SharedPreferences;


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
