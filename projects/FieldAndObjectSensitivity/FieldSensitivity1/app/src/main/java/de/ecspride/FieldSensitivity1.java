package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
/**
 * @testcase_name FieldSensitivity1
 * @version 0.2
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description An object has two fields, one of them gets a tainted value, the other one is sent to a sink.
 * @dataflow -
 * @number_of_leaks 0
 * @challenges the analysis must be able to distinguish between different fields of an object.
 */
public class FieldSensitivity1 extends Activity {
	private Datacontainer d1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity1);
        
        d1 = setTaint(d1);
		sendTaint();
    }

	private Datacontainer setTaint(Datacontainer data){
		data = new Datacontainer();
		data.setDescription("abc");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		data.setSecret(telephonyManager.getSimSerialNumber()); //source
		return data;
	}
	
	private void sendTaint(){
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, d1.getDescription(), null, null); //sink, no leak
	}
    
}
