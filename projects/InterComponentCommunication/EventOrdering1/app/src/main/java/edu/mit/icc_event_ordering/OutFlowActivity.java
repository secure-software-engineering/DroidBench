package edu.mit.icc_event_ordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * @testcase_name ICC-Event-Ordering
 * 
 * @description   Testing if information leak due to repeating of the same event squence multiple times can be detected 
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges  The analysis tool has to be able to take into account different runs of the app.  In this case, the end of one run is the source and the benning of the next run is the sink. 
 */
public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = new Intent("edu.mit.icc_event_ordering.ACTION");

		startActivity(i);
	}

}
