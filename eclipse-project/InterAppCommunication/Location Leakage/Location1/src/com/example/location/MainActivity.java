package com.example.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name Location1
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description This app obtains the location data and sends it to the Collector
 * app.
 * @dataflow location (longitude+latitude) -> Collector app 
 * @number_of_leaks 1
 * @challenges The analysis must correctly handle inter-app communication
 * through intents 
 */
public class MainActivity extends Activity implements LocationListener,View.OnClickListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    String loc = " Location: ";
    Button getLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoc = (Button)findViewById(R.id.button1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        getLoc.setOnClickListener(this);



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
    public void onClick(View v)
    {
        Intent in = new Intent("com.example.collector");
        in.putExtra(Intent.EXTRA_TEXT, loc);
        in.setType("text/plain");
        startActivity(in);
        //Toast.makeText(location.this, loc, Toast.LENGTH_SHORT).show();
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
}
