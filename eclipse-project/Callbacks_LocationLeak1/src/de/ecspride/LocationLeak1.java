package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Location Information Leakage
 * 
 * This example contains a location information leakage in the onResume() callback method.
 * The data source is placed into the onLocationChanged() callback method, especially the parameter "loc".
 * 
 * Difficulty:
 * 	- Correct emulation of the Android activity lifecycle
 * 		- Correct integration of the callback method onLocationChanged
 * 	- Detection of callback methods as source
 * 
 * @author Siegfried Rasthofer
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
    	
    	Log.d("Latitude", "Latitude: " + latitude);
    	Log.d("Longtitude", "Longtitude: " + longtitude);
    	Toast.makeText(getApplicationContext(), "Latitude: " + latitude, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), "Longtitude: " + longtitude, Toast.LENGTH_LONG).show();
    }
    
    private class MyLocationListener implements LocationListener {  
		  @Override  
		  public void onLocationChanged(Location loc) {  
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
