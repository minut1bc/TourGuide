package com.example.android.tourguide;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<Location> {

    LocationAdapter(Activity context, ArrayList<Location> locations) {
        super(context, 0, locations);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        Location currentLocation = getItem(position);

        TextView nameTextView = gridItemView.findViewById(R.id.name_text_view);
        assert currentLocation != null;
        nameTextView.setText(currentLocation.getName());

        TextView addressTextView = gridItemView.findViewById(R.id.address_text_view);
        addressTextView.setText(currentLocation.getAddress());

        RatingBar ratingBar = gridItemView.findViewById(R.id.rating_bar);
        ratingBar.setRating(currentLocation.getRating());

        TextView openClosedStatusTextView = gridItemView.findViewById(R.id.open_closed_status_text_view);
        if (currentLocation.isOpen()) {
            openClosedStatusTextView.setText(R.string.open_now);
            openClosedStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorOpen));
        } else {
            openClosedStatusTextView.setText(R.string.closed);
            openClosedStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorClosed));
        }

        ImageView imageView = gridItemView.findViewById(R.id.image);
        imageView.setImageResource(currentLocation.getImageResourceId());

        return gridItemView;
    }
}
