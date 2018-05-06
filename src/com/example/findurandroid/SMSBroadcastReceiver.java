package com.example.findurandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;






    public class SMSBroadcastReceiver extends BroadcastReceiver  {
    	
            private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
            private static final String TAG = "SMSBroadcastReceiver";
            //private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
  		    //private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds		     
  		    //protected LocationManager locationManager;
          
           
            
            @Override
            public void onReceive(Context context, Intent intent) {
                 Log.i(TAG, "Intent recieved: " + intent.getAction());

                                            
                    if (intent.getAction().equals(SMS_RECEIVED)) {
                        Bundle bundle = intent.getExtras();
                        if (bundle != null) {
                            Object[] pdus = (Object[])bundle.get("pdus");
                            
                            final SmsMessage[] messages = new SmsMessage[pdus.length];
                          
                            for (int i = 0; i < pdus.length; i++) {
                                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                                
                            }
                           
                           
                          
                            if (messages.length > -1) {    
                            	
                            	 Intent i = new Intent(context,Todoactivity.class);
                                 i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
                                 i.putExtra("body", messages[0].getMessageBody());
                                 i.putExtra("number", messages[0].getOriginatingAddress());
                                 context.startService(i);
                            	
                            //	String rg="Make Ring";
                            	//String gp="Find GPS";
                            	
                            	
                            // Toast.makeText(context,"Received",7000).show();
                            //if(messages[0].getMessageBody().equals(rg))
                            	
                            //{
                            	
                            	                          
                           // }
                            //if(messages[0].getMessageBody().equals(gp))
                            	//{ 
                            	//Findgps fg=new Findgps();
                            	//String mg="hi";
                            	//fg.showCurrentLocation();
                            	//SmsManager.getDefault().sendTextMessage(messages[0].getOriginatingAddress(), null, mg,null,null);
                            	//}
                            	
                                                            	      

                            	         
                            	
                            	//if( str.equals(msg1))
                            	//{
                            	//msg=messages[0].getOriginatingAddress();
                            	
                                //Toast.makeText(context, "Message recieved: " +messages[0].getMessageBody()+msg,7000).show();
                            	//}  
                            	
                            }
                        }
                    }
               }
           
    	
        }
     