package edu.mit.icc_component_not_in_manifest;

import edu.mit.icc_component_not_in_manifest.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Component-Not-in-Manifest
 * 
 * @description Testing if Activity not in the Manifest is also analyzed.
 * @dataflow 
 * @number_of_leaks 0 
 * @challenges The analysis must recognize that activity is not startable if it is not in the AndroidManifest.xml 
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
		
		startActivity(i);
	}

}
