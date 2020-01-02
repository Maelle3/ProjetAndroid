package com.example.myproject;


import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InternetErrorActivity extends AppCompatActivity {


        public void checkNetworkConnection(){
            AlertDialog.Builder builder =new AlertDialog.Builder(this);
            builder.setTitle("Pas de Connection Internet");
            builder.setMessage("Recommencez après avoir réétabli la connection");
            builder.setNegativeButton("fermer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        public boolean isNetworkConnectionAvailable(){
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnected();
            if(isConnected) {
                Log.d("Network", "Connected");
                return true;
            }
            else{
                checkNetworkConnection();
                Log.d("Network","Not Connected");
                return false;
            }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
