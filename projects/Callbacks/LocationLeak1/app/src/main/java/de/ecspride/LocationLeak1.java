package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name LocationLeak1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description This example contains a location information leakage in the onResume() callback method.
 *  The data source is placed into the onLocationChanged() callback method, especially the parameter "loc".
 * @dataflow onLocationChanged: source -> latitude, longtitude; onResume: latitude -> sink, longtitude -> sink 
 * @number_of_leaks 2
 * @challenges the analysis must be able to emulate the Android activity lifecycle correctly,
 *  integrate the callback method onLocationChanged and detect the callback methods as source.
 */
public class LocationLeak1 extends Activity {
	private String latitude = "";
	private String longtitude = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_leak1);
        
        LocationManager locationManager = (LocationManager) 
        getSystemService(Context.LOCATION_SERVICE);
        
        LocationListener locationListener = new MyLocationListener();  
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);            
    }

    @Override
    protected void onResume (){
    	super.onResume();
    	
    	Log.d("Latitude", "Latitude: " + latitude); //sink, leak
    	Log.d("Longtitude", "Longtitude: " + longtitude); //sink, leak
    }
    
    private class MyLocationListener implements LocationListener {  
		  @Override  
		  public void onLocationChanged(Location loc) {  //source
			  double lat = loc.getLatitude();
				double lon = loc.getLongitude();
				
				latitude =  Double.toString(lat);
				longtitude = Double.toString(lon);
		  }  

		  @Override  
		  public void onProviderDisabled(String provider) {}  

		  @Override  
		  public void onProviderEnabled(String provider) { }  

		  @Override  
		  public void onStatusChanged(String provider, int status, Bundle extras) {}  
    }
}
