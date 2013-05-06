package de.ecspride;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class ActivityLifecycle1 extends Activity {
	
	private static String URL = "http://www.google.de/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lifecycle1);
        
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId();
		URL = URL.concat(imei);
    }

    @Override
    protected void onStart(){
    	super.onStart();
    	try{
    		connect();
    	}catch(Exception ex){
    		//do nothing
    	}
    }

	 private void connect() throws IOException{
    	URL url = new URL(URL);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
    }
    
}
