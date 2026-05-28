package benchmark.infoflow.javatojava;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name JavaToJava
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Information leak from java to java code.
 * @dataflow source -> imei -> leak to info log
 * @number_of_leaks 1
 * @challenges the analysis handle explicit data leak in java to java.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); //source
        leaker(imei);
    }
    public void leaker(String info){
        Log.i("InfoFlows", info); //sink
    }
}