package assignment.boostcamp.mymovieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import assignment.boostcamp.mymovieapp.data.Movie;
import assignment.boostcamp.mymovieapp.presenter.MoviesContract;
import assignment.boostcamp.mymovieapp.presenter.MoviesPresenter;
import assignment.boostcamp.mymovieapp.presenter.OnItemClickListener;
import assignment.boostcamp.mymovieapp.presenter.OnPositionListener;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder>
    implements MoviesAdapterContract.View, MoviesAdapterContract.Model{

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private ArrayList<Movie> items;
    private OnItemClickListener onItemClickListener;
    private OnPositionListener onPositionListener;
    //protected MoviesPresenter presenter;
    private Context context;

    public MoviesAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(context, parent, onItemClickListener, onPositionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        if(holder == null)
            return;
        holder.onBind(items.get(position), position, getItemCount());
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    @Override
    public void setOnClickListener(OnItemClickListener onClickListener) {
        //this.onItemClickListener = presenter;
        this.onItemClickListener = onClickListener;
    }

    @Override
    public void setOnPositionListener(OnPositionListener onPositionListener) {
        this.onPositionListener = onPositionListener;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public ArrayList getItems() {
        return items;
    }

    public Movie getItem(int position) {
        return items.get(position);
    }

    @Override
    public void setItems(ArrayList items) {
        //Log.d(TAG, "setItems");
        this.items.clear();
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void clearItem() {
        items.clear();
        notifyDataSetChanged();
    }
}
