package de.ecspride;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ObjectSensitivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_sensitivity1);
        
        LinkedList<String> list1 = new LinkedList<String>();
		LinkedList<String> list2 = new LinkedList<String>();
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		list1.add(telephonyManager.getSimSerialNumber());
		list2.add("123");
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, list2.get(0), null, null); 
    }    
}
