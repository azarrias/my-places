package com.waxtadpolegames.android.myplaces;

class MyPlaces {
    public static void main(String[] args) {
        IPlaceDAO places = new PlaceDAOList();
        for (Place place : places) {
            System.out.println(place.toString());
        }
    }
}
