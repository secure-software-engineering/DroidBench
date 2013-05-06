package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class AnnonymousClass1 extends Activity {
	
	private static double latitude;
	private static double longitude;
	private LocationManager locationManager;

	LocationListener locationListner = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {		    
		}
   
		@Override
		public void onProviderEnabled(String provider) {
		}
   
		@Override
		public void onProviderDisabled(String provider) {
		}
   
		@Override
		public void onLocationChanged(Location location) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();		    
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonymous_class1);
        
        // Acquire a reference to the system Location Manager
 		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       
 		// Register the listener with the Location Manager to receive location updates
 		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListner);		 
    }  
    
    @Override
	protected void onResume(){
		super.onResume();
		Log.i("TAG", "Latitude: " + latitude + "Longtitude: " + longitude);
	}
}
