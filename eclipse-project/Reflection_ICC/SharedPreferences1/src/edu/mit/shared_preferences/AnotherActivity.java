package edu.mit.shared_preferences;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class AnotherActivity extends Activity {

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
        che = "edu.mit.shared_preferences.read";
    	try{
    		
      		c=Class.forName(che);
      		o = c.newInstance();
      		SharedPreferences at = getSharedPreferences("MyPrefsFile", 0);
      		Object[] obj = {at};
            o = c.newInstance();
               Class<?> params[] = new Class[obj.length];
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] instanceof String) {
                    params[i] = String.class;
                } else if (obj[i] instanceof SharedPreferences) {
                    params[i] = SharedPreferences.class;
                }
            }
            
            method = o.getClass().getMethod("leak",params);
           id =  (String) method.invoke(o,obj);
           
            Toast.makeText(getBaseContext(),"Imei is "+id, 
                          Toast.LENGTH_SHORT).show();
                 Log.d("imeino", id);
    	}
      	  catch(Exception e){
      		 
                   e.printStackTrace();
                   Toast.makeText(getBaseContext(),"there is an error in AnotherActivity", 
                           Toast.LENGTH_SHORT).show();
                   
      	  }
        
        
        
    }
    
 
}
