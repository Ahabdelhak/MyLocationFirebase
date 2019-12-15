package com.example.ahabdelhak.otocabtask.Activities;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapActvity_fun {

    //Add users Marker
//    void AddUsersMarkers(final GoogleMap googleMap){
//        DatabaseReference f_database = FirebaseDatabase.getInstance().getReference("Locations");
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//
//                        Locations loc = snapshot.getValue(Locations.class);
//
//                        double lat = Double.parseDouble(loc.lat);
//                        double lng = Double.parseDouble(loc.lng);
//                        LatLng location = new LatLng(lat,lng);
//                        googleMap.addMarker(new MarkerOptions().position(location).title(loc.email).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        f_database.addListenerForSingleValueEvent(listener);
//    }
    //get current Location -Admin Location

    void GetCurrentLocations(GoogleMap googleMap,double lat , double lng) {
        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Me");
        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(9));
    }

    void RefreshMap(Context context , GoogleMap mMap){
    mMap.clear();
    Toast.makeText(context, "Updating..", Toast.LENGTH_SHORT).show();
    }

}
