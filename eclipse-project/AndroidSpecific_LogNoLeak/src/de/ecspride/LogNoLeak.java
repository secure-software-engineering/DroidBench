package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LogNoLeak extends Activity {
	private String notTainted = "";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_no_leak);
        
        notTainted = "not tainted";
    } 
    
	public void onPause(){
		Log.i("TAG", notTainted);
	}
}
