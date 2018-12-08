package assignment.boostcamp.mymovieapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import assignment.boostcamp.mymovieapp.R;
import assignment.boostcamp.mymovieapp.adapter.MoviesAdapter;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.presenter.MoviesContract;
import assignment.boostcamp.mymovieapp.presenter.MoviesPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesContract.View{

    @BindView(R.id.search_bar)
    public EditText searchBar;
    @BindView(R.id.search_button)
    public Button searchBtn;
    @BindView(R.id.search_result_recycler_view)
    RecyclerView searchResultRecycleView;

    protected MoviesAdapter adapter;
    // protected MoviesPresenter presenter; 와 차이 알기
    protected MoviesContract.Presenter presenter;
    private String searchStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // presenter 는 MoviesContract.Presenter 내부 메소드만 참조사용가능
        // 왜 범위 제한을 두었는지?
        // 다른 클래스에 대한 presenter 메소드에 접근제한을 두기 위해
        presenter = new MoviesPresenter();
        // attachView 를 하는 이유?
        //
        presenter.attachView(this);

        adapter = new MoviesAdapter(this);

        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);

        searchResultRecycleView.setLayoutManager(new LinearLayoutManager(this));
        searchResultRecycleView.setAdapter(adapter);

        initView();
    }

    private void initView(){
        searchBtn.setOnClickListener(v -> {
            searchStr = searchBar.getText().toString();
            InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);
            if(searchStr.equals("")){
                // 검색어 없음 -> 아직보류
                // refreshList(null);
                return;
            }
            refreshList(searchStr);
        });
    }

    protected void refreshList(String search){
        presenter.getMovies(search);
    }

    @Override
    public void onLinkToMovieDetail(Movie movie) {
        // 여기서 매개 변수인 OnItemClickListener 에 대한 구현이 이루어진다
        // OnItemClickListener 의 onClick 메서드에 대한 구현
        // adapter 에서 setOnClickListener 의 매개 변수를 OnItemClickListener 로 사용하도록 override 했음
        //adapter.setOnClickListener((item) -> {
        // 여기서 listener 가 또 구현되어 있어서 에러가 있었음
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getLink()));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
        //});
    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void onUnauthorizedError() {

    }

    @Override
    public void onUnknownError() {

    }

    @Override
    public void onSuccessGetList() {

    }

    @Override
    public void onConnectFail() {

    }

    @Override
    public void onNotFound() {

    }
}
