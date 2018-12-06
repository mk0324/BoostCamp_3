package assignment.boostcamp.mymovieapp.model;

import java.util.List;

import assignment.boostcamp.mymovieapp.data.Movie;

public interface MoviesModelCallback {
    interface RetrofitCallback{
        void onSuccess(List<Movie> movies);
        void onFailure();
    }
}
