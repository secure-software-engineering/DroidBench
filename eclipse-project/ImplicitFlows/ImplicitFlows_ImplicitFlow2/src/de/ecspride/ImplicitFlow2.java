package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
/**
 * @testcase_name ImplicitFlow2
 * @version 0.1
 * @author Secure Software Engineering Group (SSE), European Center for Security and Privacy by Design (EC SPRIDE) 
 * @author_mail siegfried.rasthofer@cased.de
 * 
 * @description Based on an input of a password field a log message is written
 * @dataflow source -> userInputPassword -> if-condition -> sink
 * @number_of_leaks 2
 * @challenges the analysis must be able to handle implicit flows,
 *  detect callbacks from layout xml file and treat the value of password fields as source
 */
public class ImplicitFlow2 extends Activity {
	private boolean passwordCorrect = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow2);
    }

    public void checkPassword(View view){
		EditText mEdit = (EditText)findViewById(R.id.password);
		String userInputPassword = mEdit.getText().toString(); //source
		
		if(userInputPassword.equals("superSecure"))
			passwordCorrect = true;
		
		if(passwordCorrect)
			Log.i("INFO", "Password is correct"); //sink, leak
		else
			Log.i("INFO", "Password is not correct"); //sink, leak
	}
    
}
