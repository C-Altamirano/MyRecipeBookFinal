package com.example.caltamirano.myrecipebook.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.caltamirano.myrecipebook.ListFragment;
import com.example.caltamirano.myrecipebook.MainActivity;
import com.example.caltamirano.myrecipebook.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import com.example.caltamirano.myrecipebook.R;
import com.facebook.drawee.view.SimpleDraweeView;



public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> dataset;
    private Context context;
    private OnRecipeSelectedListener onRecipeSelectedListener;
    private OnRecipeSaveSelectedListener onRecipeSaveSelectedListener;

    public RecipeAdapter(Context context) {
        this.context = context;
        this.dataset = new ArrayList<>();
        this.onRecipeSelectedListener = new ListFragment();
        this.onRecipeSaveSelectedListener = new ListFragment();
    }

    public RecipeAdapter(List<Recipe> dataset, MainActivity context) {
        this.dataset = dataset;
        this.context = context;
    }

    public RecipeAdapter(Context context, OnRecipeSelectedListener onRecipeSelectedListener, OnRecipeSaveSelectedListener onRecipeSaveSelectedListener) {
        this.dataset = new ArrayList<>();
        this.context = context;
        this.onRecipeSelectedListener = onRecipeSelectedListener;
        this.onRecipeSaveSelectedListener = onRecipeSaveSelectedListener;
    }

    public interface OnRecipeSelectedListener {
        void onRecipeSelected(Recipe recipe, Context context);
    }

    public interface OnRecipeSaveSelectedListener {
        void onRecipeSaveSelected(Recipe recipe, Context context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe a = dataset.get(position);
        holder.nombreTextView.setText(a.getTitle());
        holder.publisherTextView.setText(a.getPublisher());
        holder.linkTextView.setText(a.getfUrl());
        holder.rankRatingBar.setRating((float) (a.getSocialRank() * 0.05));

        // Así descargamos con Fresco, creando un objeto Uri primero
        Uri uri = Uri.parse(a.getImageUrl());
        holder.fotoSimpleDraweeView.setImageURI(uri);
        //holder.fotoSimpleDraweeView.setBackgroundColor((int) Math.random()*255);

        holder.setViewHolderSelectedListener(a, onRecipeSelectedListener);
        holder.setViewHolderSaveSelectedListener(a, onRecipeSaveSelectedListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void clear() {
        dataset.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView fotoSimpleDraweeView;
        private TextView nombreTextView;
        private TextView publisherTextView;
        private TextView linkTextView;
        private RatingBar rankRatingBar;
        private CardView itemCardView;
        private ImageButton saveImageButton;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.fotoSimpleDraweeView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            publisherTextView = (TextView) itemView.findViewById(R.id.publisherTextView);
            linkTextView = (TextView) itemView.findViewById(R.id.linkTextView);
            rankRatingBar = (RatingBar) itemView.findViewById(R.id.rankRatingBar);
            itemCardView = (CardView) itemView.findViewById(R.id.cardView);
            saveImageButton = (ImageButton) itemView.findViewById(R.id.saveButton);
        }

        public void setViewHolderSelectedListener(final Recipe recipe, final OnRecipeSelectedListener onRecipeSelectedListener) {
            itemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecipeSelectedListener.onRecipeSelected(recipe, context);
                }
            });
        }

        public void setViewHolderSaveSelectedListener(final Recipe recipe, final OnRecipeSaveSelectedListener onRecipeSaveSelectedListener) {
            saveImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveImageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                    onRecipeSaveSelectedListener.onRecipeSaveSelected(recipe, context);
                }
            });
        }

    }

    public void setDataset(List<Recipe> recipes) {
        if (recipes != null) {
            dataset.addAll(recipes);
        }
        notifyDataSetChanged();
    }

    public void add(Recipe recipe) {
        dataset.add(recipe);
        notifyDataSetChanged();
    }

}
