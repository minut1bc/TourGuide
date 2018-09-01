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
public class LandmarksFragment extends Fragment {

    public LandmarksFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.arcul_de_triumf_name), getString(R.string.arcul_de_triumf_address), getString(R.string.arcul_de_triumf_start_hour), getString(R.string.arcul_de_triumf_stop_hour), (float) 4.5, R.drawable.arculdetriumf));
        locations.add(new Location(getString(R.string.palace_of_the_parliment_name), getString(R.string.palace_of_the_parliment_address), getString(R.string.palace_of_the_parliment_start_hour), getString(R.string.palace_of_the_parliment_stop_hour), (float) 3.5, R.drawable.palaceofparliment));
        locations.add(new Location(getString(R.string.romanian_atheneum_name), getString(R.string.romanian_atheneum_address), getString(R.string.romanian_atheneum_start_hour), getString(R.string.romanian_atheneum_stop_hour), (float) 5, R.drawable.romanianatheneum));
        locations.add(new Location(getString(R.string.carol_park_name), getString(R.string.carol_park_address), getString(R.string.carol_park_start_hour), getString(R.string.carol_park_stop_hour), (float) 5, R.drawable.carolpark));
        locations.add(new Location(getString(R.string.romanian_peoples_salvation_cathedral_name), getString(R.string.romanian_peoples_salvation_cathedral_address), getString(R.string.romanian_peoples_salvation_cathedral_start_hour), getString(R.string.romanian_peoples_salvation_cathedral_stop_hour), (float) 4, R.drawable.romania_peoplessalvationcathedral));

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
