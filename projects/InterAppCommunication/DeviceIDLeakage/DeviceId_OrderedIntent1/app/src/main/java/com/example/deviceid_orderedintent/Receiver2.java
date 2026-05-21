package com.example.deviceid_orderedintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Receiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String res = getResultData();
        Intent in = new Intent("com.example.collector");
        in.setType("text/plain");
        in.putExtra(Intent.EXTRA_TEXT,res);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
        Toast.makeText(context, "RCVR 2.." + res, Toast.LENGTH_LONG).show();
        //setResultData(res);
    }
}