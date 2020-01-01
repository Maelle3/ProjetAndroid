package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monIntent = new Intent(ArticleActivity.this,
                        SourcesActivity.class);
                startActivity(monIntent);;
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
        ListView myListView = (ListView) findViewById(R.id.ListViewArticles);
        ArticleAdapter adapter = new ArticleAdapter(this, liste_articles);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.d("jsontest3", "Coucou");
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
