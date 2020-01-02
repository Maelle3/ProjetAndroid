package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        final String id_source = getIntent().getStringExtra("id_source");
        final String nom = getIntent().getStringExtra("nom_source");
        final RequestQueue queue = Volley.newRequestQueue(this);


        TextView txt = (TextView) findViewById((R.id.TextView));
        txt.setText(nom);

        TextView source = (TextView) findViewById((R.id.txtSource));
        source.setText(nom);

        TextView auteur = (TextView) findViewById((R.id.txtAuteur));
        auteur.setText(getIntent().getStringExtra("author"));

        TextView description = (TextView) findViewById((R.id.txtDescription));
        description.setText(getIntent().getStringExtra("description"));

        TextView date = (TextView) findViewById((R.id.txtDate));
        date.setText(getIntent().getStringExtra("date"));

        TextView titre = (TextView) findViewById((R.id.txtTitre));
        titre.setText(getIntent().getStringExtra("titre"));

        String url_i = getIntent().getStringExtra("url_image");


        ImageView image = findViewById(R.id.imageView);
        if (url_i != "null"){
            Picasso.get().load(url_i).into(image);
        }
        else {
            image.setImageResource(R.mipmap.image_null);
        }

        Button btn1 = (Button) findViewById(R.id.lire);

        btn1.setOnClickListener (new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(DetailActivity.this, WebViewActivity.class);
                monIntent.putExtra("url", getIntent().getStringExtra("url_article"));
                startActivity(monIntent);
            }});

        Button btn2 = (Button) findViewById(R.id.Button);

        btn2.setOnClickListener (new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://newsapi.org/v2/everything?apiKey=35bf446307124bdc80419062b1a6be02&language=fr&sources="+id_source;


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent monIntent = new Intent(DetailActivity.this, ArticleActivity.class);
                                monIntent.putExtra("jsonobject", response.toString());
                                monIntent.putExtra("nom", nom );
                                monIntent.putExtra("id", id_source);
                                startActivity(monIntent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);
            }

        });
    }
}

