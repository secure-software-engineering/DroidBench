package lu.uni.trux.raicc.send;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String imei = this.getIntent().getStringExtra("DroidBench");
        Log.i("DroidBench", imei);
    }
}
