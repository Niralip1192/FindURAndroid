package com.example.findurandroid;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Toast;


public class Todoactivity  extends Service{
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters

    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds

    private boolean gps_enabled = false;
	private boolean network_enabled = false;

    protected LocationManager locationManager;
    private LocationListener locListener = new MyLocationListener();
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
       
    }


    @Override
    public void onStart(Intent intent, int startid)
    {
             
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
          
	        String msg1,msg2;
	        msg1=intent.getStringExtra("body");
	        msg2=intent.getStringExtra("number");
	        
	        SharedPreferences sharedPreferences = getSharedPreferences("filename2", MODE_PRIVATE);
		    String strring = sharedPreferences.getString("MEM1", "");
		    
		    SharedPreferences sharedPreferences1 =getSharedPreferences("filename3", MODE_PRIVATE);
		    String strgps = sharedPreferences1.getString("MEM12", "");
		    
		    locationManager.requestLocationUpdates(

	                LocationManager.GPS_PROVIDER,

	                MINIMUM_TIME_BETWEEN_UPDATES,

	                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,

	                new MyLocationListener()

	        );

		    
		  
		
		   if(msg1.equals(strring))
		    {
			   try
			   {
				   
				  
				   
			      AudioManager manager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
			      manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			      manager.setStreamVolume(AudioManager.STREAM_RING, 6, AudioManager.FLAG_PLAY_SOUND);	  
			      Uri uri = RingtoneManager.getActualDefaultRingtoneUri(getBaseContext(), RingtoneManager.TYPE_RINGTONE);
				  
			 final  Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), uri);
			  			   r.play();
			  			 
			  			 TimerTask task = new TimerTask() {
			  			    @Override
			  			    public void run() {
			  			        r.stop();
			  			    }
			  			};
			  			Timer timer = new Timer();
			  			timer.schedule(task,12000);	
			  			
			   }
			   catch(Exception e)
			   {
				   e.printStackTrace();
			   }
		    	
		    }
		    
		   
		   if(msg1.equals(strgps))
		    {
			   SmsManager.getDefault().sendTextMessage(msg2, null,"Your message is received it takes around 5 mins to get your phone location if you dont get location within 5 mins again send same msg once",null,null);
			 			   
			  
			   String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		       if(!provider.contains("gps")){ 
		                 final Intent poke = new Intent();
		                 poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
		                 poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		                 poke.setData(Uri.parse("3")); 
		                 sendBroadcast(poke);
		                }
		       Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		       try {
					gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
				} catch (Exception ex) {
				}
				try {
					network_enabled =locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
				} catch (Exception ex) {
				}
				if (gps_enabled) {
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
				}
				if (network_enabled) {
					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
				}
				boolean state = isMobileDataEnable();
				if (!state) {

				     toggleMobileDataConnection(true);
				}
		       if (location != null) {
		    	   
		    	   double latitude,longitude;
				  
		    	   longitude= location.getLongitude();
		    	   latitude= location.getLatitude();
		    	   
		    	   
		           String message = String.format(

		                   "Current Location \n Longitude: %1$s \n Latitude: %2$s",

		                   latitude,longitude );
		           String link;
		           link=" http://maps.google.com/?q="+latitude+","+longitude;
		           
		                   SmsManager.getDefault().sendTextMessage(msg2, null, message,null,null);
		                   SmsManager.getDefault().sendTextMessage(msg2, null, link,null,null);
		                   Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());                 
		                   try
		                   {
		                       List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
		                       if(null!=listAddresses&&listAddresses.size()>0){
		                           String Location1 = listAddresses.get(0).getAddressLine(1);
		                           SmsManager.getDefault().sendTextMessage(msg2, null, Location1  ,null,null);
		                      
		                       }
		                   } catch (IOException e) {
		                     e.printStackTrace();
		                   }
		                                  
		                         
		                   
		    }
		   
	       
 }
		   
}
    public boolean isMobileDataEnable() {

    	  boolean mobileDataEnabled = false; // Assume disabled
    	  ConnectivityManager cm = (ConnectivityManager) this
    	    .getSystemService(Context.CONNECTIVITY_SERVICE);
    	  try {
    	   Class cmClass = Class.forName(cm.getClass().getName());
    	   Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
    	   method.setAccessible(true); // method is callable
    	   // get the setting for "mobile data"
    	   mobileDataEnabled = (Boolean) method.invoke(cm);
    	  } catch (Exception e) {
    	   
    	  }
    	  return mobileDataEnabled;
    	 }
    public boolean toggleMobileDataConnection(boolean ON) {
    	  try {
    	   
    	   
    	   final ConnectivityManager conman = (ConnectivityManager) this
    	     .getSystemService(Context.CONNECTIVITY_SERVICE);
    	  
    	   final Class conmanClass = Class.forName(conman.getClass().getName());
    	   
    	   final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
    	   
    	   iConnectivityManagerField.setAccessible(true);
    	  
    	   final Object iConnectivityManager = iConnectivityManagerField
    	     .get(conman);
    	   
    	   final Class iConnectivityManagerClass = Class
    	     .forName(iConnectivityManager.getClass().getName());
    	 
    	   final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
   
    	   setMobileDataEnabledMethod.setAccessible(true);
    	   
    	   setMobileDataEnabledMethod.invoke(iConnectivityManager, ON);
    	  } catch (Exception e) {
    	  }
    	  return true;
    	 }
	 private class MyLocationListener implements LocationListener {

		 
		 @Override
	        public void onLocationChanged(Location location) {
			   
                
		    	   
		           String message = String.format(

		                   "Current Location \n Longitude: %1$s \n Latitude: %2$s",

		                   location.getLongitude(),location.getLatitude());        
	                  
	    }

	 
	        @Override
	        public void onStatusChanged(String s, int i, Bundle b) {

	           

	        }

	 
	        @Override
	        public void onProviderDisabled(String s) {
	        
	        	
	            	String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	                if(provider.contains("gps")){ 
	                    final Intent poke = new Intent();
	                    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	                    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	                    poke.setData(Uri.parse("3")); 
	                    sendBroadcast(poke);
	                }
	        	
	      
	        }



			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
				String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

	            if(!provider.contains("gps")){ 
	                final Intent poke = new Intent();
	                poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider"); 
	                poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
	                poke.setData(Uri.parse("3")); 
	                sendBroadcast(poke);
			}

	      
	        }

	       

	   }

	     

 }
	    
	


