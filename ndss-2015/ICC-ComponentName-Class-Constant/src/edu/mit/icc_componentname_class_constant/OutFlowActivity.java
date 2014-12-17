package edu.mit.icc_componentname_class_constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import java.util.Random;

import edu.mit.icc_componentname_class_constant.R;
import android.content.ComponentName;

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
