
package com.androidhive.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;


public class Splash extends Activity {

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        
        Thread timer = new Thread() {
            public void run() {
                try {
                    ProgressBar pbLoading = (ProgressBar)findViewById(R.id.pb_loading_app);
                    pbLoading.setProgress(0);
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