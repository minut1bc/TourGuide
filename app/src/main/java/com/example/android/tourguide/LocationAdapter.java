package com.example.android.tourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private Context context;
    private ArrayList<Location> locations;

    LocationAdapter(Context context, ArrayList<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);

        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder locationViewHolder, int position) {
        // Bind data to the view

        Location currentLocation = locations.get(position);

        locationViewHolder.nameTextView.setText(currentLocation.getName());
        locationViewHolder.addressTextView.setText(currentLocation.getAddress());
        locationViewHolder.ratingBar.setRating(currentLocation.getRating());

        if (currentLocation.isOpen()) {
            locationViewHolder.openClosedStatusTextView.setText(R.string.open_now);
            locationViewHolder.openClosedStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.colorOpen));
        } else {
            locationViewHolder.openClosedStatusTextView.setText(R.string.closed);
            locationViewHolder.openClosedStatusTextView.setTextColor(ContextCompat.getColor(context, R.color.colorClosed));
        }

        locationViewHolder.locationImageView.setImageResource(currentLocation.getImageResourceId());

    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        RatingBar ratingBar;
        TextView openClosedStatusTextView;
        ImageView locationImageView;

        LocationViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.name_text_view);
            this.addressTextView = itemView.findViewById(R.id.address_text_view);
            this.ratingBar = itemView.findViewById(R.id.rating_bar);
            this.openClosedStatusTextView = itemView.findViewById(R.id.open_closed_status_text_view);
            this.locationImageView = itemView.findViewById(R.id.image);
        }

    }

}
