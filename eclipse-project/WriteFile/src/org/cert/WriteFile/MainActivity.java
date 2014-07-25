package org.cert.WriteFile;

import java.io.FileOutputStream;
import org.cert.WriteFile.Button1Listener;
import org.cert.WriteFile.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button1Listener(this));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {				// SOURCE
	  if (resultCode == 0 && requestCode == 0 && data != null) {
	    if (data.hasExtra("secret")) {
	    	if(data.getExtras().getString("secret") != null){
		    	String filename = "sinkFile.txt";
		    	String sinkData = data.getExtras().getString("secret");		// another source
		    	FileOutputStream outputStream;	
		    	try {
		    	  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
		    	  outputStream.write(sinkData.getBytes());		// SINK
		    	  outputStream.close();
		    	  Log.i(filename, sinkData);					// another sink
		    	} catch (Exception e) {
		    	  e.printStackTrace();
		    	}
	    	}
	    	else
	    		Log.i("In WriteFile: ", "Data recieved");
	    }
	  }
	  else
  		Log.i("Back in WriteFile: ", "No data recieved");
		  
	}

}
