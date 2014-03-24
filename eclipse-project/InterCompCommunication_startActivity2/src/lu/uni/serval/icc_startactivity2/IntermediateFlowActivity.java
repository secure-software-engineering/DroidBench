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
		i.setClassName("lu.uni.serval.icc_startactivity2", "lu.uni.serval.icc_startactivity2.InFlowActivity");
		this.startActivity(i);
	}
}
