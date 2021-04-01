package com.example.mymoves.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.PrivateKey;

public class MovieModel implements Parcelable {

    private String title;
    private String poster_path;
    private String release_date;
    private int id;
    private int runtime;
    private float vote_average;
    private String movie_overview;

    public MovieModel() {
    }


    public MovieModel(String title,
                      String poster_path,
                      String release_date,
                      int id, int runtime,
                      float vote_average,
                      String movie_overview) {

        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.runtime = runtime;
        this.vote_average = vote_average;
        this.movie_overview = movie_overview;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        runtime = in.readInt();
        vote_average = in.readFloat();
        movie_overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeInt(runtime);
        dest.writeFloat(vote_average);
        dest.writeString(movie_overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public int getRuntime() {
        return runtime;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getMovie_overview() {
        return movie_overview;
    }
}
