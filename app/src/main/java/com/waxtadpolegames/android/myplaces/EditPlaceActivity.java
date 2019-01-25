package com.waxtadpolegames.android.myplaces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class EditPlaceActivity extends AppCompatActivity {
    private long id;
    private Place place;
    private EditText name;
    private Spinner type;
    private EditText address;
    private EditText phone;
    private EditText url;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("data_id", -1);
        place = MainActivity.places.get((int)id);

        name = findViewById(R.id.et_name);
        name.setText(place.getName());

        address = findViewById(R.id.et_address);
        address.setText(place.getAddress());

        phone = findViewById(R.id.et_phone);
        phone.setText(place.getPhone());

        url = findViewById(R.id.et_url);
        url.setText(place.getUrl());

        comment = findViewById(R.id.et_comment);
        comment.setText(place.getComment());
    }
}
