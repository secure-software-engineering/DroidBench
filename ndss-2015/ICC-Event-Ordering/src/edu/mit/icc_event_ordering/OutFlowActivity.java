package edu.mit.icc_event_ordering;

import java.util.List;
import java.util.LinkedList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Random;

import edu.mit.icc_event_ordering.R;

public class OutFlowActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent i = new Intent("edu.mit.icc_event_ordering.ACTION");

		startActivity(i);
	}

}
