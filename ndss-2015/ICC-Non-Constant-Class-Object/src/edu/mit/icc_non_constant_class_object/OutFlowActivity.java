package edu.mit.icc_non_constant_class_object;

import edu.mit.icc_non_constant_class_object.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Non-Constant-Class-Object 
 * 
 * @description   Testing Intent resolution on a non-constant Activity.getClass() 
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to resolve an Intent of a non-constant Activity class 
 */
public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		InFlowActivity act = new InFlowActivity();

		Intent i = new Intent(this, act.getClass());
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
