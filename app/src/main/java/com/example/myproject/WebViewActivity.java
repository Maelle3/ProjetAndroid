package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web_view);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String nom = getIntent().getStringExtra("nom");
        final String id = getIntent().getStringExtra("id");

        super.onCreate(savedInstanceState);

        TextView txt = (TextView) findViewById(R.id.Chargement);
        txt.setText("Vous allez être redirigé vers le site de " + nom);


        WebView webView = findViewById(R.id.webview);
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);



        /*Button btn = (Button) findViewById(R.id.Button);

        btn.setOnClickListener (new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://newsapi.org/v2/everything?apiKey=35bf446307124bdc80419062b1a6be02&language=fr&sources="+id;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent monIntent = new Intent(WebViewActivity.this, ArticleActivity.class);
                                monIntent.putExtra("jsonobject", response.toString());
                                monIntent.putExtra("nom", nom );
                                monIntent.putExtra("id", id);
                                startActivity(monIntent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);
            }

        });*/

    }
}
