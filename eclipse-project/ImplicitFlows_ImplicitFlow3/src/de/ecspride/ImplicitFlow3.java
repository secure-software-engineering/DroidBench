package de.ecspride;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ImplicitFlow3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow3);
        ArrayList arrayList = new ArrayList();
		LinkedList linkedList = new LinkedList();
		
		//information to leak: 101
		leakInformationBit(linkedList);
		leakInformationBit(arrayList);
		leakInformationBit(linkedList);	
		
		Log.i("INFO", "bevore");
    }

    //second leak
  	public void leakData(View view){
  		Log.i("INFO", "bevor1");
  		//leak bit about correct password
  		EditText mEdit = (EditText)findViewById(R.id.password);
  		String userInputPassword = mEdit.getText().toString();
  		Log.i("INFO", "bevore2");
  		
  		Interface classTmp;
  		if(userInputPassword.equals("superSecure"))
  			classTmp = new ClassA();
  		else
  			classTmp = new ClassB();
  		
  		classTmp.leakInfo();
  	}

  	
  	private void leakInformationBit(List list){	
  		if(list instanceof ArrayList)
  			Log.i("INFO", "0");
  		else if(list instanceof LinkedList)
  			Log.i("INFO", "1");
  	}
  	
  	interface Interface{
  		public void leakInfo();
  	}
  	
  	public class ClassA implements Interface{
  		public void leakInfo(){
  			Log.i("INFO", "password correct");
  		}
  	}
  	
  	public class ClassB implements Interface{
  		public void leakInfo(){
  			Log.i("INFO", "password incorrect");
  		}
  	}

    
}
