package assignment.boostcamp.mymovieapp.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import assignment.boostcamp.mymovieapp.R;
import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.presenter.MoviesPresenter;
import assignment.boostcamp.mymovieapp.presenter.OnItemClickListener;
import assignment.boostcamp.mymovieapp.presenter.OnPositionListener;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = MoviesViewHolder.class.getSimpleName();
    private OnItemClickListener onItemClickListener;
    private OnPositionListener onPositionListener;
    //protected MoviesPresenter presenter;

    @BindView(R.id.movie_list_view)
    ConstraintLayout movieList;
    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_rating)
    RatingBar movieRating;
    @BindView(R.id.movie_release_year)
    TextView movieReleaseYear;
    @BindView(R.id.movie_director)
    TextView movieDirector;
    @BindView(R.id.movie_actors)
    TextView movieActors;

    private Context context;

    public MoviesViewHolder(final Context context, ViewGroup parent, OnItemClickListener onItemClickListener, OnPositionListener onPositionListener) {
        super(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));
        ButterKnife.bind(this, itemView);

        // 으앙 헷갈령... 여기서 presenter 의 참조를 받아야 아이템 클릭시 presenter 로 전달이 되는뎅
        // 여기서 presenter 생성하면 presenter 가 여러개가 되니까..ㅠㅠㅠ
        //presenter = new MoviesPresenter();

        //홀더는 adapter 에서 전달된 onItemClickListener 를 참조
        //this.onItemClickListener = presenter;
        this.onItemClickListener = onItemClickListener;
        this.onPositionListener = onPositionListener;
        this.context = context;
    }

    public void onBind(Movie movie, int position, int listSize){

        Glide.with(context)
                .load(movie.getImage())
                .into(moviePoster);

        movieTitle.setText(movie.getTitle());
        movieRating.setRating((Float.valueOf(movie.getUserRating()))/2);
        movieReleaseYear.setText(movie.getPubDate());
        movieDirector.setText(movie.getDirector());
        movieActors.setText(movie.getActor());

        //얘는 adapter 가 아닌 presenter 로 전달된다..
        // 여기서 어떻게 onItemClick 이 presenter 로 전달될 수 있나?
        movieList.setOnClickListener(v -> onItemClickListener.onItemClick(movie));

        /*int page = 1;
        if(position == 9){
            page += 1;
            onPositionListener.onLoad(page);
        }else if(position == 0 && page != 1){
            page -= 1;
            onPositionListener.onLoad(page);
        }*/

        Log.d(TAG, "position " + position + " / max " + listSize);
    }
}
