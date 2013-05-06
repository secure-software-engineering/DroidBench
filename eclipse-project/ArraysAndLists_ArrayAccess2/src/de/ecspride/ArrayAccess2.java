package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ArrayAccess2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_access2);
        
        String[] array = new String[10];
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		array[5] = telephonyManager.getDeviceId();
		array[4] = "no taint";
		
		SmsManager sm = SmsManager.getDefault();
		sm.sendTextMessage("+49 1234", null, array[calculateIndex()], null, null);
		
	}
	
	private int calculateIndex(){
		int index = 1;
		index++;
		index *= 5;
		index = index%10;
		index += 4;
		
		return index;
	}
}
