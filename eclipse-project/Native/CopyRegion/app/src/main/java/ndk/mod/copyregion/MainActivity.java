package ndk.mod.copyregion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;


/**
 * @testcase_name CopyRegion
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Obtains the system property in native code and leaks it in native code using Linux sockets
 * @dataflow source -> imei -> native method -> copy string region ->  return copied string -> leak to toast
 * @number_of_leaks 1
 * @challenges the analysis must handle indirect data dependency and new string of JNI Function
 */
public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("ndkmod");
    }

    // ___________________
    public native String cFuncCopyRegion(String message, int size); // data copy in C code
    // ___________________

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); //source
        String copiedImei = cFuncCopyRegion(imei, imei.length());
        Toast.makeText(MainActivity.this, "Device Info:" + copiedImei, Toast.LENGTH_LONG).show(); //sink
    }
}