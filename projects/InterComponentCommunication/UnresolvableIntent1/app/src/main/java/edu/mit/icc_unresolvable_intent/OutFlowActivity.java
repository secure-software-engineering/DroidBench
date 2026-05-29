package edu.mit.icc_unresolvable_intent;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-Unresolvable-Intent
 * 
 * @description An intent is created with a random selection of 2 constant strings to start an Activity.
 * @dataflow source -> sink
 * @number_of_leaks 2 
 * @challenges   The analysis tool has to be able to identify unresolvable Intent and not associate the unresolved Intent with any Activity 
 */
public class OutFlowActivity extends Activity {

    private static Random rnd = new Random(System.currentTimeMillis());

    String randomString() {
        if (rnd.nextBoolean())
            return "edu.mit.icc_unresolvable_intent.ACTION"; 
        else
            return "edu.mit.icc_unresolvable_intent.EDIT";
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		Intent i = new Intent(randomString());
		i.putExtra("DroidBench", imei);
		
		startActivity(i);
	}

}
