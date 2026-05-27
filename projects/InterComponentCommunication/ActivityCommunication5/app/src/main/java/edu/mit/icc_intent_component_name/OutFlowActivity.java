package edu.mit.icc_intent_component_name;

import edu.mit.icc_intent_component_name.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.content.ComponentName;

/**
 * @testcase_name ICC-Intent-Component-Name 
 * 
 * @description   Testing the intent resolution of component name 
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges The analysis tool must be able to resolve Intent's component from a component name and follow the taint to another Activity. 
 */
public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source

		ComponentName cn = new ComponentName(this, "edu.mit.icc_intent_component_name.InFlowActivity");
		
		Intent i = new Intent();
		i.setComponent(cn);
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
