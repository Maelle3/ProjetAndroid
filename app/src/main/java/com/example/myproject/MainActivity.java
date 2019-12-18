package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar myProgressBar;
    private TextView TxtFinDeChargement;
    private int ProgressStatus = 0;
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(ProgressStatus < 100){
                    ProgressStatus++;
                    android.os.SystemClock.sleep(50);
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            myProgressBar.setProgress(ProgressStatus);
                        }
                    });
                }
            }
        }).start();
    }
}
