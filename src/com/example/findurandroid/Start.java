package com.example.findurandroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputType;
import android.view.View;



public class Start extends Activity {
	EditText et;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        et=(EditText)findViewById(R.id.EditstartText1);   
              
      }
    

   
    
    public void startone(View view)
    {
    	et.setText(et.getText().toString()+"1");
    }
    public void starttwo(View view)
    {
    	et.setText(et.getText().toString()+"2");
    }
    public void startthree(View view)
    {
    	et.setText(et.getText().toString()+"3");
    }
    public void startfour(View view)
    {
    	et.setText(et.getText().toString()+"4");
    }
    public void startfive(View view)
    {
    	et.setText(et.getText().toString()+"5");
    }
    public void startsix(View view)
    {
    	et.setText(et.getText().toString()+"6");
    }
    public void startseven(View view)
    {
    	et.setText(et.getText().toString()+"7");
    }
    public void starteight(View view)
    {
    	et.setText(et.getText().toString()+"8");
    }
    public void startnine(View view)
    {
    	et.setText(et.getText().toString()+"9");
    }
    public void startzero(View view)
    {
    	et.setText(et.getText().toString()+"0");
    }
    public void startenter(View view)
    {
    	 SharedPreferences sharedPreferences =getSharedPreferences("filename", MODE_PRIVATE);
         String Memsave = sharedPreferences.getString("MEMSave", "");
       
         if (et.getText().toString().equals(Memsave))
         {
        	 Intent intent=new Intent(getBaseContext(),Mainnewstart.class);
        	  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	             startActivity(intent);  
         }
         else
         {
        	 Toast.makeText(getApplicationContext(), "Incorrect Password", 7000).show();
        	 et.setText("");
         }
      
    }
    public void startcancel(View view)
    {
    	et.setText("");
    }
 
    @Override
	 public void onBackPressed()
	 {
	 Intent intent = new Intent(Intent.ACTION_MAIN);
     intent.addCategory(Intent.CATEGORY_HOME);
     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     startActivity(intent);
    	
	 }
}