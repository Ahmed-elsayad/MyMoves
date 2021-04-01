package com.example.mymoves.utils;

import com.example.mymoves.models.MovieModel;
import com.example.mymoves.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //search for movies
   // https://api.themoviedb.org/3/search/movie?api_key=8e481140b5671331108439cb9f6ff601&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie (
             @Query("api_key") String key ,
             @Query("query")   String query,
             @Query("page") String pageNUm
    );

    // making search with id
    //https://api.themoviedb.org/3/movie/550?api_key=8e481140b5671331108439cb9f6ff601

    @GET("/3/movie/{movie_id}?")
    Call<MovieModel>getMovie(
        @Path("movie_id") int movie_id,
        @Query("api_key") String api_key
    );

}
