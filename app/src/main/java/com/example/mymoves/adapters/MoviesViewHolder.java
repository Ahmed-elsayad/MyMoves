package com.example.mymoves.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymoves.R;

public class MoviesViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    TextView title, release_date,duration;
    ImageView movieImage;
    RatingBar ratingBar;

    OnMovieListener onMovieListener;

    public MoviesViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener = onMovieListener;

        title = itemView.findViewById(R.id.mtitle);
        release_date = itemView.findViewById(R.id.mcategory);
        duration = itemView.findViewById(R.id.mduration);
        movieImage = itemView.findViewById(R.id.movie_image);
        ratingBar = itemView.findViewById(R.id.mratingBar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
