package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;

/**
 * @testcase_name Threading_AsyncTask1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive data is read in onCreate() and send out in a dedicated thread started
 *   using Android's AsyncTask mechanism.
 * @dataflow onCreate: source -> doInBackground() -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to correctly handle Android's AsyncTask mechanism.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TelephonyManager telephonyManager = (TelephonyManager)
				getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		MyAsyncTask async = new MyAsyncTask();
		async.execute(telephonyManager.getDeviceId());
	}
	
	private class MyAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			Log.d("DroidBench", params[0]);
			return "Done";
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
