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
public class MuseumsFragment extends Fragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location("National Museum of Contemporary Art", "Street Izvor 2-4, Bucharest", "12:00 PM", "08:00 PM", (float) 5, R.drawable.nationalmuseumofcontemporaryart));
        locations.add(new Location("National Museum of Romanian History", "Calea Victoriei 12, Bucharest", "10:00 AM", "06:00 PM", (float) 4.5, R.drawable.nationalmuseumofromanianhistory));
        locations.add(new Location("Bucharest Municipality Museum", "Boulevard Ion C. Brătianu 2, Bucharest", "10:00 AM", "06:00 PM", (float) 4, R.drawable.bucharestmunicipalitymuseum));
        locations.add(new Location("Romanian Peasant Museum", "Highway Pavel Dimitrievici Kiseleff 3", "09:00 AM", "05:00 PM", (float) 5, R.drawable.romanianpeasantmuseum));
        locations.add(new Location("Grigore Antipa National Museum of Natural History", "Highway Pavel Dimitrievici Kiseleff 1", "10:00 AM", "08:00 PM", (float) 5, R.drawable.grigoreantipanationalmuseumofnaturalhistory));

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
