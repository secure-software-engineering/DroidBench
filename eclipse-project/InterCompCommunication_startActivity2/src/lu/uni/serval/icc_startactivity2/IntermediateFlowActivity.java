package lu.uni.serval.icc_startactivity2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntermediateFlowActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		String imei = i.getStringExtra("DroidBench");
		
		Intent i2 = new Intent(this, lu.uni.serval.icc_startactivity2.InFlowActivity.class);
		i2.putExtra("DroidBench", imei);
		this.startActivity(i2);
	}
}
