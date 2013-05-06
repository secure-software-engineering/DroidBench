package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ObjectSensitivity2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description A tainted value from a source is written to a local variable and a field. Both are overwritten before 
 *  they are passed to a sink
 * @dataflow
 * @number_of_leaks 0
 * @challenges the analysis must be able to remove taints from variables and fields
 */
public class OverwiteValue extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overwite_value);
        
        String var;
		DataStore ds = new DataStore();
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String taintedString =  telephonyManager.getDeviceId(); //source
		
		var = taintedString;
		ds.field = taintedString;
		
		var = "abc";
		ds.field = "def";
				
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49", null, var, null, null);  //sink, no leak
        sms.sendTextMessage("+49", null, ds.field, null, null);  //sink, no leak
    }    
}
