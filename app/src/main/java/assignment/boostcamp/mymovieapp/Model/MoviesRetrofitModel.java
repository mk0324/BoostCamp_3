package assignment.boostcamp.mymovieapp.model;

import java.util.List;

import assignment.boostcamp.mymovieapp.retrofit.RetrofitServiceManager;
import assignment.boostcamp.mymovieapp.data.MoviesResponse;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRetrofitModel {
    private MoviesModelCallback.RetrofitCallback callback;
    private RetrofitService retrofitService;

    public MoviesRetrofitModel() {
        retrofitService = RetrofitServiceManager.getInstance();
    }

    public void setCallback(MoviesModelCallback.RetrofitCallback callback){
        this.callback = callback;
    }

    public void getMovies(String search, int display, int start){
        String client_id = "Xgltfccu6rti3kKussJg";
        String client_secret = "zLXeNw3XZy";
        Call<MoviesResponse> call = retrofitService.getMovies(client_id, client_secret, search, display, start);
        call.enqueue(new Callback<MoviesResponse>(){
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
               /* if (response.code() == ResponseCode.SE01) {
                    callback.onSuccess(ResponseCode.SE01, null);
                    return;
                }
                if (response.code() == ResponseCode.SE05) {
                    callback.onSuccess(ResponseCode.SE05, null);
                    return;
                }
                if (response.code() == ResponseCode.SE06) {
                    callback.onSuccess(ResponseCode.SE06, null);
                    return;
                }
                if (response.code() == ResponseCode.SE99) {
                    callback.onSuccess(ResponseCode.SE99, null);
                    return;
                }*/
                List<Movie> movies = response.body().getItems();
                callback.onSuccess(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
