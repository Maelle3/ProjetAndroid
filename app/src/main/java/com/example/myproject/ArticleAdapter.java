package com.example.myproject;


import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
/* On surcharge ArrayAdapter */

public class ArticleAdapter extends BaseAdapter {
    List<Article> articles;
    LayoutInflater inflater;

    private class ViewHolder {
        TextView tvTitre;
        TextView tvAuteur;
        TextView tvDate;
        TextView tvSource;
    }
    public ArticleAdapter(Context context, List<Article> articles) {
        inflater = LayoutInflater.from(context);
        this.articles = articles;
    }
    /**
     * Génère la vue pour un objet
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            if (position % 2 == 0){
                convertView = inflater.inflate(R.layout.article_gauche, null);
            }
            else{
                convertView = inflater.inflate(R.layout.article_droit, null);
            }
            holder.tvTitre = (TextView) convertView.findViewById(R.id.txtTitre);
            holder.tvAuteur = (TextView) convertView.findViewById(R.id.txtAuteur);
            holder.tvDate = (TextView) convertView.findViewById(R.id.txtDate);

            convertView.setTag(holder);
        }
        else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        Article article = articles.get(position);
        holder.tvTitre.setText(article.getTitre());
        holder.tvAuteur.setText(article.getAuteur());
        holder.tvDate.setText(article.getDate());
        holder.tvSource.setText(article.getSource());

        ImageView image = convertView.findViewById(R.id.imageView);
        if (article.getImage() != "null"){
            Picasso.get().load(article.getImage()).into(image);
        }
        else image.setImageResource(R.drawable.icone_news_background);

        return convertView;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Article getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}