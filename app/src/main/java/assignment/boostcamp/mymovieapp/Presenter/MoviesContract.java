package assignment.boostcamp.mymovieapp.presenter;

import assignment.boostcamp.mymovieapp.adapter.MoviesAdapterContract;
import assignment.boostcamp.mymovieapp.data.Movie;

public interface MoviesContract {
    interface View{
        void toast(String msg);
        void onConnectFail();
        void onLinkToMovieDetail(Movie movie);
    }

    interface Presenter{
        void getMovies(String search);
        void attachView(View view);
        void detachView();
        void setAdapterView(MoviesAdapterContract.View adapterView);
        void setAdapterModel(MoviesAdapterContract.Model adapterModel);
    }
}
