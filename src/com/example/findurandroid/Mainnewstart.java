package com.example.findurandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import android.widget.ListView;



public class Mainnewstart extends Activity {
ListView l1;


private ArrayAdapter<String>ad;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.activity_mainstart);
		 this.setTheme(R.style.ThemeRed);
		l1=(ListView)findViewById(R.id.listView2);
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
