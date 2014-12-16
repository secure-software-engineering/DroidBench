package edu.mit.icc_concat_action_string;

import java.util.List;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import lu.uni.serval.icc_startactivity4.R;

public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		Intent i = new Intent("lu.uni.serval.icc_startactivity4" + ".ACTION");
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
