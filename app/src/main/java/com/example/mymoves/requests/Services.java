package com.example.mymoves.requests;

import com.example.mymoves.utils.Credentials;
import com.example.mymoves.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 //Api generator class
public class Services {


    private static Retrofit.Builder retrofitBuilder
            = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory
                    (GsonConverterFactory.create());


    private static  Retrofit retrofit
            = retrofitBuilder.build();

  //Singleton pattern
    private static MovieApi movieApi
    = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
