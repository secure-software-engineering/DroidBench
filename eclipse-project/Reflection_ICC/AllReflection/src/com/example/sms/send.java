package com.example.sms;

import android.telephony.SmsManager;

public class send {
	
	
	public void se(String te,String ph )
	{
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(te, null, ph,null,null);
		
	}

}
