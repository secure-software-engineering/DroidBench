package lu.uni.serval.icc_startactivityforresult2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
 * This class is used to make sure that privacy detecting tools can distinguish the different components.
 */
public class IsolateActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		this.setResult(1, i);
		finish();
	}
	
}
