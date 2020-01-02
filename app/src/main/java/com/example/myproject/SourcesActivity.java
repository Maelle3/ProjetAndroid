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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        lvsources = findViewById(R.id.ListViewSources);
        try {
            Log.d("testsource", "coucou1");
            this.RemplirLaListe();
            ListView myListView = (ListView) findViewById(R.id.ListViewSources);
            SourceAdapter adapter = new SourceAdapter(this, liste_sources);
            myListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();





        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void RemplirLaListe() throws JSONException {

        JSONObject jsont =  new JSONObject(getIntent().getStringExtra("json_sources"));;
        json_sources = jsont.getJSONArray("sources");
        liste_sources.clear();
        for (int i = 0; i < json_sources.length(); i++){
            JSONObject source = json_sources.getJSONObject(i);
            liste_sources.add(new Source(source.get("id").toString(), source.get("name").toString()));
        }
        Log.d("testsource","coucou4");

    }
}
