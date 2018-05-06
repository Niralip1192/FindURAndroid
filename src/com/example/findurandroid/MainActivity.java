package com.example.findurandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import android.widget.ListView;


public class MainActivity extends Activity {
ListView l1;


private ArrayAdapter<String>ad;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 SharedPreferences sharedPreferences1 =getSharedPreferences("filename1", MODE_PRIVATE);
		  Boolean ckm=sharedPreferences1.getBoolean("skipMessage",false);		  
		 
		try
		{
		if(ckm.equals(true))
		{	 
			
		Intent intent=new Intent(getApplicationContext(),Start.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);  
		}
		
		 setContentView(R.layout.activity_main);
		 this.setTheme(R.style.ThemeRed);
		}
		catch (Exception e)
		{
			 e.printStackTrace();
		}
		
	
		   
		
		l1=(ListView)findViewById(R.id.listView1);
		
		String[] values = new String[] { "Set GPS Attention Word", "Set Ring Attention Word", "Passcode Setting " };
		
	 ad = new ArrayAdapter<String>(this,
				  android.R.layout.simple_expandable_list_item_1, android.R.id.text1, values);
		l1.setAdapter(ad);
		l1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
			     Intent intent;
			     switch (arg2) {
			         case 0:
			             intent= new Intent(arg1.getContext(), GPSword.class);
			             startActivity(intent);
			             break;
			         case 1:
			             intent=new Intent(arg1.getContext(),Ringword.class);
			             startActivity(intent);
			             break;
			         case 2:
			             intent=new Intent(arg1.getContext(),Passcode.class);
			             startActivity(intent);  
			             break;
			        
			        
			         default:
			             break;
			         }
								
			}
		});
		
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

