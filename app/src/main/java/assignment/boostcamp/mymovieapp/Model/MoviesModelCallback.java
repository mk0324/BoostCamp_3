package assignment.boostcamp.mymovieapp.model;

import java.util.List;

import assignment.boostcamp.mymovieapp.data.ErrorResponse;
import assignment.boostcamp.mymovieapp.data.Movie;

public interface MoviesModelCallback {
    interface RetrofitCallback{
        void onSuccess(int code, List<Movie> movies);
        void onError(int code, ErrorResponse errorResponse);
        void onFailure();
    }
}
