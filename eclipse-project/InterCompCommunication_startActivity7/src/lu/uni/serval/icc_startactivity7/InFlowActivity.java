package lu.uni.serval.icc_startactivity7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Droid");
		sb.append("Bench");
		sb.append("2");
		
		String imei = i.getStringExtra(sb.toString());
		Log.i("DroidBench", imei);
	}

}
