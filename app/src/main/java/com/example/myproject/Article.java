package com.example.myproject;


public class Article {

    private String titre;
    private String auteur;
    private String date;
    private String description;
    private String url;
    private String image;

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

    public Article(String titre, String auteur, String date, String description, String url, String image) {
        this.titre = titre;
        this.auteur = auteur;
        this.date = date;
        this.description = description;
        this.url = url;
        this.image = image;
    }
}
