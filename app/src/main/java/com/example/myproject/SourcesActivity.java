package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
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
        ListView myListView = (ListView) findViewById(R.id.myListView);
        SourceAdapter adapter = new SourceAdapter(this, liste_sources);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void RemplirLaListe() throws JSONException {
        liste_sources.clear();
        for (int i = 0; i < json_sources.length(); i++) {
            JSONObject article = json_sources.getJSONObject(i);
            liste_sources.add(new Source(article.get("id").toString(), article.get("name").toString()));
        }
    }
}
