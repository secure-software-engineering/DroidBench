package edu.wayne.cs;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ChildClass extends ParentClass {

    @Override
    public String source(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId(); // source
    };

    @Override
    public void sink(String info) {
        Log.d("DroidBench", info);
    }
}
