package assignment.boostcamp.mymovieapp.adapter;

import java.util.ArrayList;

public interface MoviesAdapterContract {
    interface View{
        void setOnClickListener(OnItemClickListener onClickListener);
        void setOnPositionListener(OnPositionListener onPositionListener);
        void notifyAdapter();
    }

    interface Model{
        ArrayList getItems();
        void setItems(ArrayList items);
        void addItems(ArrayList items);
        void clearItem();
    }
}
