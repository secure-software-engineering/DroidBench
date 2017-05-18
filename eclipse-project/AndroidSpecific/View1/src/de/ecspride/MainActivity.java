package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
/**
 * @testcase_name View1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail Steven.Arzt@cased.de
 * 
 * @description The device ID is sent via SMS in a custom view.
 * @dataflow source -> deviceID -> custom view (static field) -> sink
 * @number_of_leaks 1
 * @challenges The analysis needs to properly include custom views into the lifecycle
 */
public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_date_leakage);
        
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        MyView.deviceID = mgr.getDeviceId();
    }
   
}
