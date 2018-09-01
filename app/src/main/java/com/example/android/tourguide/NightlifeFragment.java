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
public class NightlifeFragment extends Fragment {

    public NightlifeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.silver_church_name), getString(R.string.silver_church_address), getString(R.string.silver_church_start_hour), getString(R.string.silver_church_stop_hour), (float) 5, R.drawable.silverchurch));
        locations.add(new Location(getString(R.string.funky_lounge_herastrau_name), getString(R.string.funky_lounge_herastrau_address), getString(R.string.funky_lounge_herastrau_start_hour), getString(R.string.funky_lounge_herastrau_stop_hour), (float) 4.5, R.drawable.funkyloungeherastrau));
        locations.add(new Location(getString(R.string.the_vintage_pub_name), getString(R.string.the_vintage_pub_address), getString(R.string.the_vintage_pub_start_hour), getString(R.string.the_vintage_pub_stop_hour), (float) 4, R.drawable.thevintagepub));
        locations.add(new Location(getString(R.string.jacks_pub_name), getString(R.string.jacks_pub_address), getString(R.string.jacks_pub_start_hour), getString(R.string.jacks_pub_stop_hour), (float) 4, R.drawable.jackspub));
        locations.add(new Location(getString(R.string.club_a_name), getString(R.string.club_a_address), getString(R.string.club_a_start_hour), getString(R.string.club_a_stop_hour), (float) 3.5, R.drawable.cluba));

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
