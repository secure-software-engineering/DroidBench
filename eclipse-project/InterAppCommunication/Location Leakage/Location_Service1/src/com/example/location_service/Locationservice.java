package com.example.location_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Locationservice extends Service implements LocationListener
{
	
	protected LocationManager locationManager;
    protected LocationListener locationListener;
    String loc = " Location: ";
    
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
    	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }
        
    @Override
    public void onLocationChanged(Location location)
    {
        loc = loc.concat("Latitude:");
        loc = loc.concat(Double.toString(location.getLatitude()));
        loc = loc.concat(", Longitude:");
        loc = loc.concat(Double.toString(location.getLongitude()));
    }

    @Override
    public void onDestroy() {
        Intent in = new Intent("com.example.collector");
        in.putExtra(Intent.EXTRA_TEXT, loc);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.setType("text/plain");
        startActivity(in);
        
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }

    class MyBinder extends Binder{
        Locationservice getService(){
            return Locationservice.this;
        }
    }

	

	

}
