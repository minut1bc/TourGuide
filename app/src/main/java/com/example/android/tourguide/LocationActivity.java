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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Location location = getIntent().getParcelableExtra("location");

        actionBar.setTitle(location.getName());

        final TextView nameTextView = findViewById(R.id.name_text_view);
        nameTextView.setText(location.getName());

        TextView addressTextView = findViewById(R.id.address_text_view);
        addressTextView.setText(location.getAddress());
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
        startHourTextView.setText(location.getStartHour());

        TextView stopHourTextView = findViewById(R.id.stop_hour_text_view);
        stopHourTextView.setText(location.getStopHour());

        RatingBar ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setRating(location.getRating());

        ImageView imageView = findViewById(R.id.image);
        imageView.setImageResource(location.getImageResourceId());

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
