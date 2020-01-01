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
    protected JSONArray json_sources;
    protected ListView lvsources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        lvsources = findViewById(R.id.ListViewSources);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    json_sources = response.getJSONArray("sources");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        try {
            this.RemplirLaListe();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SourceAdapter adapter = new SourceAdapter(this, liste_sources);
        lvsources.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvsources.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SourcesActivity.this, ArticleActivity.class);
                startActivity(intent);
            }
        });

    }


    private void RemplirLaListe() throws JSONException {
        liste_sources.clear();
        for (int i = 0; i < json_sources.length(); i++) {
            JSONObject article = json_sources.getJSONObject(i);
            liste_sources.add(new Source(article.get("id").toString(), article.get("name").toString()));
        }
    }
}
