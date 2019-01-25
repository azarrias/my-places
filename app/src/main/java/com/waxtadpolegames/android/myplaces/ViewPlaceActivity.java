package com.waxtadpolegames.android.myplaces;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        if (place.getAddress().isEmpty()) {
            findViewById(R.id.address_logo).setVisibility(View.GONE);
            findViewById(R.id.address).setVisibility(View.GONE);
        } else {
            //findViewById(R.id.address).setVisibility(View.VISIBLE);
            TextView address = findViewById(R.id.address);
            address.setText(place.getAddress());
        }

        if (place.getPhone().isEmpty()) {
            findViewById(R.id.phone_logo).setVisibility(View.GONE);
            findViewById(R.id.phone).setVisibility(View.GONE);
        } else {
            TextView phone = findViewById(R.id.phone);
            phone.setText(place.getPhone());
        }

        if (place.getUrl().isEmpty()) {
            findViewById(R.id.url_logo).setVisibility(View.GONE);
            findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            TextView url = findViewById(R.id.url);
            url.setText(place.getUrl());
        }

        if (place.getComment().isEmpty()) {
            findViewById(R.id.comment_logo).setVisibility(View.GONE);
            findViewById(R.id.comment).setVisibility(View.GONE);
        } else {
            TextView comment = findViewById(R.id.comment);
            comment.setText(place.getComment());
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_place, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                return true;
            case R.id.action_directions:
                return true;
            case R.id.action_edit:
                editPlace((int)id);
                return true;
            case R.id.action_delete:
                deletePlace((int)id);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deletePlace(final int id) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_place_title)
                .setMessage(R.string.delete_place_message)
                .setPositiveButton(R.string.dialog_positive_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        MainActivity.places.delete(id);
                        finish();
                    }
                })
                .setNegativeButton(R.string.dialog_negative_text, null)
                .show();
    }

    public void editPlace(final long id) {
        Intent i = new Intent(this, EditPlaceActivity.class);
        i.putExtra("data_id", id);
        startActivity(i);
    }
}
