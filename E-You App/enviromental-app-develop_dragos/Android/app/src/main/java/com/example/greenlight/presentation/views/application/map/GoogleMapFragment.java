package com.example.greenlight.presentation.views.application.map;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.greenlight.R;
import com.example.greenlight.data.models.IncidentsModel;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.presentation.viewmodel.MapViewModel;
import com.example.greenlight.presentation.views.dialogs.TurnOnLocationDialog;
import com.example.greenlight.utils.Converters;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


public class GoogleMapFragment extends Fragment {
    @Inject
    public MapViewModel mapViewModel;

    private final int DELAY = 3 * 1000; //
    private GoogleMap mMap;
    private boolean mapReady = false;
    private ImageView mapActionButton;
    private ImageView goToMyLocationButton;
    private boolean isPermissionGranter;
    private LocationManager locationManager;
    private final int MIN_LOCATION_TIME_UPDATE = 1000; // milliseconds
    private final float MIN_DISTANCE_UPDATE = 0.0f; //meters
    private Marker myPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_google_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map_fragment);

        mapFragment.getMapAsync(googleMap -> {
                    mMap = googleMap;
                    mMap.setOnMarkerClickListener(markerClickListener);
                    mapReady = true;
                    checkPermission(rootView);
                    init();
                }
        );

        return rootView;
    }

    private void init() {

        if (mMap != null) {
            mMap.clear();

            Location location = mapViewModel.getMyLocation().getValue();
            if (location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                addMyLocation(latLng);
            }
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        setupListeners(view);

        //init();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        stopUpdateLocation();
    }

    @Override
    public void onResume() {
        if (locationManager != null) {
            startUpdateLocationUpdate();
        }

        super.onResume();
    }

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(@NonNull Marker marker) {
            if (!marker.getTitle().equals("myLocation")) {
                mapViewModel.updateSelectedIncidentId(marker.getTitle());
                Navigation.findNavController(requireView()).navigate(R.id.action_googleMapFragment2_to_infoMarkerFragment);
            }
            return false;
        }
    };

    private void addMyLocation(LatLng latLng) {
        if (mMap != null) {
            myPosition = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("myLocation")
                    .icon(Converters.BitmapFromVector(requireContext(), R.drawable.ic_my_location, 10)));
        }
    }


    private void setupListeners(@NonNull View createdView) {

        mapActionButton = createdView.findViewById(R.id.mapActionButtonImage);

        mapActionButton.setOnClickListener(view -> {
            Navigation.findNavController(createdView).navigate(R.id.action_googleMapFragment2_to_reportActionFragment);
        });

        goToMyLocationButton.setOnClickListener(v -> {
            moveCameraToMyLocation();
        });
        locationManager = (LocationManager) createdView.getContext().getSystemService(LOCATION_SERVICE);

        mapViewModel.getMyLocation().observe(requireActivity(), location -> {
            LatLng newLocation = new LatLng(location.getLatitude(), location.getLongitude());
            try {
                if (myPosition == null) {
                    myPosition = mMap.addMarker(new MarkerOptions()
                            .title("myLocation")
                            .position(newLocation)
                            .icon(Converters.BitmapFromVector(requireContext(), R.drawable.ic_my_location, 10))
                    );
                } else {
                    myPosition.setPosition(newLocation);
                    myPosition.setTitle("myLocation");
                }
            } catch (NullPointerException nullPointerException) {
                Log.e("LOC_UPD", nullPointerException.getMessage());
            }
        });

        mapViewModel.getAllIncidents().observe(requireActivity(), incidentResponse -> {
            switch (incidentResponse.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    break;
                case SUCCESS:
                    Log.e("AAA",incidentResponse.getData().getData().toString());
                    addToView(incidentResponse.getData().getData(), getContext());
                    break;
                case ERROR:
                    Toast.makeText(getContext(), "Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.", Toast.LENGTH_LONG).show();
                    Log.e("PHOTOS", incidentResponse.getError().toString());
                    break;
            }
        });

        mapViewModel.getPostedIncidentResponse().observe(requireActivity(), incidentResponse -> {
            switch (incidentResponse.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    break;
                case SUCCESS:
                    Toast.makeText(getContext(),"Placed successfully",Toast.LENGTH_LONG).show();
                    mapViewModel.sendPhotoResponse.setValue(Resource.initial());
                    mapViewModel.incidentResponse.setValue(Resource.initial());
                    mapViewModel.requestIncidents(mapViewModel.getAuthToken());
                    break;
                case ERROR:
                    mapViewModel.sendPhotoResponse.setValue(Resource.initial());
                    mapViewModel.incidentResponse.setValue(Resource.initial());
                    Toast.makeText(getContext(), "Unable to reach connection, " +
                            "please ensure the access to the internet or try again later.", Toast.LENGTH_LONG).show();
                    Log.e("PHOTOS",incidentResponse.getError().toString());
                    break;
            }
        });

        mapViewModel.solveIncidentResponse.observe(requireActivity(), solveIncidentResponseResource -> {
            switch (solveIncidentResponseResource.getType()) {
                case INITIAL:
                    break;
                case LOADING:
                    break;
                case SUCCESS:

                    Toast.makeText(getContext(), "Successfully resolved!", Toast.LENGTH_LONG).show();
                    mapViewModel.solveIncidentResponse.setValue(Resource.initial());
                    mapViewModel.requestIncidents(mapViewModel.getAuthToken());
                    break;
                case ERROR:

                    mapViewModel.solveIncidentResponse.setValue(Resource.initial());
                    Toast.makeText(getContext(), "Error to solve incident, try again later.",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        });
        mapViewModel.requestIncidents(mapViewModel.getAuthToken());
    }

    private void moveCameraToMyLocation() {
        Location location = mapViewModel.getMyLocation().getValue();

        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(18.0f));
        }
    }

    private void addToView(List<IncidentsModel> list, Context context) {
        try {
            if (mMap != null) {
                mMap.clear();
                Location location = mapViewModel.getMyLocation().getValue();
                if(location  != null) {
                LatLng latLng2 = new LatLng(location.getLatitude(),location.getLongitude());
                addMyLocation(latLng2);
                }
                for (IncidentsModel i : list) {
                    double lat = Double.parseDouble(i.getIncident().getLatitude());
                    double longitude = Double.parseDouble(i.getIncident().getLongitude());
                    String markerType = i.getIncident().getMarkerType();
                    LatLng latLng = new LatLng(lat, longitude);
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title(i.getDocumentId())
                            .icon(Converters.BitmapFromVector(requireActivity(),
                                    Converters.getMarkerIcon(markerType),
                                    10)));
                }
            }
        } catch (Exception exception) {
            Log.e("ADD_MARKER", exception.getMessage());
        }

    }

    private void setupViews(@NonNull View view) {
        mapActionButton = view.findViewById(R.id.mapActionButtonImage);
        goToMyLocationButton = view.findViewById(R.id.goToMyLocation);
    }

    private void setupMap() {
        if (mapReady) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            UiSettings uis = mMap.getUiSettings();
            uis.setZoomControlsEnabled(true);
            uis.setMyLocationButtonEnabled(true);
            uis.setScrollGesturesEnabled(true);
            uis.setZoomGesturesEnabled(true);
            uis.setZoomControlsEnabled(false);
            mMap.clear();
        }
    }

    private void checkPermission(View view) {
        Dexter.withContext(view.getContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new PermissionListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        isPermissionGranter = true;
                        if (!canGetLocation(view)) {
                            new TurnOnLocationDialog(view.getContext()).showDialog();
                        }
                        startUpdateLocationUpdate();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            mapViewModel.updateMyLocation(location);
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
            Log.e("PROVIDER ON", "ON " + provider);
            startUpdateLocationUpdate();
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
            Log.e("PROVIDER OFF", "OFF " + provider);
            stopUpdateLocation();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("STATUS CHANGE ", "" + provider + " " + status);
        }
    };


    @SuppressLint("MissingPermission")
    private void startUpdateLocationUpdate() {
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_LOCATION_TIME_UPDATE,
                    MIN_DISTANCE_UPDATE, locationListener);
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_LOCATION_TIME_UPDATE,
                    MIN_DISTANCE_UPDATE, locationListener);
        }
    }

    private void stopUpdateLocation() {
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public boolean canGetLocation(@NonNull View view) {
        LocationManager lm;
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        lm = (LocationManager) view.getContext().getSystemService(Context.LOCATION_SERVICE);

        // exceptions will be thrown if provider is not permitted.
        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            networkEnabled = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        return gpsEnabled && networkEnabled;
    }


}
