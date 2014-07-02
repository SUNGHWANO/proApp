package com.example.tt;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private BackPressCloseHandler backPressCloseHandler;
	private long backKeyPressedTime = 0;
	private Toast toast;
	private Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		WebView webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("file:///android_asset/www/alerttest.html"); 
		setContentView(webView);
		
	
	
	webView.addJavascriptInterface(new Object() {
    @JavascriptInterface
    public String toString() {
        return "AppInterface";
    }
    @JavascriptInterface
    public void toast(String text) {
        //Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    	
      NotificationManager notimng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
   		PendingIntent notiP = PendingIntent.getActivity(	getApplicationContext(), 0, 
   				new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

   		Notification notice = new NotificationCompat.Builder(getApplicationContext())
   				.setContentTitle("VoiceSchedulet Alert")
   				.setContentText("Today Schedule")
   				.setSmallIcon(R.drawable.ic_launcher)
   				.setTicker("Today Your Schedule").setAutoCancel(true)
   				.setContentIntent(notiP).build();

   		notimng.notify(0, notice);
      
  		/*
    	if ( notimng != null ) {
  			notimng.cancel(0);
  		}
    	*/
      
      
   		 Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
      vibe.vibrate(1000);
    	
    }
}, "AppInterface");

	
}
	
	

	
	
	
}
