package com.example.diana.hotels;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diana.hotels.model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 08-Nov-17.
 */

public class HotelsRepo extends RecyclerView.Adapter<HotelsRepo.ViewHolder> {

    List<Hotel> hotels;
    HotelsRepoListener hotelsRepoListener;

    public HotelsRepo(@Nullable HotelsRepoListener hotelsRepoListener) {
        hotels = new ArrayList<>();
        populateHotelList();
        this.hotelsRepoListener = hotelsRepoListener;
    }

    private void populateHotelList() {
        this.hotels.add(new Hotel("aa", "cluj"));
        this.hotels.add(new Hotel("aaaa", "buc"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_row, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hotel currentHotel = hotels.get(position);
        holder.hotelName.setText(currentHotel.getName());
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hotelName;

        public ViewHolder(View itemView) {
            super(itemView);
            hotelName = (TextView) itemView.findViewById(R.id.hotel_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hotelsRepoListener != null) {
                        hotelsRepoListener.onHotelSelected(getAdapterPosition());
                    }
                }
            });
        }
    }
}
