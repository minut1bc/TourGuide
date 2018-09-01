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
public class RestaurantsFragment extends Fragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.location_grid, container, false);

        final ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(getString(R.string.erra_restaurant_name), getString(R.string.erra_restaurant_address), getString(R.string.erra_restaurant_start_hour), getString(R.string.erra_restaurant_stop_hour), (float) 5, R.drawable.errarestaurant));
        locations.add(new Location(getString(R.string.nor_sky_name), getString(R.string.nor_sky_address), getString(R.string.nor_sky_start_hour), getString(R.string.nor_sky_stop_hour), (float) 4, R.drawable.norskycasualrestaurant));
        locations.add(new Location(getString(R.string.trattoria_monza_name), getString(R.string.trattoria_monza_address), getString(R.string.trattoria_monza_start_hour), getString(R.string.trattoria_monza_stop_hour), (float) 4, R.drawable.trattoriamonza));
        locations.add(new Location(getString(R.string.camizo_restaurant_name), getString(R.string.camizo_restaurant_address), getString(R.string.camizo_restaurant_start_hour), getString(R.string.camizo_restaurant_stop_hour), (float) 4.5, R.drawable.camizorestaurant));
        locations.add(new Location(getString(R.string.snap_bar_lounge_name), getString(R.string.snap_bar_lounge_address), getString(R.string.snap_bar_lounge_start_hour), getString(R.string.snap_bar_lounge_stop_hour), (float) 5, R.drawable.snapbarandlounge));

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
