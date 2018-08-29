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
public class NightlifeFragment extends Fragment {


    public NightlifeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("Silver Church", "Calea Plevnei 61, Bucharest", "10:00 PM", "05:00 AM", (float) 5, R.drawable.silverchurch));
        locations.add(new Location("Funky Lounge Herăstrău", "Boulevard Beijing, Bucharest", "03:00 PM", "02:30 AM", (float) 4.5, R.drawable.funkyloungeherastrau));
        locations.add(new Location("The Vintage Pub", "Street Smârdan 43, Bucharest", "03:00 PM", "05:30 AM", (float) 4, R.drawable.thevintagepub));
        locations.add(new Location("Jack's Pub", "Street Lipscani 45, Bucharest", "10:00 AM", "05:00 AM", (float) 4, R.drawable.jackspub));
        locations.add(new Location("Club A", "Street Blănari 14, Bucharest", "01:00 PM", "05:00 AM", (float) 3.5, R.drawable.cluba));

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
