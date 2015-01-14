package edu.mit.application_modeling;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AnotherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DroidBench", ((MyApplication)getApplication()).imei); //sink
    }
}
