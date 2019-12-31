package com.example.myproject;


import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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
        if (convertView == null) {holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.livre_item, null);
            holder.tvTitre = (TextView) convertView.findViewById(R.id.txtTitre);
            holder.tvAuteur = (TextView) convertView
                    .findViewById(R.id.txtAuteur);
            convertView.setTag(holder);
        } else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
        Livre livre = biblio.get(position);
        holder.tvTitre.setText(livre.getTitre());
        holder.tvAuteur.setText(livre.getAuteur());
        return convertView;
    }
    /**
     * Retourne le nombre d'éléments
     */
    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return biblio.size();
    }
    /**
     * Retourne l'item à la position
     **/
    public Livre getItem(int position) {
        // TODO Auto-generated method stub
        return biblio.get(position);
    }
    /**
     * Retourne la position de l'item
     * */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}