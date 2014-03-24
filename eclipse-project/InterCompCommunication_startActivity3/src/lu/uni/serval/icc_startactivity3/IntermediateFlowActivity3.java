package lu.uni.serval.icc_startactivity3;

import lu.uni.serval.icc_startactivity3.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntermediateFlowActivity3 extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		String deviceid = i.getStringExtra("DroidBench");
		
		i = new Intent(this, InFlowActivity.class);
		i.putExtra("DroidBench", deviceid);

		this.startActivity(i);
	}
}
