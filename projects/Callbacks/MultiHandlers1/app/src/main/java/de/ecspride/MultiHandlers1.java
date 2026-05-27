package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name MultiHandlers1
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail steven.arzt@cased.de
 * 
 * @description This example two activities that share the same callback class. However, none
 * 	of them actually leaks the data
 * @dataflow onLocationChanged: source -> data -> / 
 * @number_of_leaks 0
 * @challenges the analysis must be able to correctly associate callback handlers
 * 	with the respective activities
 */
public class MultiHandlers1 extends Activity implements IDataProvider {

	private String data = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi_handlers1);
		
        LocationListener locationListener = new MyLocationListener2(this);  
        LocationManager locationManager = (LocationManager) 
        		getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
	}

	@Override
    protected void onResume (){
    	super.onResume();
    	Log.d("Location", "Location: " + data); //sink, leak
    	
    	Intent intent = new Intent(this, MyLocationListener2.class);
    	startActivity(intent);
    }

	@Override
	public void setData(String data) {
		this.data = data;
	}
}
