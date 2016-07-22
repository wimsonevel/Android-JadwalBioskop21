package example.wim.androidretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import example.wim.androidretrofit.R;
import example.wim.androidretrofit.listener.RecyclerViewItemClickListener;
import example.wim.androidretrofit.model.MovieData;

/**
 * Created by Wim on 7/20/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    private List<MovieData> movieDataList;
    private Context context;

    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDataList = new ArrayList<>();
    }

    private void add(MovieData item) {
        movieDataList.add(item);
        notifyItemInserted(movieDataList.size() - 1);
    }

    public void addAll(List<MovieData> movieDataList) {
        for (MovieData movieData : movieDataList) {
            add(movieData);
        }
    }

    public void remove(MovieData item) {
        int position = movieDataList.indexOf(item);
        if (position > -1) {
            movieDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public MovieData getItem(int positon){
        return movieDataList.get(positon);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);

        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = movieViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerViewItemClickListener != null) {
                        recyclerViewItemClickListener.onItemClick(adapterPos, movieViewHolder.itemView);
                    }
                }
            }
        });
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final MovieData movieData = movieDataList.get(position);

        Picasso.with(context)
                .load(movieData.getPoster())
                .into(holder.poster);

        holder.title.setText(Html.fromHtml(movieData.getMovie()));
        holder.genre.setText(context.getResources().getString(R.string.genre, movieData.getGenre()));
        holder.duration.setText(context.getResources().getString(R.string.duration, movieData.getDuration()));
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;
        TextView genre;
        TextView duration;

        public MovieViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
            genre = (TextView) itemView.findViewById(R.id.genre);
            duration = (TextView) itemView.findViewById(R.id.duration);
        }
    }
}
