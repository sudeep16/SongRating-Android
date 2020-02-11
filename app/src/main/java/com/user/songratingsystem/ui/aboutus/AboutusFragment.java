package com.user.songratingsystem.ui.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.user.songratingsystem.R;

public class AboutusFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mapView;
    TextView detail;


    public AboutusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        detail = view.findViewById(R.id.detail);
        detail.setText("Song Rating System is an application which tries to focus in songs that are liked by users and tries to recommend them about the latest songs and try to receive their porsitive response.");


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapView = googleMap;

        CameraUpdate center, zoom;
        center = CameraUpdateFactory.newLatLng(new LatLng(27.7177913, 85.2968171));
        zoom = CameraUpdateFactory.zoomTo(10);

        mapView.addMarker(new MarkerOptions().position(new LatLng(27.7177913, 85.2968171)).title("Song Rating Server"));
        mapView.moveCamera(center);
        mapView.animateCamera(zoom);
        mapView.getUiSettings().setZoomControlsEnabled(true);
    }
}
