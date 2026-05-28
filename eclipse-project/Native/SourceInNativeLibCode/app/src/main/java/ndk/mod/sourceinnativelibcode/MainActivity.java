package ndk.mod.sourceinnativelibcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @testcase_name SourceInNativeLibCode
 * @version 0.1
 * @author Cyber Security Research Center (CSEC), in Soongsil Univerty (SSU)
 * @author_mail gigacms@gmail.com
 *
 * @description Obtains the system property in native code and leaks it in native code using Linux sockets
 * @dataflow source -> system property get -> write -> leak in native
 * @number_of_leaks 1
 * @challenges the analysis must handle native source and sink invocations and capture system-level sinks
 */
public class MainActivity extends Activity {
    static {
        System.loadLibrary("ndkmod");
    }

    // ___________________
    public native boolean cFuncVerifyDevice(); // Source in C code
    // ___________________

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean isVerified = cFuncVerifyDevice();

                if(isVerified){
                    Toast.makeText(MainActivity.this, "Success! Verified device.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed! Unverified device", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}