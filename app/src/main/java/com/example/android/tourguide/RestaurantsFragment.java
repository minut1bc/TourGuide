package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Erra Restaurant", "Street Constantin C. Nottara 13, Bucharest", "04:00 PM", "10:00 PM", (float) 5, R.drawable.errarestaurant));
        locations.add(new Location("Nor Sky Casual Restaurant", "Street Barbu Văcărescu 201, Bucharest", "10:00 AM", "00:00 AM", (float) 4, R.drawable.norskycasualrestaurant));
        locations.add(new Location("Trattoria Monza", "Street Baba Novac 25, Bucharest", "10:00 AM", "00:00 AM", (float) 4, R.drawable.trattoriamonza));
        locations.add(new Location("Camizo Restaurant", "Street Prisaca Dornei 10A, Bucharest", "10:00 AM", "11:00 PM", (float) 4.5, R.drawable.camizorestaurant));
        locations.add(new Location("Snap Bar & Lounge", "Boulevard Decebal 13, Bucharest", "Non", "Stop", (float) 5, R.drawable.snapbarandlounge));

        LocationAdapter adapter = new LocationAdapter(getActivity(), locations);

        GridView gridView = rootView.findViewById(R.id.grid);

        gridView.setAdapter(adapter);

        // Set a click listener to open the location page when the grid item is clicked on
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Get the {@link Location} object at the given position the user clicked on
                Location location = locations.get(position);

                Intent intent = new Intent(getContext(), LocationActivity.class);
                intent.putExtra("location", location);

                startActivity(intent);

            }
        });

        return rootView;

    }
}
