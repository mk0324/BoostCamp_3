package assignment.boostcamp.mymovieapp.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import assignment.boostcamp.mymovieapp.R;
import assignment.boostcamp.mymovieapp.data.Movie;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = MoviesViewHolder.class.getSimpleName();
    private OnItemClickListener onItemClickListener;
    private OnPositionListener onPositionListener;

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

        movieList.setOnClickListener(v -> onItemClickListener.onItemClick(movie, position));

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
