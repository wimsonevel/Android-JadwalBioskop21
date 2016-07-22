package example.wim.androidretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.wim.androidretrofit.R;
import example.wim.androidretrofit.model.Showtime;
import example.wim.androidretrofit.util.FlowLayout;

/**
 * Created by Wim on 7/21/16.
 */
public class ShowtimeListAdapter extends RecyclerView.Adapter<ShowtimeListAdapter.ShowtimeViewHolder>{

    private List<Showtime> showtimeList;
    private Context context;

    public ShowtimeListAdapter(Context context) {
        this.context = context;
        showtimeList = new ArrayList<>();
    }

    private void add(Showtime item) {
        showtimeList.add(item);
        notifyItemInserted(showtimeList.size() - 1);
    }

    public void addAll(List<Showtime> showtimeList) {
        for (Showtime showtime : showtimeList) {
            add(showtime);
        }
    }

    public void remove(Showtime item) {
        int position = showtimeList.indexOf(item);
        if (position > -1) {
            showtimeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Showtime getItem(int positon){
        return showtimeList.get(positon);
    }

    @Override
    public ShowtimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_showtime, parent, false);
        return new ShowtimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowtimeViewHolder holder, int position) {
        final Showtime showtime = showtimeList.get(position);

        holder.theater.setText(showtime.getBioskop());

        for (int i=0; i<showtime.getJam().size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_time, holder.lyTime, false);
            TextView time = (TextView) view.findViewById(R.id.time);

            time.setText(showtime.getJam().get(i));

            holder.lyTime.addView(view);
        }
        holder.price.setText(showtime.getHarga());
    }

    @Override
    public int getItemCount() {
        return showtimeList.size();
    }

    static class ShowtimeViewHolder extends RecyclerView.ViewHolder {

        TextView theater;
        FlowLayout lyTime;
        TextView price;

        public ShowtimeViewHolder(View itemView) {
            super(itemView);

            theater = (TextView) itemView.findViewById(R.id.theater);
            lyTime = (FlowLayout) itemView.findViewById(R.id.lyTime);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
