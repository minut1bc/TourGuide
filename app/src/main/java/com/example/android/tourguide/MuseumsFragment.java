package com.example.android.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        locations.add(new Location(getString(R.string.national_musem_of_contemporany_art_name), getString(R.string.national_musem_of_contemporany_art_address), getString(R.string.national_musem_of_contemporany_art_start_hour), getString(R.string.national_musem_of_contemporany_art_stop_hour), (float) 5, R.drawable.nationalmuseumofcontemporaryart));
        locations.add(new Location(getString(R.string.national_museum_of_romanian_history_name), getString(R.string.national_museum_of_romanian_history_address), getString(R.string.national_museum_of_romanian_history_start_hour), getString(R.string.national_museum_of_romanian_history_stop_hour), (float) 4.5, R.drawable.nationalmuseumofromanianhistory));
        locations.add(new Location(getString(R.string.bucharest_municipality_musuem_name), getString(R.string.bucharest_municipality_musuem_address), getString(R.string.bucharest_municipality_musuem_start_hour), getString(R.string.bucharest_municipality_musuem_stop_hour), (float) 4, R.drawable.bucharestmunicipalitymuseum));
        locations.add(new Location(getString(R.string.romanian_peasant_museum_name), getString(R.string.romanian_peasant_museum_address), getString(R.string.romanian_peasant_museum_start_hour), getString(R.string.romanian_peasant_museum_stop_hour), (float) 5, R.drawable.romanianpeasantmuseum));
        locations.add(new Location(getString(R.string.grigore_antipa_national_museum_of_natural_history_name), getString(R.string.grigore_antipa_national_museum_of_natural_history_address), getString(R.string.grigore_antipa_national_museum_of_natural_history_start_hour), getString(R.string.grigore_antipa_national_museum_of_natural_history_stop_hour), (float) 5, R.drawable.grigoreantipanationalmuseumofnaturalhistory));

        LocationAdapter adapter = new LocationAdapter(getActivity(), locations);
        adapter.setHasStableIds(true);

        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                // Get the {@link Location} object at the given position the user clicked on
                Location currentLocation = locations.get(position);

                Intent intent = new Intent(getContext(), LocationActivity.class);
                intent.putExtra(Constants.keyLocation, currentLocation);

                startActivity(intent);
            }
        }));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);

        return rootView;

    }
}
