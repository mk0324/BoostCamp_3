package assignment.boostcamp.mymovieapp.presenter;

import java.util.ArrayList;
import java.util.List;

import assignment.boostcamp.mymovieapp.adapter.MoviesAdapterContract;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.model.MoviesModelCallback;
import assignment.boostcamp.mymovieapp.model.MoviesRetrofitModel;

public class MoviesPresenter
    implements MoviesContract.Presenter, MoviesModelCallback.RetrofitCallback,
        OnItemClickListener, OnPositionListener {
    private MoviesContract.View view;
    private MoviesRetrofitModel retrofitModel;
    private MoviesAdapterContract.View adapterView;
    private MoviesAdapterContract.Model adapterModel;
    private String search;

    public MoviesPresenter() {
        retrofitModel = new MoviesRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void getMovies(String search) {
        this.search = search;
        adapterModel.clearItem();
        retrofitModel.getMovies(search, 10, 1);
    }

    @Override
    public void onItemClick(Movie item) {
        view.onLinkToMovieDetail(item);
    }

    @Override
    public void onLoad(int page) {
        retrofitModel.getMovies(search, 10, 10*page);
    }

    @Override
    public void onSuccess(List<Movie> movies) {
        adapterModel.addItems(new ArrayList(movies));
        view.onSuccessGetList();
        return;
    }

    @Override
    public void onFailure() {
        view.onConnectFail();
    }

    @Override
    public void attachView(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void setAdapterView(MoviesAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnClickListener(this);
        this.adapterView.setOnPositionListener(this);
    }

    @Override
    public void setAdapterModel(MoviesAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }
}
