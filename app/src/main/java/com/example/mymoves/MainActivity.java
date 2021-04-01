package com.example.mymoves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Service;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mymoves.adapters.MoviesRecyclerView;
import com.example.mymoves.adapters.OnMovieListener;
import com.example.mymoves.models.MovieModel;
import com.example.mymoves.requests.Services;
import com.example.mymoves.response.MovieSearchResponse;
import com.example.mymoves.utils.Credentials;
import com.example.mymoves.utils.MovieApi;
import com.example.mymoves.viewModel.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;

    private MoviesRecyclerView moviesRecyclerView;


      MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mrecyclerview);

        movieListViewModel = new ViewModelProvider
                (this)
                .get(MovieListViewModel.class);

        SetupSearchView();
       // SearchMovieApi("f", "1") ;
        observeAnyChange();


        ConfigureRecyclerView();



    }


    private void observeAnyChange(){

        movieListViewModel.getMovies().
                observe(this,
                        new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels)
            {

                if(movieModels!=null){
                    for(MovieModel movieModel : movieModels){
                        moviesRecyclerView.setmMovies(movieModels);



                    }
                }

            }
        });


    }

  /*  private void SearchMovieApi(String query, String pageNumber){

        movieListViewModel.searchMovieApi(query,pageNumber);
    }*/

    private void ConfigureRecyclerView(){
        moviesRecyclerView = new MoviesRecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesRecyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                   movieListViewModel.searchNextPage();
                }
            }
        });

    }

    @Override
    public void onMovieClick(int position) {

       // Toast.makeText(this, "the position"+ position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String category) {

    }

    public void SetupSearchView(){
  try{
      final SearchView searchView  =
              findViewById(R.id.search_view);
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              movieListViewModel.searchMovieApi(
                      query
                      ,"1");
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              return false;
          }
      }) ;
  }catch (Exception e){
      Log.d("errrrror",e.getMessage().toString());
  }


    }

  /*  private void getRetrofitResponse() {

        MovieApi movieApi = Services
                .getMovieApi();
        Call<MovieSearchResponse> responseCall
                = movieApi.searchMovie(
                Credentials.API_KEY,
                "Jack Reacher",
                "1"
        );

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse
                    (Call<MovieSearchResponse> call,
                     Response<MovieSearchResponse> response) {
                if(response.code() == 200)
                {
                    //view the response in the logcat
                    Log.d("Tag","the response " + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>
                            (response.body().getMovies());

                    for(MovieModel movie: movies){

                        Log.d("Tag", "the list " +
                                movie.getRelease_date());
                    }

                }
                else {
                    try {
                        Log.d("error", "The error " + response.errorBody().string());
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure
                    (Call<MovieSearchResponse> call,
                     Throwable t) {

t.printStackTrace();
            }
        });


    }

    private void getRetrofitResponseAccordingToID(){
        MovieApi  movieApi = Services.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                500,
                Credentials.API_KEY
        );
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call,
                                   Response<MovieModel> response) {

                if(response.code() == 200){
                    MovieModel movieModel = response.body();
                    Log.v("Tag","The Response "+movieModel
                    .getTitle());
                }
                else {
                    try{
                 Log.v("Tag", "error "+response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }*/
}