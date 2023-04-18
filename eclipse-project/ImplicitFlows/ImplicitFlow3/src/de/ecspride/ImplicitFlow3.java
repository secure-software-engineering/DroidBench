package de.ecspride;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
/**
 * @testcase_name ImplicitFlow3
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description Based on an input of a password field a log message is written
 * @dataflow source -> userInputPassword -> if-condition -> -> class initialization -> methodCall -> sink
 * @number_of_leaks 2
 * @challenges the analysis must be able to handle implicit flows,
 *  detect callbacks from layout xml file and treat the value of password fields as source
 */
public class ImplicitFlow3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow3);
        ArrayList<?> arrayList = new ArrayList();
		LinkedList<?> linkedList = new LinkedList();
		
		//information to leak: 101
		leakInformationBit(linkedList);
		leakInformationBit(arrayList);
		leakInformationBit(linkedList);	
		
		Log.i("INFO", "before");
    }

    //second leak
  	public void leakData(View view){
  		Log.i("INFO", "before1");
  		//leak bit about correct password
  		EditText mEdit = (EditText)findViewById(R.id.password);
  		String userInputPassword = mEdit.getText().toString(); //source
  		Log.i("INFO", "before2");
  		
  		Interface classTmp;
  		if(userInputPassword.equals("superSecure"))
  			classTmp = new ClassA();
  		else
  			classTmp = new ClassB();
  		
  		classTmp.leakInfo();
  	}

  	
  	private void leakInformationBit(List<?> list){	
  		if(list instanceof ArrayList)
  			Log.i("INFO", "0"); //sink
  		else if(list instanceof LinkedList)
  			Log.i("INFO", "1"); //sink
  	}
  	
  	interface Interface{
  		public void leakInfo();
  	}
  	
  	public class ClassA implements Interface{
  		public void leakInfo(){
  			Log.i("INFO", "password correct"); //sink, leak
  		}
  	}
  	
  	public class ClassB implements Interface{
  		public void leakInfo(){
  			Log.i("INFO", "password incorrect"); //sink, leak
  		}
  	}

    
}
