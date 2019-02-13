package com.waxtadpolegames.android.myplaces;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static IPlaceDAO places = new PlaceDAOList();
    private RecyclerView recyclerView;
    public PlaceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.edit_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new PlaceAdapter(this, places);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            launchAbout(null);
            return true;
        }
        if (id == R.id.action_search) {
            launchViewPlace(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchAbout(Object o) {
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    public void launchViewPlace(View view) {
        final EditText input = new EditText(this);
        input.setText("0");
        new AlertDialog.Builder(this)
                .setTitle(R.string.selection_place_title)
                .setMessage(R.string.selection_place_message)
                .setView(input)
                .setPositiveButton(R.string.dialog_positive_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        long id = Long.parseLong(input.getText().toString());
                        Intent i = new Intent(MainActivity.this, ViewPlaceActivity.class);
                        i.putExtra("data_id", id);
                        startActivity(i);
                    }
                })
                .setNegativeButton(R.string.dialog_negative_text, null)
                .show();
    }
}
