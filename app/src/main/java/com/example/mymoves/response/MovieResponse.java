package com.example.mymoves.response;

import com.example.mymoves.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this class is for single movie request
public class MovieResponse {

    @SerializedName("results")
    @Expose()
    private MovieModel movie;

     public MovieModel getMovie(){
        return movie;
    }

}
