package de.ecspride;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ImplicitFlow2 extends Activity {
	private boolean passwordCorrect = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_flow2);
    }

    public void checkPassword(View view){
		EditText mEdit = (EditText)findViewById(R.id.password);
		String userInputPassword = mEdit.getText().toString();
		
		if(userInputPassword.equals("superSecure"))
			passwordCorrect = true;
		
		if(passwordCorrect)
			Log.i("INFO", "Password is correct");
		else
			Log.i("INFO", "Password is not correct");
	}
    
}
