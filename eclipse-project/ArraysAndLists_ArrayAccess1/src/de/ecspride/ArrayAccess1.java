package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ArrayAccess1 extends Activity {
	public static String[] arrayData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_access1);
        
        arrayData = new String[3];
		
		arrayData[0] = "element 1 is tainted:";
		arrayData[1] = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		//arrayData[2] is not tainted
		arrayData[2] = "neutral text";
		
		SmsManager sms = SmsManager.getDefault();
		
		//no data leak: 3rd argument of sendTextmessage() is not tainted
        sms.sendTextMessage("+49 1234", null, arrayData[2], null, null);  
    }    
}
