package edu.mit.icc_broadcast_programmatic_intentfilter;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.CheckBox;
import android.os.Vibrator;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Context;
import android.util.Log;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class BroadcastTest extends Activity {
    private static String ACTION = "action";

    public void onCreate(Bundle bundle) {
        BroadcastReceiver receiver = new BroadcastReceiver(){
                public void onReceive(Context c, Intent i) {
                    String taint = i.getStringExtra("imei");
		    
                    Log.i("DroidBench", taint);
                }				  
            };

        this.registerReceiver(receiver, new IntentFilter(ACTION));
    }

    public void onDestroy() {
        //this is tainted!!!
	TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	String imei = mgr.getDeviceId();

        Intent intent = new Intent(ACTION);
        intent.putExtra("imei", imei);

        sendBroadcast(intent);
    }
}
