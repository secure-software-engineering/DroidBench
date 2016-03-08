package com.example.deviceid_orderedintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Receiver3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String s = intent.getExtras().getString("data");
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        s = s.concat(telephonyManager.getDeviceId());
        Toast.makeText(context, "RCVR 3.." + s, Toast.LENGTH_LONG).show();
        setResultData(s);
    }
}