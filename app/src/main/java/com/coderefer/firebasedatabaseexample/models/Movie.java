package com.coderefer.firebasedatabaseexample.models;

/**
 * Created by vamsi on 18-Jul-16.
 */

public class Movie {

    public String movieName;
    public String moviePoster;
    public float movieRating;

    public Movie(){

    }
    public Movie(String movieName,String moviePoster,float movieRating){
        this.movieName = movieName;
        this.moviePoster = moviePoster;
        this.movieRating  = movieRating;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public float getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }
}
