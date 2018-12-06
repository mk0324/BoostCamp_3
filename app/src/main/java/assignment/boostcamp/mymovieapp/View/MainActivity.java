package assignment.boostcamp.mymovieapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import assignment.boostcamp.mymovieapp.R;
import assignment.boostcamp.mymovieapp.adapter.MoviesAdapter;
import assignment.boostcamp.mymovieapp.adapter.MoviesViewHolder;
import assignment.boostcamp.mymovieapp.adapter.OnItemClickListener;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.presenter.MoviesContract;
import assignment.boostcamp.mymovieapp.presenter.MoviesPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MoviesContract.View{

    @BindView(R.id.search_bar)
    public EditText searchBar;
    @BindView(R.id.search_button)
    public Button searchBtn;
    @BindView(R.id.search_result_recycler_view)
    RecyclerView searchResultRecycleView;

    protected MoviesAdapter adapter;
    protected MoviesContract.Presenter presenter;
    private String searchStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MoviesPresenter();
        presenter.attachView(this);

        adapter = new MoviesAdapter(this);

        presenter.setAdapterModel(adapter);
        presenter.setAdapterView(adapter);

        searchResultRecycleView.setLayoutManager(new LinearLayoutManager(this));
        searchResultRecycleView.setAdapter(adapter);

        initView();
        //onLinkToMovieDetail();
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
    public void onLinkToMovieDetail() {
        adapter.setOnClickListener((holder, view, position) -> {
            Movie item = adapter.getItem(position);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
        });
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
