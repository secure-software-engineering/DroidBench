package edu.mit.icc_unresolvable_intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import lu.uni.serval.icc_startactivity4.R;

public class OutFlowActivity extends Activity {

    private static final String AB = ".abcdefghijklmnopqrstuvwxyz";
    private static Random rnd = new Random();

    String randomString( int len ) {
	StringBuilder sb = new StringBuilder( len );
	for( int i = 0; i < len; i++ ) 
	    sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	return sb.toString();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		Intent i = new Intent(randomString(20));
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
