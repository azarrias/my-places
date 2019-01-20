package com.waxtadpolegames.android.myplaces;

import java.util.Iterator;

public interface IPlaceDAO extends Iterable<Place> {
    Place get(int id);
    void add(Place place);
    int create();
    void delete(int id);
    int size();

    @Override
    Iterator<Place> iterator();

    void update(int id, Place place);
}
