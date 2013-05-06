package de.ecspride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;

public class IntentSink2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sink2);
    }

	public void startIntent(View view){
		EditText appName = (EditText) findViewById(R.id.name);
		
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        
        Intent intent=new Intent();
		intent.setClassName(this,appName.getText().toString());
		intent.putExtra("id",deviceId);
		startActivity(intent);
	}
    
}
