package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name ArrayAccess3
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail Steven.Arzt@cased.de
 * 
 * @description Sensitive data is written into an array, read back again, and leaked.
 * @dataflow source -> array -> sink
 * @number_of_leaks 1
 * @challenges The analysis must support arrays 
 */
public class ArrayAccess3 extends Activity {
	public static String[] arrayData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_access1);
        
        arrayData = new String[3];
		
		arrayData[0] = "element 1 is tainted:";
		arrayData[1] = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId(); //source
		//arrayData[2] is not tainted
		arrayData[2] = "neutral text";
		
		SmsManager sms = SmsManager.getDefault();
		
        sms.sendTextMessage("+49 1234", null, arrayData[1], null, null);  //sink, leak
    }    
}
