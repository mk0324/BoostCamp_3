package assignment.boostcamp.mymovieapp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import assignment.boostcamp.mymovieapp.adapter.MoviesAdapterContract;
import assignment.boostcamp.mymovieapp.data.ErrorResponse;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.model.MoviesModelCallback;
import assignment.boostcamp.mymovieapp.model.MoviesRetrofitModel;
import assignment.boostcamp.mymovieapp.retrofit.ResponseCode;

public class MoviesPresenter
    implements MoviesContract.Presenter, MoviesModelCallback.RetrofitCallback,
        OnItemClickListener, OnPositionListener {
    private MoviesContract.View view;
    private MoviesRetrofitModel retrofitModel;
    private MoviesAdapterContract.View adapterView;
    private MoviesAdapterContract.Model adapterModel;
    private String search;
    private int page;

    public MoviesPresenter() {
        retrofitModel = new MoviesRetrofitModel();
        retrofitModel.setCallback(this);
    }

    @Override
    public void getMovies(String search) {
        this.page = 1;
        this.search = search;
        adapterModel.clearItem();
        retrofitModel.getMovies(search, 1);
    }

    @Override
    public void onItemClick(Movie item) {
        view.onLinkToMovieDetail(item);
    }

    @Override
    public void onLoad(int page) {
        if(this.page == page) {
            view.toast("목록을 모두 가져왔습니다.");
            return;
        }
        this.page = page;
        retrofitModel.getMovies(search, (10*(page-1)) + 1);
    }

    @Override
    public void onSuccess(boolean researchResult, List<Movie> movies) {
        if(movies == null && researchResult == ResponseCode.RESULT_FAIL) {
            view.toast("검색 결과가 없습니다.");
            return;
        }else if(movies == null && researchResult == ResponseCode.RESULT_SUCCESS) {
            view.toast("목록을 모두 가져왔습니다.");
            return;
        }else {
            adapterModel.addItems(new ArrayList(movies));
            //view.onSuccessGetList();
            return;
        }
    }

    @Override
    public void onError(int code, ErrorResponse errorResponse){
        Log.d(errorResponse.getErrorCode(), errorResponse.getErrorMessage());
        String msg = "";

        if(code == ResponseCode.ERROR_400){
            msg = "검색 요청에 오류가 있습니다.";
        }else if(code == ResponseCode.ERROR_404){
            msg = "요청 URL이 잘못되었습니다.";
        }else if(code == ResponseCode.ERROR_500){
            msg = "서버 내부 에러가 발생했습니다.";
        }

        view.toast(msg);
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
