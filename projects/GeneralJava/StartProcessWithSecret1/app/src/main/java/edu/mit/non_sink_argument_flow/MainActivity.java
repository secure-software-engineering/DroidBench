package edu.mit.non_sink_argument_flow;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

/**
 * @testcase_name Non-Sink-Argument-Flow
 * 
 * @description Flow to sink is through memory reachable from receiver, but not through argument
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to track taint flown to sink through a receiver
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();  //source
        
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("cmd", imei);
            pb.start();  //leak (assuming cmd is a leak)
        } catch (Exception e) {
            
        }
    }
}
