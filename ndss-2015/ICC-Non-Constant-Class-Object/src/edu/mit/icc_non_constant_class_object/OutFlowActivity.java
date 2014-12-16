package edu.mit.icc_non_constant_class_object;

import lu.uni.serval.icc_startactivity1.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

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
