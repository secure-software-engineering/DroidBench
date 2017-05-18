package droidbench.sit.fraunhofer.de.simpleunreachable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name SimpleUnreachable1
 * @author Fraunhofer SIT
 * @author_mail Steven.Arzt@sit.fraunhofer.de
 *
 * @description Both source and sink are unreachable due to a simple constraint.
 * @dataflow
 * @number_of_leaks 0
 * @challenges The analysis should detect that some branches are unreachable.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int x = 4 * 20 + foo();
        if (x < 10) {
            TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            String id = tm.getDeviceId(); // unreachable source
            Log.d("DroidBench", id); // unreachable sink
        }
    }

    protected int foo() {
        return 42;
    }
}
