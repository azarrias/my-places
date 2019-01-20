package com.waxtadpolegames.android.myplaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class ViewPlaceActivity extends AppCompatActivity {
    private long id;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("data_id", -1);
        place = MainActivity.places.get((int)id);

        TextView name = findViewById(R.id.name);
        name.setText(place.getName());
        ImageView typeLogo = findViewById(R.id.type_logo);
        typeLogo.setImageResource(place.getType().getResource());
        TextView type = findViewById(R.id.type);
        type.setText(place.getType().getText());
        TextView address = findViewById(R.id.address);
        address.setText(place.getAddress());
        TextView phone = findViewById(R.id.phone);
        phone.setText(place.getPhone());
        TextView url = findViewById(R.id.url);
        url.setText(place.getUrl());
        TextView comment = findViewById(R.id.comment);
        comment.setText(place.getComment());
        TextView date = findViewById(R.id.date);
        date.setText(DateFormat.getDateInstance().format(new Date(place.getDate())));
        TextView time = findViewById(R.id.time);
        time.setText(DateFormat.getTimeInstance().format(new Date(place.getDate())));
        RatingBar rating = findViewById(R.id.rating);
        rating.setRating(place.getRating());
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                place.setRating(rating);
            }
        });
    }
}
