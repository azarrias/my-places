package com.waxtadpolegames.android.myplaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlaceDAOList implements IPlaceDAO {
    protected List<Place> placesVector = examplePlaces();

    public PlaceDAOList(){
        placesVector = examplePlaces();
    }

    @Override
    public Place get(int id) {
        return placesVector.get(id);
    }

    @Override
    public void add(Place place) {
        placesVector.add(place);
    }

    @Override
    public int create() {
        Place place = new Place();
        placesVector.add(place);
        return placesVector.size() - 1;
    }

    @Override
    public void delete(int id) {
        placesVector.remove(id);
    }

    @Override
    public int size() {
        return placesVector.size();
    }

    @Override
    public Iterator<Place> iterator() {
        return placesVector.iterator();
    }

    @Override
    public void update(int id, Place place) {
        placesVector.set(id, place);
    }

    public static ArrayList<Place> examplePlaces(){
        ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place("Empire state building",
                "20 W 34th St, New York, NY 10001, EE. UU.",
                -73.9878531,
                40.7484405,
                TypePlace.OTHERS,
                "+1 212-736-3100",
                "http://www.esbnyc.com/",
                "Beautiful view. Great historical building one of my favorites. Absolutely Love NYC!! Will return soon.",
                5));
        places.add(new Place("Eiffel Tower",
                "Champ de Mars, 5 Avenue Anatole France, 75007 Paris, France",
                2.2922926,
                48.8583701,
                TypePlace.OTHERS,
                "+33 892 70 12 39",
                "https://www.toureiffel.paris/en",
                "Worth the wait and trip up to the top.",
                5));
        places.add(new Place("El celler de Can Roca",
                "Carrer de Can Sunyer, 48, 17007 Girona",
                2.805826,
                41.9933774,
                TypePlace.RESTAURANT,
                "972 22 21 57",
                "https://cellercanroca.com/",
                "Best menu ever ...",
                5));
        places.add(new Place("HR Giger Bar Museum",
                "Rue du Château 2, 1630 Gruyères, Switzerland",
                7.0823665,
                46.584187,
                TypePlace.BAR,
                "+41 26 921 08 00",
                "http://www.hrgiger.com",
                "A beer with the aliens",
                3));
        places.add(new Place("Bibliotheca Alexandrina",
                "El Shatby, Alexandria 30100, Egypt",
                29.9070125,
                31.2088705,
                TypePlace.EDUCATION,
                "+20 3 4839999",
                "http://www.bibalex.org/en/default",
                "Knowledge hub!",
                4));

        return places;
    }
}
