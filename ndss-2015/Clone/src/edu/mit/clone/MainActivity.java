package edu.mit.clone;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.List;
import java.util.LinkedList;

import edu.mit.clone.R;

/**
 * @testcase_name Clone
 * 
 * @description Easy testcase: Clone
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - must model clone
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
        LinkedList<String> list = new LinkedList<String>();
        list.add(imei);

        LinkedList<String> list2 = (LinkedList<String>)list.clone();

        Log.i("DroidBench", list2.get(0));
    }
}
