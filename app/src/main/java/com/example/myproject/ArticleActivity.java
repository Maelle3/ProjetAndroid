package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
        try {
            this.RemplirLaListe();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView myListView = (ListView) findViewById(R.id.myListView);
        ArticleAdapter adapter = new ArticleAdapter(this, liste_articles);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void RemplirLaListe() throws JSONException {
        liste_articles.clear();
        for (int i = 0; i < json_articles.length(); i++) {
            JSONObject article = json_articles.getJSONObject(i);
            liste_articles.add(new Article(article.get("title").toString(), article.get("author").toString(), article.get("publishedAt").toString().substring(0, 10), article.get("description").toString(), article.get("url").toString(), article.get("urlToImage  ").toString()));
        }
    }
}
