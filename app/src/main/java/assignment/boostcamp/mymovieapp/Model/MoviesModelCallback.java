package assignment.boostcamp.mymovieapp.Model;

import java.util.List;

import assignment.boostcamp.mymovieapp.Data.Movie;

public interface MoviesModelCallback {
    interface RetrofitCallback{
        void onSuccess(List<Movie> movies);
        void onFailure();
    }
}
