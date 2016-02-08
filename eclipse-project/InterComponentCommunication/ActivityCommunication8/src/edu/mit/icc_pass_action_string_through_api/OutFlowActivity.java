package edu.mit.icc_pass_action_string_through_api;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Pass-Action-String-Through-API 
 * 
 * @description  Testing Intent resolution of a string that has been passed through a list
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to track a string through the list, then resolve Intent's action and follow the Intent to the Activity to be activated
 */
public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		List<String> list = new LinkedList<String>();
		list.add("edu.mit.icc_action_string_operations.ACTION");

		String action = list.get(0);

		Intent i = new Intent(action);
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
