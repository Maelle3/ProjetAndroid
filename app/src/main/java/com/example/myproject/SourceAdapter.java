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

public class SourceAdapter extends BaseAdapter {
    List<Source> sources;
    LayoutInflater inflater;

    private class ViewHolder {
        TextView tvNom;
    }
    public SourceAdapter(Context context, List<Source> sources) {
        inflater = LayoutInflater.from(context);
        this.sources = sources;
    }
    /**
     * Génère la vue pour un objet
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.source_layout, null);
            holder.tvNom = (TextView) convertView.findViewById(R.id.txtNom);

            convertView.setTag(holder);
        }
        else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }
       Source source = sources.get(position);
        holder.tvNom.setText(source.getName());


        return convertView;
    }

    @Override
    public int getCount() {
        return sources.size();
    }

    @Override
    public Source getItem(int position) {
        return sources.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}