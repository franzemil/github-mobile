package com.example.franzemil.gitmobile.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franzemil.gitmobile.R;
import com.example.franzemil.gitmobile.models.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.ViewHolder>{

    private List<Repositorio> dataSet;
    private RepositorioListener listener;

    public RepositorioAdapter() {
        this.dataSet = new ArrayList<>();
    }

    public List<Repositorio> getDataSet() {
        return dataSet;
    }

    public void setListener(RepositorioListener listener) {
        this.listener = listener;
    }

    public void setDataSet(List<Repositorio> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_respositorio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositorioAdapter.ViewHolder holder, int position) {
        Repositorio repositorio = this.getDataSet().get(position);

        holder.nameTextView.setText(repositorio.getName());
        holder.descriptionTextView.setText(repositorio.getDescription());
        holder.fullNameTextView.setText(repositorio.getFullName());

        if (this.listener != null) {
            holder.setOnclickListerner(this.listener, repositorio);
        }
    }

    @Override
    public int getItemCount() {
        return this.getDataSet().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView repositorioCardView;
        TextView nameTextView;
        TextView fullNameTextView;
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            repositorioCardView = (CardView) itemView.findViewById(R.id.repositorioCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            fullNameTextView = (TextView) itemView.findViewById(R.id.fullNameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.descripcionTextView);
        }

        public void setOnclickListerner(final RepositorioListener listener, final Repositorio repositorio) {
            repositorioCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickListener(repositorio);
                }
            });
        }
    }

    public interface RepositorioListener {
        void onClickListener(Repositorio repositorio);
    }
}
