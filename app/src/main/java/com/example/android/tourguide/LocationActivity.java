package com.example.android.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.location);

        Location currentLocation = getIntent().getParcelableExtra(Constants.keyLocation);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(currentLocation.getName());
        }

        final TextView nameTextView = findViewById(R.id.name_text_view);
        nameTextView.setText(currentLocation.getName());

        TextView addressTextView = findViewById(R.id.address_text_view);
        addressTextView.setText(currentLocation.getAddress());
        addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Prepare the address string for Uri parsing
                String address = nameTextView.getText().toString();
                address = address.replace(" ", "+");

                // Create a Uri from an intent string. Use the result to create an Intent
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }
        });

        TextView startHourTextView = findViewById(R.id.start_hour_text_view);
        startHourTextView.setText(currentLocation.getStartHour());

        TextView stopHourTextView = findViewById(R.id.stop_hour_text_view);
        stopHourTextView.setText(currentLocation.getStopHour());

        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setRating(currentLocation.getRating());

        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(currentLocation.getImageResourceId());

    }

    // Ensures the Up Button behaves like the hardware Back Button, thus saving the state of the ViewPager
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }

}
