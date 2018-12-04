package assignment.boostcamp.mymovieapp.Retrofit;

import assignment.boostcamp.mymovieapp.Data.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/v1/search/movie.json")
    Call<Item> getMovies(
            @Header("X-Naver-Client-Id") String id,
            @Header("X-Naver-Client-Secret") String secret,
            @Query("query") String search
    );
}
