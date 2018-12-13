package assignment.boostcamp.mymovieapp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import assignment.boostcamp.mymovieapp.data.ErrorResponse;
import assignment.boostcamp.mymovieapp.retrofit.ResponseCode;
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
                //여러가지 에러코드 처리
                if(response.isSuccessful()) {
                    if(response.body().getTotal() == 0){
                        callback.onSuccess(response.code(), null);
                    }
                    List<Movie> movies = response.body().getItems();
                    callback.onSuccess(response.code(), movies);
                } else {
                    Gson gson = new Gson();
                    ErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);

                    callback.onError(response.code(), errorResponse);
                    return;
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onFailure();
            }
        });
    }
}
