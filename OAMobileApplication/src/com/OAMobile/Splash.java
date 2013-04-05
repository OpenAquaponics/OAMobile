package com.OAMobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;


public class Splash extends Activity {

	private OAMobileData gData = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        gData = (OAMobileData) getApplication();
        
        Thread timer = new Thread() {
            public void run() {
                try {
                    ProgressBar pbLoading = (ProgressBar)findViewById(R.id.pb_loading_app);
                    pbLoading.setProgress(0);
                    if(gData != null) gData.loadSettings();

                    for(int i = 0; i < 5; i++) {
                        sleep(330);
                        pbLoading.setProgress((i+1)*20);
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent openMainMenu = new Intent("com.androidhive.jsonparsing.MAIN_MENU");
                    startActivity(openMainMenu);
                }
            }
        };
        timer.start();
    }

}