package com.example.findurandroid;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;



public class Makering extends Activity {
	
	
	Uri uri = RingtoneManager.getActualDefaultRingtoneUri(getBaseContext(), RingtoneManager.TYPE_RINGTONE);
	  
    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), uri);
 @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makering);
        
       
 }
 
 public void rtstop(View view)
 {
	 try{
         if(  r.isPlaying())
         {
        	 r.stop();
               Intent intent = new Intent(Intent.ACTION_MAIN);
               intent.addCategory(Intent.CATEGORY_HOME);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
         }
         }catch (Exception e) {
         
         }

 }
 @Override
 public void onBackPressed()
 {
	 try{
         if(  r.isPlaying())
         {
        	r.stop();
        
        	 Intent intent = new Intent(Intent.ACTION_MAIN);
             intent.addCategory(Intent.CATEGORY_HOME);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
                           
         }
         }catch (Exception e) {
         
         }
	 
 }
	

 
}
