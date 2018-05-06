package com.example.findurandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;






public class Ringword extends Activity {
	
	
	
   EditText et;
	Button bt;
	public String rt;
	TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
     
        et = (EditText)findViewById(R.id.ringtext1);
        bt = (Button)findViewById(R.id.ringbtn1);
        bt.setOnClickListener(btOnClickListener);
    	
       LoadPreferences();
     //  SMSBroadcastReceiver nac = new SMSBroadcastReceiver(this);
     }
        
  
      
    Button.OnClickListener btOnClickListener
    = new Button.OnClickListener(){
    	 @Override
    	 public void onClick(View arg0) {
    		   // TODO Auto-generated method stub
    		   SavePreferences("MEM1",et.getText().toString());

    		   LoadPreferences();
    		  }
    		   
    	    }; 
    	 private void SavePreferences(String key, String value){
    			    SharedPreferences sharedPreferences = getSharedPreferences("filename2", MODE_PRIVATE);
    			    SharedPreferences.Editor editor = sharedPreferences.edit();
    			    editor.putString(key,value);  
    			    editor.commit();
    			   }
    			  
    	  private void LoadPreferences(){
    			    SharedPreferences sharedPreferences = getSharedPreferences("filename2", MODE_PRIVATE);
    			    String strSavedMem1 = sharedPreferences.getString("MEM1", "");
    			   
    			    et.setText(strSavedMem1); 
    			    
    			    
    		         }
    		  
    	   
    	  
    		  
    		
    	                 
    public void removeringtext(View view)
    {
    	
    	et=(EditText)findViewById(R.id.ringtext1);
    	et.setText(" ");
    }

    
   
    	                          
}      
    



