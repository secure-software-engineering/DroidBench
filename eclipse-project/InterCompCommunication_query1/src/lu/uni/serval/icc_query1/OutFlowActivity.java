package lu.uni.serval.icc_query1;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class OutFlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String URL = "content://lu.uni.serval.icc_query1/deviceid";
		Uri uri = Uri.parse(URL);
		
		Cursor c = getContentResolver().query(uri, null, null, null, null);
		c.moveToFirst();
		Log.i("DroidBench", c.getString(0));
	}

}
