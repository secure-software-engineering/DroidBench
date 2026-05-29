package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import de.ecspride.applicationlifecycle2.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

}
