package com.example.myproject;


import android.util.Log;

public class Article {

    private String titre;
    private String auteur;
    private String date;
    private String source;
    private String description;
    private String url;
    private String image;

    public Article(String titre, String auteur, String date, String description, String url, String image) {
        this.titre = titre;
        if (auteur != null){
            this.auteur = auteur;
        }
        else {
            this.auteur = "Anonyme";
        }
        this.date = date;
        this.description = description;
        this.url = url;
        this.image = image;
    }
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getSource() {
        return source;
    }

}
