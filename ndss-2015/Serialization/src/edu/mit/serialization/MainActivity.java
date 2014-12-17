package edu.mit.serialization;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.mit.serialization.R;


/**
 * @testcase_name Serialization
 * 
 * @description Test serialization end to end flow.
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges - must model serialization
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
        S s1 = new S(imei);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(s1);
            oos.close();
        
            byte[] bytes = out.toByteArray(); 
        
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream iis = new ObjectInputStream(in);
            S s2 = (S)iis.readObject();
            iis.close();
        
            Log.i("DroidBench", s2.toString());
        } catch (Exception e) {
        }
    }
}

class S implements Serializable {
	
    private static final long serialVersionUID = -1155152173616606359L;

    private String message;
	
    public S(String message) {
        this.message = message;
    }
		
    public String toString() {
        return message;
    }
}
