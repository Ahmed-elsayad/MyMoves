package com.example.mymoves.response;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.mymoves.AppExecutors;
import com.example.mymoves.models.MovieModel;
import com.example.mymoves.requests.Services;
import com.example.mymoves.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;


public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> movies;
    private static MovieApiClient instance;

    //Global Runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;


    private MovieApiClient(){
        movies = new MutableLiveData<>();
    }

    public static MovieApiClient getInstance() {
        if(instance == null){
            return instance = new MovieApiClient();
        }
        return instance;
    }

    public LiveData <List<MovieModel>> getMovies() {
        return movies;
    }

  public  void searchMovieApi(String query ,String pageNumber ){

        if(retrieveMoviesRunnable !=null){
           retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable =
                new RetrieveMoviesRunnable(query,pageNumber);

        final Future myHandler = AppExecutors.
                getInstance().networkIO().
                submit(retrieveMoviesRunnable);

         AppExecutors.getInstance().networkIO().
                 schedule(new Runnable() {
             @Override
             public void run() {
                 // cancel request
                 myHandler.cancel(true);
             }
         },4000, TimeUnit.MILLISECONDS);



  }
    //dar retrieving data from restApi by runnable class

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private String pageNumber;
        boolean cancelRequest ;


        public RetrieveMoviesRunnable
                (String query , String pageNumber){
            this.query = query;
            this.pageNumber =  pageNumber;
            cancelRequest = false;

        }

        @Override
        public void run() {

            try {
                Response response = getMovies(query,pageNumber)
                        .execute();

                if(cancelRequest){
                    return;
                }

                if(response.code()==200){
                    List<MovieModel> list = new ArrayList<>
                            (((MovieSearchResponse)response.body())
                                    .getMovies());

                   //send data to liveData
                    if(pageNumber == "1"){
                        movies.postValue(list);
                    }

                    else{
                        List<MovieModel> currentMovies = movies.
                                getValue();
                        currentMovies.addAll(list);
                        movies.postValue(currentMovies);
                    }

                }
                else {

                    String error = response.errorBody().string();

                    Log.v("Tag","error "+error);
                    movies.postValue(null);
                     }

            } catch (IOException e) {
                e.printStackTrace();
            }



        }


        private Call<MovieSearchResponse> getMovies
                (String query , String pageNumber){
            return Services.getMovieApi().
                    searchMovie(Credentials.API_KEY,
                            query, pageNumber);


        }

    }

}
