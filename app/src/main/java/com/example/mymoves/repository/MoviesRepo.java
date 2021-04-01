package com.example.mymoves.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mymoves.models.MovieModel;
import com.example.mymoves.response.MovieApiClient;

import java.util.List;

public class MoviesRepo {

    private MovieApiClient movieApiClient;

    private static  MoviesRepo moviesRepo;

    private String mQuery;
    private String mPageNumber;


    private MoviesRepo(){

        movieApiClient = MovieApiClient.getInstance();

    }

    public static MoviesRepo getInstance() {
        if(moviesRepo == null){
            moviesRepo = new MoviesRepo();
        }
        return moviesRepo;
    }

    public LiveData <List<MovieModel>> getMovies()
    {
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query,String pageNumber){

        mQuery=query;
        mPageNumber=pageNumber;
        movieApiClient.searchMovieApi(query,pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }

}
