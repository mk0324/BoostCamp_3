package assignment.boostcamp.mymovieapp.Adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import assignment.boostcamp.mymovieapp.Data.Movie;
import assignment.boostcamp.mymovieapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
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

    public MoviesViewHolder(final Context context, ViewGroup parent, OnItemClickListener onItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false));
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    public void onBind(Movie movie, int position, int listSize){
        
    }
}
