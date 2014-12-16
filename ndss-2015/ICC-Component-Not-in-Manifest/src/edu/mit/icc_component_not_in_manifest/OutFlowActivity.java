package edu.mit.icc_component_not_in_manifest;

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
		
		Intent i = new Intent(this, InFlowActivity.class);
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
