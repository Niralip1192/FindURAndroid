package com.example.findurandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;




public class GPSword extends Activity {

	EditText et;
	Button gpbt1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
       
      
        et = (EditText)findViewById(R.id.gpsText1);
        gpbt1 = (Button)findViewById(R.id.gpsbtn1);
        gpbt1.setOnClickListener(gpbt1OnClickListener);
        LoadPreferences();
    }
    
    Button.OnClickListener gpbt1OnClickListener
    = new Button.OnClickListener(){

  @Override
  public void onClick(View arg0) {

   SavePreferences("MEM12", et.getText().toString());
   LoadPreferences();
  }
   
   };
   private void SavePreferences(String key, String value){
	    SharedPreferences sharedPreferences =getSharedPreferences("filename3", MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putString(key, value);
	    editor.commit();
	   }
	  
	   private void LoadPreferences(){
	    SharedPreferences sharedPreferences =getSharedPreferences("filename3", MODE_PRIVATE);
	    String strSavedMem1 = sharedPreferences.getString("MEM12", "");
	   
	    et.setText(strSavedMem1);
	   
	   }
  
    public void removetext(View view)
    {
    	
    	et=(EditText)findViewById(R.id.gpsText1);
      	et.setText("  ");
    	
    }

   
    
}

