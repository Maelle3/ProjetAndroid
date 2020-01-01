package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);

        String url_lemonde = "https://newsapi.org/v2/everything?apiKey=35bf446307124bdc80419062b1a6be02&language=fr&sources=le-monde";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_lemonde, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent monIntent = new Intent(MainActivity.this, ArticleActivity.class);
                        monIntent.putExtra("jsonobject", response.toString());
                        startActivity(monIntent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);







    }
}
