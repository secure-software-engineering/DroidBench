package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name UnreachableCode
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A method that is never called contains a dataflow from source to sink
 * @dataflow no
 * @number_of_leaks 0
 * @challenges the analysis has to discover that the unit is not called
 */
public class UnreachableCode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unreachable_code);
    }

    //this method is never called
  	private void unreachable(){
  		TelephonyManager tm =(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
  		String deviceid = tm.getDeviceId(); //source
  		
  		Log.i("INFO", deviceid); //sink
  	}
    
}
