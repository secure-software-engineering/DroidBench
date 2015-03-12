package edu.mit.clone;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name Clone
 * 
 * @description Tesging LinkedList.clone
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - must model clone of list
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
