package com.example.franzemil.gitmobile.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.franzemil.gitmobile.R;
import com.example.franzemil.gitmobile.models.Language;

import java.util.ArrayList;
import java.util.List;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder>{

    private List<Language> dataSet;
    private Context context;
    private LanguageListener listener;

    public LanguageAdapter(Context context) {
        this.dataSet = new ArrayList<>();
        this.context = context;
    }



    public List<Language> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Language> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Language language = this.getDataSet().get(position);
        holder.languageTextView.setText(language.getLanguage());
        Glide.with(context).load(language.getImage())
            .into(holder.languageImageView);

        if (this.listener != null) {
            holder.setOnClickListener(language, this.listener);
        }
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public void setListener(LanguageListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView languageCardView;
        ImageView languageImageView;
        TextView languageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            languageCardView = (CardView) itemView.findViewById(R.id.languageCardView);
            languageImageView = (ImageView) itemView.findViewById(R.id.languageImageView);
            languageTextView = (TextView) itemView.findViewById(R.id.languageTextView);
        }

        public void setOnClickListener(final Language language, final LanguageListener listener) {
            languageCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLanguageClickListener(language);
                }
            });
        }
    }

    public interface LanguageListener {
        void onLanguageClickListener(Language language);
    }
}
