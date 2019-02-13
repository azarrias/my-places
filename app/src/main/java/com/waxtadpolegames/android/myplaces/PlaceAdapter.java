package com.waxtadpolegames.android.myplaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    protected IPlaceDAO places;         // List of places to display
    protected LayoutInflater inflater;  // Creates layouts from XML specs
    protected Context context;          // Needed by the inflater

    public PlaceAdapter(Context context, IPlaceDAO places) {
        this.context = context;
        this.places = places;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_places, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Place place = places.get(i);
        customizeView(viewHolder, place);
    }

    private void customizeView(ViewHolder holder, Place place) {
        holder.name.setText(place.getName());
        holder.address.setText(place.getAddress());

        int id = R.drawable.otros;
        switch(place.getType()) {
            case RESTAURANT:    id = R.drawable.restaurante; break;
            case BAR:           id = R.drawable.bar; break;
            case CAFE:          id = R.drawable.copas; break;
            case THEATER:       id = R.drawable.espectaculos; break;
            case HOTEL:         id = R.drawable.hotel; break;
            case SHOPPING:      id = R.drawable.compras; break;
            case EDUCATION:     id = R.drawable.educacion; break;
            case SPORTS:        id = R.drawable.deporte; break;
            case PARK:          id = R.drawable.naturaleza; break;
            case GAS_STATION:   id = R.drawable.gasolinera; break;
        }
        holder.image.setImageResource(id);
        holder.image.setScaleType(ImageView.ScaleType.FIT_END);
        holder.ratingBar.setRating(place.getRating());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address;
        public ImageView image;
        public RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.place_name);
            address = itemView.findViewById(R.id.place_address);
            image = itemView.findViewById(R.id.place_picture);
            ratingBar = itemView.findViewById(R.id.place_rating);
        }
    }
}
