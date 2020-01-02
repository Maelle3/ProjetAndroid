package com.example.myproject;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WebViewActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_web_view);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String nom = getIntent().getStringExtra("nom");
        final String id = getIntent().getStringExtra("id");

        super.onCreate(savedInstanceState);

        TextView txt = (TextView) findViewById(R.id.Chargement);
        txt.setText("Vous allez être redirigé vers le site de " + nom);

       Boolean b = isNetworkConnectionAvailable();


        WebView webView = findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);



    }
}
