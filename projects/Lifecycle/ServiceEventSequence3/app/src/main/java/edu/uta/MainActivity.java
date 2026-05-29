package edu.uta;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import edu.uta.ServiceEventSequence3.LocalBinder;

/**
 * @testcase_name Lifecycle_ServiceEventSequence3
 * 
 * @description   Testing if information leak can be detected which occurs through possible flows between the service callbacks.  
 * @dataflow onCreate() :source -> onBind -> onUnbind: sink. Buttons should be clicked in the given order to launch the attack: bind > unbind
 * @number_of_leaks 1
 * @challenges  The analysis tool must be able to analyze all possible flows from onUnbind() callback, that is, onBind() and onRebind().  
 * 
 */

public class MainActivity extends Activity {
    ServiceEventSequence3 mService;
    boolean mBound = false;
    boolean unBindReturnedTrue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          Log.d("BindingActivity", "onCRRRRREATTEEEeeEE");

    }
    
    public void onButtonClicked(View v)
    {
    	Intent intent = new Intent(this, ServiceEventSequence3.class);
    	if(v.getId() == R.id.unbind)
    	{
    		if(mBound)
    		{
    			unbindService(mConnection);
    			mBound = false;
    		}
    	}
    	else if(v.getId() == R.id.bind)
    	{
    		if(!mBound)
    		{
		     	bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
		     	mBound = true;
    		}
    	}
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