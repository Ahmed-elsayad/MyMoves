package com.example.mymoves.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymoves.R;
import com.example.mymoves.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    public MoviesRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_rv_item,
                parent,false);
        return new MoviesViewHolder(view,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MoviesViewHolder)holder).title.
                setText(mMovies.get(position).getTitle());
        ((MoviesViewHolder)holder).release_date.
                setText(mMovies.get(position).getRelease_date());
        ((MoviesViewHolder)holder).duration.
                setText(String.valueOf(mMovies.get(position).getRuntime()));

        ((MoviesViewHolder)holder).ratingBar
                .setRating((mMovies.get(position).getVote_average())/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(position).getPoster_path())
                .into(((MoviesViewHolder)holder).movieImage);

    }

    @Override
    public int getItemCount() {
        if(mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies ( List<MovieModel>mMovies){
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
}