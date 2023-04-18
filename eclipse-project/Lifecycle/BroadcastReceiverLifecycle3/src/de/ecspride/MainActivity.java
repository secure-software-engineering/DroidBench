package de.ecspride;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;

/**
 * @testcase_name BroadcastReceiverLifecycle3
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail Steven.Arzt@cased.de
 * 
 * @description The sensitive data is read and leaked in a dynamically registered broadcast receiver.
 * @dataflow source -> imei -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle the dynamic registration of broadcast
 *   receivers
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("de.ecspride.MyAction");
		
		registerReceiver(new MyReceiver(), filter);
		
		Intent intent = new Intent();
		intent.setAction("de.ecspride.MyAction");
		sendBroadcast(intent);
	}
	
	private class MyReceiver extends BroadcastReceiver {
		
		public MyReceiver() {
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			String imei = ((TelephonyManager) getApplicationContext().getSystemService
					(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
			Log.d("DroidBench", imei);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
