package org.cert.echoer;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

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
			i = getIntent();
			String action = i.getAction();
			if (action.equals(Intent.ACTION_SEND)) {
				Bundle extras = i.getExtras();
				Log.i("Data recieved in Echoer: ", extras.getString("secret"));
			}
			else if (action.equals(Intent.ACTION_VIEW)){
			    Uri uri = i.getData();
			    Log.i("URI recieved in Echoer: ", uri.toString());	
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
