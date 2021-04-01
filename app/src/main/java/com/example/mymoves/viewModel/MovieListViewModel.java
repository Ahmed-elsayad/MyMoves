package com.example.mymoves.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymoves.models.MovieModel;
import com.example.mymoves.repository.MoviesRepo;

import java.util.List;

public class MovieListViewModel extends ViewModel {

 private static MoviesRepo moviesRepo;

    public MovieListViewModel()
    {
       moviesRepo = MoviesRepo.getInstance();
    }

public static LiveData<List<MovieModel>> getMovies()
{
        return moviesRepo.getMovies();
}

public void searchMovieApi (String query, String pageNumber){
        moviesRepo.searchMovieApi(query,pageNumber);
}

public  void searchNextPage()
{
 moviesRepo.searchNextPage();
}





}
