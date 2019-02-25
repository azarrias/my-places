package com.waxtadpolegames.android.myplaces;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ViewPlaceActivity extends AppCompatActivity {

    private long id;
    private Place place;

    final static int REQUEST_CODE_EDIT = 1;
    final static int REQUEST_CODE_GALLERY = 2;

    private LinearLayout llAddress;
    private LinearLayout llPhone;
    private LinearLayout llUrl;
    private ImageView galleryLogo;
    private ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        Bundle extras = getIntent().getExtras();
        id = extras.getLong("data_id", -1);

        llAddress = findViewById(R.id.ll_address);
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap(v);
            }
        });

        llUrl = findViewById(R.id.ll_url);
        llUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebSite(v);
            }
        });

        llPhone = findViewById(R.id.ll_phone);
        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall(v);
            }
        });

        galleryLogo = findViewById(R.id.gallery);
        galleryLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(v);
            }
        });

        ivPicture = findViewById(R.id.pic);

        updateViews();
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
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, place.getName() + " - " + place.getUrl());
                startActivity(intent);
                return true;
            case R.id.action_directions:
                showMap(null);
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

    private void showMap(View view) {
        Uri uri;
        double latitude = place.getPosition().getLatitude();
        double longitude = place.getPosition().getLongitude();

        if (latitude != 0 || longitude != 0) {
            uri = Uri.parse("geo:" + latitude + "," + longitude);
        } else {
            uri = Uri.parse("geo:0,0?q=" + place.getAddress());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_EDIT) {
            updateViews();
            // force to re-paint this view
            findViewById(R.id.scroll_view_1).invalidate();
        } else if (data != null && requestCode == REQUEST_CODE_GALLERY) {
            place.setPhoto(data.getDataString());
            setPicture(ivPicture, place.getPhoto());
        } else {
            Toast.makeText(this, "Picture not loaded", Toast.LENGTH_LONG).show();
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
        startActivityForResult(i, REQUEST_CODE_EDIT);
    }

    public void phoneCall(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + place.getPhone())));
    }

    public void openWebSite(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(place.getUrl())));
    }

    public void openGallery(View view) {
        String action;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            action = Intent.ACTION_OPEN_DOCUMENT;
        } else {
            action = Intent.ACTION_PICK;
        }

        Intent intent = new Intent(action, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    protected void setPicture(ImageView imageView, String uri) {
        if (uri != null && !uri.isEmpty() && !uri.equals("null")) {
            imageView.setImageURI(Uri.parse(uri));
        } else {
            imageView.setImageBitmap(null);
        }
    }

    public void updateViews() {
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

        setPicture(ivPicture, place.getPhoto());
    }
}
