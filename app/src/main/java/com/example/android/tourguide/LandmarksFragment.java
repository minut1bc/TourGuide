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
public class LandmarksFragment extends Fragment {


    public LandmarksFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Arcul de Triumf", "Arcul de Triumf Plaza, Bucharest", "Non", "Stop", (float) 4.5, R.drawable.arculdetriumf));
        locations.add(new Location("Palace of the Parliament", "Street Izvor 2-4, Bucharest", "09:00 AM", "05:00 PM", (float) 3.5, R.drawable.palaceofparliment));
        locations.add(new Location("Romanian Athenaeum", "Street Benjamin Franklin 1-3, Bucharest", "06:00 PM", "11:00 PM", (float) 5, R.drawable.romanianatheneum));
        locations.add(new Location("Carol Park", "Boulevard Mărășești, Bucharest", "Non", "Stop", (float) 5, R.drawable.carolpark));
        locations.add(new Location("Romanian People's Salvation Cathedral", "Calea 13 Septembrie 6-40, Bucharest", "Non", "Stop", (float) 4, R.drawable.romania_peoplessalvationcathedral));

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
