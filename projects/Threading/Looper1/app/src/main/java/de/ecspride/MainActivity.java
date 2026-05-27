package de.ecspride;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * @testcase_name Threading_Looper1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description Sensitive Sensitive data is read in onCreate() and enqueued for
 * 		a custom thread hosting an Android Looper whose handler sends out the data.
 * @dataflow onCreate: source -> Handler.dispatchMessage() -> Looper -> Handler.handleMessage() -> sink
 * @number_of_leaks 1
 * @challenges The analysis must be able to correctly handle Android's Looper infrastructure.
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LooperThread lpt = new LooperThread();
		lpt.start();
		
		while (!lpt.ready)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		TelephonyManager telephonyManager = (TelephonyManager)
				getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = telephonyManager.getDeviceId(); //  source

		Message msg = new Message();
		msg.obj = deviceId;
		LooperThread.handler.dispatchMessage(msg);
	}

}
