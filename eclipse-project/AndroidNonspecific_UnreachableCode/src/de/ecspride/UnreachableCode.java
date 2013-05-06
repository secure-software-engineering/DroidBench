package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class UnreachableCode extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unreachable_code);
    }

    //this method is never called
  	private void unrechable(){
  		TelephonyManager tm =(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
  		String deviceid = tm.getDeviceId();
  		
  		Log.i("INFO", deviceid);
  	}
    
}
