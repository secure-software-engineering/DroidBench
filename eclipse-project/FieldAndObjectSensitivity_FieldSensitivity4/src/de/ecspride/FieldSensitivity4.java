package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class FieldSensitivity4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity4);
        
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		
		Datacontainer data1 = new Datacontainer();
		
		SmsManager sms = SmsManager.getDefault();
	    sms.sendTextMessage("+49 1234", null, data1.value, null, null);
	    
	    data1.value = imei;
    }

    class Datacontainer{
		String value = "android";	
	}
    
}
