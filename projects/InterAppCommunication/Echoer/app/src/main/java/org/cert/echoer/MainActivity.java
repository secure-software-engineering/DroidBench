package org.cert.echoer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

/**
 * @testcase_name Echoer
 * @version 0.1
 * 
 * @description Receives data and echoes it back to the sender.
 * @dataflow getExtras -> Log, getData -> Log, getIntent() -> setResult
 * @number_of_leaks 3
 * @challenges the sensitive data comes from outside the application using getIntent() on MainActivity.
 *
 */
public class MainActivity extends Activity {

	Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button1Listener(this));
		getDataFromIntent();
	}
	
	protected void onResume()
	{
		super.onResume();
		getDataFromIntent();
	}

	private void getDataFromIntent(){
		try {
			i = getIntent(); // source
			String action = i.getAction();
			if (action.equals(Intent.ACTION_SEND)) {
				Bundle extras = i.getExtras(); // source
				Log.i("TAG", "Data received in Echoer: " + extras.getString("secret")); // sink
			}
			else if (action.equals(Intent.ACTION_VIEW)){
			    Uri uri = i.getData(); // source
			    Log.i("TAG", "URI received in Echoer: " + uri.toString());// sink
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
