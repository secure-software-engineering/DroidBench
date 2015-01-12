package de.ecspride;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener2 implements LocationListener {

	private IDataProvider dataProvider;
	
	public MyLocationListener2(IDataProvider provider) {
		this.dataProvider = provider;
	}
	
	@Override
	public void onLocationChanged(Location arg0) {
		dataProvider.setData("");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
