package edu.uta;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.newservice.R;

import edu.uta.ServiceEventSequence2.LocalBinder;

/**
 * @testcase_name Lifecycle_ServiceEventSequence2
 * 
 * @description   Testing if information leak can be detected which occurs through possible flows between the service callbacks.  
 * @dataflow onStartCommand :source -> onBind -> onUnbind -> onBind: sink
 * @number_of_leaks 1
 * @challenges  The analysis tool must be able to analyze all possible flows from onUnbind() callback, that is, onBind() and onRebind().  
 * 
 */

public class MainActivity extends Activity {
    ServiceEventSequence2 mService;
    boolean mBound = false;
    boolean unBindReturnedTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          Log.d("BindingActivity", "onCRRRRREATTEEEeeEE");

	      Intent intent = new Intent(this, ServiceEventSequence2.class);
	      getApplicationContext().startService(intent);
	      
	      getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

	      unbindService(mConnection);
	      
	      getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
  
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
             mBound = false;
        }
    };
    
}