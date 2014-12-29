package edu.mit.icc_unresolvable_intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import edu.mit.icc_unresolvable_intent.R;

/**
 * @testcase_name ICC-Unresolvable-Intent
 * 
 * @description Testing unresolvable Intent.
 * @dataflow source -> sink
 * @number_of_leaks 1
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
