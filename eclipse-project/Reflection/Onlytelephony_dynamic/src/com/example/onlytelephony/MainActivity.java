package com.example.onlytelephony;


import java.lang.reflect.Method;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


	TelephonyManager telephonyManager;  
	String id=null;
	TextView tv;
    Class<?> c = null;
    Method method;
    String int1;
    Object o;
    Class<?>[] param;
    Class<?> i;
    Intent it;
    ArrayList<Class> cl = new ArrayList<Class>();
    ArrayList<Context> co = new ArrayList<Context>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView1);

        id = "android.telephony.TelephonyManager";
       int1 = "android.content.Intent";
      
       try{
    	   
           c=Class.forName(id);
         
           telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
         method = c.getMethod("get"+"Device"+"Id", new Class<?>[0]);
         Toast.makeText(this,"tele manager is executed", 
                 Toast.LENGTH_SHORT).show();
               id =(String) method.invoke(telephonyManager);
              
               tv.setText(id);
           	
       }catch(Exception e){
   	               e.printStackTrace();
   	               tv.setText("there is error");
       }

        Intent i = new Intent(this,Activity2.class);
        i.putExtra("imei", id);
        startActivity(i);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
