package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
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

public class ArticleActivity extends AppCompatActivity {

    protected List<Article> liste_articles = new ArrayList<Article>();
    protected JSONArray json_articles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ImageButton btn1 = (ImageButton)findViewById(R.id.imageButton);

        final RequestQueue queue = Volley.newRequestQueue(this);


//ACCEDER AUX CHOIX DES SOURCES
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url_sources = "https://newsapi.org/v2/sources?apiKey=d31f5fa5f03443dd8a1b9e3fde92ec34&language=fr";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_sources, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent monIntent = new Intent(ArticleActivity.this, SourcesActivity.class);
                                monIntent.putExtra("json_sources", response.toString());
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



        try {
            TextView txt = (TextView) findViewById((R.id.TextView));
            txt.setText(getIntent().getStringExtra("nom"));
            JSONObject source1 = new JSONObject(getIntent().getStringExtra("jsonobject"));
            json_articles = source1.getJSONArray("articles");
            Log.d("jsontest2",json_articles.toString());
            this.RemplirLaListe();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        ListView lvArticle = (ListView) findViewById(R.id.ListViewArticles);
        ArticleAdapter adapter = new ArticleAdapter(this, liste_articles);
        lvArticle.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent monIntent = new Intent(ArticleActivity.this, DetailActivity.class);
                monIntent.putExtra("author", liste_articles.get(position).getAuteur());
                monIntent.putExtra("date", liste_articles.get(position).getDate());
                monIntent.putExtra("description", liste_articles.get(position).getDescription());
                monIntent.putExtra("url_image", liste_articles.get(position).getImage());
                monIntent.putExtra("titre", liste_articles.get(position).getTitre());
                monIntent.putExtra("url_article", liste_articles.get(position).getUrl());
                monIntent.putExtra("nom_source", getIntent().getStringExtra("nom"));
                monIntent.putExtra("id_source", getIntent().getStringExtra("id"));
                startActivity(monIntent);
            }
        });



    }


    private void RemplirLaListe() throws JSONException {
        liste_articles.clear();
        int j = json_articles.length();
        for (int i = 0; i < json_articles.length(); i++) {
            JSONObject article = json_articles.getJSONObject(i);
            liste_articles.add(new Article(article.get("title").toString(), article.get("author").toString(), article.get("publishedAt").toString().substring(0, 10), article.get("description").toString(), article.get("url").toString(), article.get("urlToImage").toString()));
        }
    }
}

