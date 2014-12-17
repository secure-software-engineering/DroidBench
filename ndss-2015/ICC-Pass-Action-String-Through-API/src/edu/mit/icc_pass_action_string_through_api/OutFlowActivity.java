package edu.mit.icc_pass_action_string_through_api;

import java.util.List;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import edu.mit.icc_pass_action_string_through_api.R;

public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		List<String> list = new LinkedList<String>();
		list.add("lu.uni.serval.icc_startactivity4.ACTION");

		String action = list.get(0);

		Intent i = new Intent(action);
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
