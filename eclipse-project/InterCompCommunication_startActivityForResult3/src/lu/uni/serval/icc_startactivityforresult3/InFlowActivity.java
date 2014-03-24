package lu.uni.serval.icc_startactivityforresult3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class InFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = getIntent();
		setResult(1, i);
		finish();
	}

}
