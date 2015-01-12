package org.cert.WriteFile;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Button1Listener implements OnClickListener {

	private final MainActivity act;
	
	public Button1Listener(MainActivity parentActivity) {
		this.act = parentActivity;
	}

	@Override
	public void onClick(View arg0) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");

		String curLoc = getMyLocation();
		i.putExtra("secret", curLoc);
		Log.i("Phase2_3: ", "Sending implicit Intent with MIME data type text/plain: Current Location "+ curLoc);
		this.act.startActivityForResult(i, 0);			// SINK
	}
	
	private String getMyLocation(){
		LocationManager lm = (LocationManager) this.act.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);	// SOURCE
		
		try {
			double longitude = location.getLongitude();
			double latitude = location.getLatitude();
			String curLonStr = String.valueOf(longitude);
			String curLatStr = String.valueOf(latitude);
			return "Longitude: "+curLonStr+", Latitude: "+curLatStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "Couldn't get last known location info";
		}		
	}
}
