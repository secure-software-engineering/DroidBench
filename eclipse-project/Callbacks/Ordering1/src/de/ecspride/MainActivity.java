package de.ecspride;

import com.example.callbacks_ordering1.R;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

/**
 * @testcase_name Callbacks_Ordering1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description This example leaks variable contents before the handler initializing them is
 * 	even registered
 * @dataflow onLocationChanged: source -> latitude, longtitude; onCreate: latitude -> sink, longtitude -> sink 
 * @number_of_leaks 0
 * @challenges The analysis must take the order of callback registration and sink call
 * 	into account.
 */
public class MainActivity extends Activity {

	String latitude = "";
	String longtitude = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("Latitude", "Latitude: " + latitude); //sink, leak
    	Log.d("Longtitude", "Longtitude: " + longtitude); //sink, leak
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		LocationListener locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				double lat = location.getLatitude();
				double lon = location.getLongitude();
					
				latitude =  Double.toString(lat);
				longtitude = Double.toString(lon);
			}
		}; 
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

        super.onDestroy();
	}

}
