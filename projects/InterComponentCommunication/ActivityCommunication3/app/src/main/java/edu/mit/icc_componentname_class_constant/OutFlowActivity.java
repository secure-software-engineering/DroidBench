package edu.mit.icc_componentname_class_constant;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ICC-ComponentName-Class-Constant
 * 
 * @description Testing the component resolution throught Activity's classname
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to resolve component from a name and track it through startActivity
 */
public class OutFlowActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); //source
	
        ComponentName comp = new ComponentName(getPackageName(), InFlowActivity.class.getName());
        Intent i = new Intent().setComponent(comp);
        i.putExtra("DroidBench", imei);
	
        startActivity(i);
    }
}
