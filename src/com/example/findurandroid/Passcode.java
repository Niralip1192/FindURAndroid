package com.example.findurandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Passcode extends Activity {
	
	EditText et;
	Button bt1;
	CheckBox ck;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        et=(EditText)findViewById(R.id.EditpassText1);
        bt1=(Button)findViewById(R.id.btps1);
        
        bt1.setOnClickListener(bt1OnClickListener);
        ck=(CheckBox)findViewById(R.id.checkbox1);
        
        ck.setOnClickListener(ckOnClickListener);
              
        
      
        et.setInputType(InputType.TYPE_NULL);
        LoadPreferences();
  }       
    
    CheckBox.OnClickListener ckOnClickListener
    = new CheckBox.OnClickListener(){
    	 @Override
    	 public void onClick(View arg0)
    	 {

    	        if (ck.isChecked())        
    	        {          
    	        SharedPreferences settings = getSharedPreferences("filename1", MODE_PRIVATE);
    	        SharedPreferences.Editor editor = settings.edit();
    	        editor.putBoolean("skipMessage", true);
    	        // Commit the edits!
    	        editor.commit();
    	        }
    	        else
    	        {
    	        	  SharedPreferences settings = getSharedPreferences("filename1", MODE_PRIVATE);
    	    	        SharedPreferences.Editor editor = settings.edit();
    	    	        editor.putBoolean("skipMessage", false);
    	    	        // Commit the edits!
    	    	        editor.commit();
    	        }
    	 }
    	
    };
    Button.OnClickListener bt1OnClickListener
    = new Button.OnClickListener(){
    	 @Override
    	 public void onClick(View arg0) {
    		 String one=et.getText().toString();
    	    	et.setText(one+"1");
    	 }
    };
       
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void gotwo(View view)
    {
    	et.setText(et.getText().toString()+"2");
    }
    public void gothree(View view)
    {
    	et.setText(et.getText().toString()+"3");
    }
    public void gofour(View view)
    {
    	et.setText(et.getText().toString()+"4");
    }
    public void gofive(View view)
    {
    	et.setText(et.getText().toString()+"5");
    }
    public void gosix(View view)
    {
    	et.setText(et.getText().toString()+"6");
    }
    public void goseven(View view)
    {
    	et.setText(et.getText().toString()+"7");
    }
    public void goeight(View view)
    {
    	et.setText(et.getText().toString()+"8");
    }
    public void gonine(View view)
    {
    	et.setText(et.getText().toString()+"9");
    }
    public void gosave(View view)
    {
    	  if (et.getText().toString().length()<4)
    	  {
    		  Toast.makeText(getApplicationContext(), "Please Enter 4 digit for passcode", 7000).show();
    		  et.setText("");
    	  }
		   SavePreferences();

		   LoadPreferences();
    	
    }
    
    private void SavePreferences(){
	    SharedPreferences sharedPreferences = getSharedPreferences("filename", MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPreferences.edit();
	    editor.putString("MEMSave",et.getText().toString());  
	    editor.commit();
	   }
	  
   private void LoadPreferences(){
	    SharedPreferences sharedPreferences =getSharedPreferences("filename", MODE_PRIVATE);
	    SharedPreferences sharedPreferences1 =getSharedPreferences("filename1", MODE_PRIVATE);
	    String Memsave = sharedPreferences.getString("MEMSave", "");
	    Boolean ckm=sharedPreferences1.getBoolean("skipMessage",false);
	    ck.setChecked(ckm);
	    
	    et.setText(Memsave); 
	    
	    
         }
  
    public void gozero(View view)
    {
    	et.setText(et.getText().toString()+"0");
    }
    public void gocancel(View view)
    {
    	et.setText("");
    }
   


}