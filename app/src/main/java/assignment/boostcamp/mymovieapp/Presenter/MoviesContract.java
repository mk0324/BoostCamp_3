package assignment.boostcamp.mymovieapp.Presenter;

import assignment.boostcamp.mymovieapp.Adapter.MoviesAdapterContract;
import assignment.boostcamp.mymovieapp.Data.Movie;

public interface MoviesContract {
    interface View{
        void toast(String msg);
        void onUnauthorizedError();
        void onUnknownError();
        void onSuccessGetList();
        void onConnectFail();
        void onLinkToMovieDetail(Movie item);
        void onNotFound();
    }

    interface Presenter{
        void getMovies(String search);
        void attachView(View view);
        void detachView();
        void setAdapterView(MoviesAdapterContract.View adapterView);
        void setAdapterModel(MoviesAdapterContract.Model adapterModel);
    }
}
