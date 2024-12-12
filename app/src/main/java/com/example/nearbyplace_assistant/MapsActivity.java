package com.example.nearbyplace_assistant;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nearbyplace_assistant.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    int PROXIMITY_RADIUS = 5000;
    double lat, lng;
    Button btn_hosp,btn_pol,btn_fire,btn_railway,btn_hos;
    ImageButton btn_curLocation;
    Marker marker,marker2;
    LocationRequest locationRequest;
    ArrayList<Marker> marker3 = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(MapsActivity.this);


    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            // Toast.makeText(getApplicationContext()," location result is  " + locationResult, Toast.LENGTH_LONG).show();
            CurrentLocation.latitude = lat;
            CurrentLocation.longitude = lng;

            if (locationResult == null) {
                Toast.makeText(getApplicationContext(), "current location is null ", Toast.LENGTH_LONG).show();
                return;
            }
            for (Location location : locationResult.getLocations()) {
                if (location != null) {

                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    if (marker != null)
                        marker.remove();

                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("current location");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder3));
                    marker = mMap.addMarker(markerOptions);
                    Log.e("Location", lat + " " + lng);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn_hosp = findViewById(R.id.button_hos);
        btn_curLocation = findViewById(R.id.Img_Locate);
        btn_pol = findViewById(R.id.button2);
        btn_fire = findViewById(R.id.button3);
        btn_railway = findViewById(R.id.btn_marker);
        btn_hos = findViewById(R.id.button4);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationRequest = locationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());


        //method for getting current location
        btn_curLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });

        btn_hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMarker();
                Toast.makeText(MapsActivity.this,"Searching",Toast.LENGTH_LONG).show();

                for (int i=0;i<3;i++){
                    HosInfo hosInfo = null;
                    hosInfo = dbHelper.getHosValue(i);
                    Log.d("db value",dbHelper.getHosValue(i).getName());
                    System.out.println(hosInfo.toString());
                    String hosName = hosInfo.getName();
                    double lat = hosInfo.getLat();
                    double lng = hosInfo.getLng();
                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(hosName);
                    markerOptions.snippet(hosInfo.getNumber());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                    marker2 = mMap.addMarker(markerOptions);
                    marker3.add(marker2);
                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,24));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14),2200,null);
                }
            }
        });

        btn_pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMarker();
                Toast.makeText(MapsActivity.this,"Searching",Toast.LENGTH_LONG).show();

                for (int i=0;i<2;i++){
                    PolInfo polInfo = null;
                    polInfo = dbHelper.getPolValue(i);
                    System.out.println(polInfo.toString());
                    String polName = polInfo.getPolName();
                    double lat = polInfo.getPolLat();
                    double lng = polInfo.getPolLng();
                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(polName);
                    markerOptions.snippet(polInfo.getPolNum());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                    marker2 = mMap.addMarker(markerOptions);
                    marker3.add(marker2);
                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,24));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14),2200,null);

                }
            }
        });

        btn_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMarker();
                Toast.makeText(MapsActivity.this,"Searching",Toast.LENGTH_LONG).show();

                for (int i=0;i<1;i++){
                    FireStInfo fireInfo = null;
                    fireInfo = dbHelper.getFireValue(i);
                    System.out.println(fireInfo.toString());
                    String hosName = fireInfo.getFireName();
                    double lat = fireInfo.getFireLat();
                    double lng = fireInfo.getFireLng();
                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(fireInfo.getFireName());
                    markerOptions.snippet(fireInfo.getFireNum());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                    marker2 = mMap.addMarker(markerOptions);
                    marker3.add(marker2);
                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,24));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14),2200,null);

                }
            }
        });

        btn_railway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMarker();
                Toast.makeText(MapsActivity.this,"Searching",Toast.LENGTH_LONG).show();

                if(marker2 != null)
                    marker2.remove();
                for (int i=0;i<1;i++){
                    RailwayInfo railwayInfo = null;
                    railwayInfo = dbHelper.getRailwayValue(i);
                    System.out.println(railwayInfo.toString());
                    String railName = railwayInfo.getRailName();
                    double lat = railwayInfo.getRailLat();
                    double lng = railwayInfo.getRailLng();
                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(railName);
                    markerOptions.snippet(railwayInfo.getRailNum());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
                    marker2 = mMap.addMarker(markerOptions);
                    marker2.showInfoWindow();
                    marker3.add(marker2);
                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,24));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14),2200,null);

                }
            }
        });

        btn_hos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringBuffer stringBuffer = new StringBuffer("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuffer.append("location" + lat + "," + lng);
                stringBuffer.append("&radius=" + PROXIMITY_RADIUS);
                stringBuffer.append("&type=hospital");
                stringBuffer.append("&sensor=true");
                stringBuffer.append("&key=" + getResources().getString(R.string.map_api));
                String placeName = "hospital";

                String url = getUrl(lat, lng, placeName);
                Object dataFetch[] = new Object[2];
                dataFetch[0] = mMap;
                dataFetch[1] = url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }

        });
    }


    private String getUrl(double lat, double lng, String placeName) {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + lat + "," + lng);
        googlePlaceUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type=" + placeName);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + getResources().getString(R.string.map_api));

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCurrentLocation();
        checkSettingsAndStartLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdate();
    }

    private void stopLocationUpdate() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsRequestTask = client.checkLocationSettings(request);

        locationSettingsRequestTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdates();
            }
        });
        locationSettingsRequestTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(MapsActivity.this, 1010);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getCurrentLocation();
    }



    //gets the current location
    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    if (marker != null)
                        marker.remove();

                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    Toast.makeText(getApplicationContext(), "current location is "
                                    + location.getLatitude() + " " + location.getLongitude(),
                            Toast.LENGTH_LONG).show();

                    LatLng latLng = new LatLng(lat, lng);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title("current location");
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder3));
                    marker = mMap.addMarker(markerOptions);
                    // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,24));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15),2200,null);
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (REQUEST_CODE) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
                break;
        }

    }

    private void removeMarker(){
        for(Marker marker : marker3)
            marker.remove();
    }

}