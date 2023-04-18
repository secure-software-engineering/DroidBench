package com.example.deviceid_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Service_deviceid extends Service
{

	public final IBinder bind = new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
       return bind;
    }
    @Override
    public void onCreate() {
        Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
    	String s = "Device Id : ";
       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
    	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        s = s.concat(telephonyManager.getDeviceId());
        final String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        
        Intent in = new Intent("com.example.collector");
        in.setType("text/plain");
        in.putExtra(Intent.EXTRA_TEXT,s);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(in);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    class MyBinder extends Binder{
        Service_deviceid getService(){
            return Service_deviceid.this;
        }
    }

	

	

}
