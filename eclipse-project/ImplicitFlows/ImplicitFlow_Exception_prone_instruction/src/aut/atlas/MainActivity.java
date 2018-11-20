package aut.atlas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
/**
 * @testcase_name ImplicitFlow_ExceptionProneInstruction
 * @version 0.1
 * @author_mail z.bohluli@aut.ac.ir
 * @description IMEI value and its digits are written to Log through division by zero exception
 * @dataflow source -> Exception-prone instruction -> sink
 * @number_of_leaks 2
 * @challenges the analysis must be able to handle implicit flows induced by exception-prone instructions
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        String lowIMEI = new String();
        for (char c : imei.toCharArray()){
            int high = ((int)c) - 48 ;
            for (int low=0; low<=9; low++){
                try {
                    int tmp = 1 / (high - low);
                } catch (Exception e){
                    lowIMEI += (char) (low + 48);
                    Log.i("info", "An IMEI digit found " + low);
                }
            }
        }
        Log.i("info", "IMEI found = " + lowIMEI);
    }
    
}
