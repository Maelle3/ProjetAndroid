package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SourcesActivity extends AppCompatActivity {

    protected List<Source> liste_sources = new ArrayList<Source>();
    protected JSONArray json_sources ;
    protected ListView lvsources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("testsource", "coucou1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        lvsources = findViewById(R.id.ListViewSources);


        try {
            this.RemplirLaListe();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void RemplirLaListe() throws JSONException {
        json_sources =  new JSONArray( getIntent().getStringExtra("json_sources"));
        liste_sources.clear();
        for (int i = 0; i < json_sources.length(); i++){
            JSONObject source = json_sources.getJSONObject(i);
            liste_sources.add(new Source(source.get("id").toString(), source.get("name").toString()));
        }
    }
}
