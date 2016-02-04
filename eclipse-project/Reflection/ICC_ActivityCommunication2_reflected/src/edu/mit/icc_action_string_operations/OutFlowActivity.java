package edu.mit.icc_action_string_operations;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @testcase_name ICC-Action-String-Operations
 * 
 * @description  Testing substring operation and explicit intent resolution
 * @dataflow source -> sink
 * @number_of_leaks 1
 * @challenges   The analysis tool has to be able to evaluate substring operation and track tainted value through another Activity.
 */
public class OutFlowActivity extends Activity {

	String id=null;
	String int1,che;
    Object o;
    Class<?>[] param;
    Class<?> c;
    Intent it;
    Method method;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getDeviceId(); //source
		
		int1 = "edu.mit.icc_action_string_operations.ACTION";
        che = "edu.mit.icc_action_string_operations.implicit";
    	try{
             
      		c=Class.forName(che);
      		 Object[] obj = {this,int1,imei};
                      o = c.newInstance();
                         Class<?> params[] = new Class[obj.length];
                      for (int i = 0; i < obj.length; i++) {
                          if (obj[i] instanceof String) {
                              params[i] = String.class;
                          } else if (obj[i] instanceof Context) {
                              params[i] = Context.class;
                          }
                      }
                         method = o.getClass().getMethod("rec_intent",params);
                      method.invoke(o,obj);
      		         
      	  }
      	  catch(Exception e){
      		 
                   e.printStackTrace();
                   Toast.makeText(getBaseContext(),"tjere is an error", 
                           Toast.LENGTH_SHORT).show();
                   
      	  }
        
        
		
		
		/*Intent i = new Intent("ignore.edu.mit.icc_action_string_operations.ACTION".substring(7));
		i.putExtra("DroidBench", imei);
		
		startActivity(i);*/
	}

}
