package de.ecspride;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		
		Set<String> phoneNumbers = new HashSet<String>();
		phoneNumbers.add("+49 123456");
		phoneNumbers.add("+49 654321");
		phoneNumbers.add("+49 111111");
		phoneNumbers.add("+49 222222");
		phoneNumbers.add("+49 333333");
		
		int a = 22 + 11;
		int b = 22 * 2 - 1 + a;
		
		String message = (a == b) ? "no taint" : telephonyManager.getDeviceId();
		
		sendSMS(phoneNumbers, message);		
	}
	
	private void sendSMS(Set<String> numbers, String message){
		SmsManager sm = SmsManager.getDefault();
		
		for(String number : numbers){
			sm.sendTextMessage(number, null, message, null, null);
		}
	}
}
