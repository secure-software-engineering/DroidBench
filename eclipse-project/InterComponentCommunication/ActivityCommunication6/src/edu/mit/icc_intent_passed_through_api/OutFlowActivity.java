package edu.mit.icc_intent_passed_through_api;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Intent-Passed-Through-API
 * 
 * @description   Testing an Intent passed through a linked-list then used to start an Activity 
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges  The analysis tool has to be able to track an Intent through a list operation 
 */
public class OutFlowActivity extends Activity {


    @Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
		
	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
	String imei = telephonyManager.getDeviceId(); //source
		
	Intent i = new Intent(this, InFlowActivity.class);
	i.putExtra("DroidBench", imei);

	List<Intent> iList = new LinkedList<Intent>();
	iList.add(i);

	Intent i2 = iList.get(0);
		
	startActivity(i2);
    }
}
