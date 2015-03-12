package edu.mit.icc_component_not_in_manifest;

import android.app.Activity;
import android.os.Bundle;

public class InFlowInterceptorActivity extends Activity {

	InFlowActivity inflow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflow = new InFlowActivity();
		inflow.setIntent(getIntent());
		inflow.onCreate(savedInstanceState);
	}
}
