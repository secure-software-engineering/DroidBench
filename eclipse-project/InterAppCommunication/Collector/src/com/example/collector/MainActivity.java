package com.example.collector;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;
import android.os.Bundle;  
import android.content.Context;  
import android.view.Menu;   
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.EditText;  
import android.widget.Toast;  

/**
 * @testcase_name Collector
 * @version 0.1
 * @author Malaviya National Institute of Technology Jaipur, India 
 * @author_mail er.shwetabhandari@gmail.com
 * 
 * @description The data received through an intent is written into a file on
 * the SD card
 * @dataflow intent -> file
 * @number_of_leaks 1
 * @challenges The analysis must correctly handle file write operations as well
 * as inter-app communication through intents 
 */
public class MainActivity extends Activity
{
	TextView txtView;
	ImageView picView;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       txtView = (TextView)findViewById(R.id.txt);
       picView = (ImageView)findViewById(R.id.picture);
       
       Intent receivedIntent = getIntent();
       String receivedAction = receivedIntent.getAction();
       String receivedType = receivedIntent.getType();

       if(receivedAction.equals("com.example.collector"))
       {
           if(receivedType.startsWith("text/"))
           {
              
               String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);

               if (receivedText != null){
			
                   txtView.setText(receivedText);
                String filename="LeakFile.txt";  
                String data=receivedText;
                  
                FileOutputStream fos;  
                   try {  
                       File myFile = new File("/sdcard/"+filename);  
                        myFile.createNewFile();  
                        FileOutputStream fOut = new   
  
		FileOutputStream(myFile);  
                        OutputStreamWriter myOutWriter = new   
  
		OutputStreamWriter(fOut);  
                        myOutWriter.append(data);  
                        myOutWriter.close();  
                        fOut.close();  
                     
            	Toast.makeText(getApplicationContext(),filename + "saved",Toast.LENGTH_LONG).show();  
                      
                     
                   } catch (FileNotFoundException e) {e.printStackTrace();}  
                   catch (IOException e) {e.printStackTrace();} 
		}

           }
           else if(receivedType.startsWith("image/"))
           {
               String name;
               txtView.setVisibility(View.GONE);

               Uri uri = (Uri)receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
               if (uri != null)
                   picView.setImageURI(uri);
           }

           else if(receivedType.startsWith("file/"))
           {
               Uri uri =(Uri)receivedIntent.getExtras().get("file");
               File f = new File(uri.getPath());
               String s = f.getName();
               txtView.setText("File Name: "+s);
           }
       }
       
    }

}
