package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class OverwiteValue extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overwite_value);
        
        String var;
		DataStore ds = new DataStore();
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String taintedString =  telephonyManager.getDeviceId(); 
		
		var = taintedString;
		ds.field = taintedString;
		
		var = "abc";
		ds.field = "def";
				
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49", null, var, null, null); 
        sms.sendTextMessage("+49", null, ds.field, null, null); 
    }    
}
