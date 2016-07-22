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
import example.wim.androidretrofit.listener.RecyclerViewItemClickListener;
import example.wim.androidretrofit.model.CityData;

/**
 * Created by Wim on 7/21/16.
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder>{

    private List<CityData> cityDataList;
    private Context context;

    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public CityListAdapter(Context context) {
        this.context = context;
        cityDataList = new ArrayList<>();
    }

    private void add(CityData item) {
        cityDataList.add(item);
        notifyItemInserted(cityDataList.size() - 1);
    }

    public void addAll(List<CityData> cityDataList) {
        for (CityData cityData : cityDataList) {
            add(cityData);
        }
    }

    public void remove(CityData item) {
        int position = cityDataList.indexOf(item);
        if (position > -1) {
            cityDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public CityData getItem(int positon){
        return cityDataList.get(positon);
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        final CityViewHolder cityViewHolder = new CityViewHolder(view);

        cityViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = cityViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerViewItemClickListener != null) {
                        recyclerViewItemClickListener.onItemClick(adapterPos, cityViewHolder.itemView);
                    }
                }
            }
        });

        return cityViewHolder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        final CityData cityData = cityDataList.get(position);

        holder.city.setText(cityData.getKota());
    }

    @Override
    public int getItemCount() {
        return cityDataList.size();
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView city;

        public CityViewHolder(View itemView) {
            super(itemView);

            city = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
