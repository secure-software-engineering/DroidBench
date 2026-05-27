package edu.mit.icc_action_string_operations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Action-String-Operations
 * 
 * @description  Testing substring operation and explicit intent resolution
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to evaluate substring operation and track tainted value through another Activity.
 */
public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		Intent i = new Intent("ignore.edu.mit.icc_action_string_operations.ACTION".substring(7));
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
