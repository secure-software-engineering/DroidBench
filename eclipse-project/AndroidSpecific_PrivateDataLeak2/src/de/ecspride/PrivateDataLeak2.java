package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class PrivateDataLeak2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_data_leak2);
        
        EditText mEdit   = (EditText)findViewById(R.id.pwField);
		Log.v("Password", mEdit.getText().toString());
    }
}
