package com.example.nearbyplace_assistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    Button btn_map;
    ImageButton img_alert_btn;
    ImageView img_user_btn;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 50;
    String[] phoneNumber;
    String phoneNumber1 = "100";
    DBHelper dbHelper = new DBHelper(MainActivity.this);
    UserDetail user;
    double lat, lng;
    private final static int REQUEST_CODE = 100;

    FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
            startLocationUpdate();
        } else {
            askPermission();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(lat + "" + lng);
        startLocationUpdate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 11);
            startLocationUpdate();
        }


        btn_map = findViewById(R.id.button);
        img_alert_btn = findViewById(R.id.img_alertbtn);
        img_user_btn = findViewById(R.id.usericon);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

            locationRequest = locationRequest.create();
            locationRequest.setInterval(1000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            System.out.println(location.getLatitude());
                            System.out.println(location.getLongitude());
                        }
                    }
                }
            };

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(openMapIntent);
            }
        });


        img_alert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startLocationUpdate();
                getCurrentLocation();
                System.out.println(lat+" "+lng);
                String message = "I'm in an emergency situation \n" +
                        " my location is latitude: "+lat+"\nLongitude: "+lng;
                getNumber();
                for(int i=0;i<3;i++)
                    sendSMSMessage(phoneNumber[i],message);
                callEmergency();
            }
        });

        img_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openUserProfile = new Intent(MainActivity.this,UserProfile.class);
                startActivity(openUserProfile);
            }
        });
    }


    protected void callEmergency(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
            try{
                String num = "tel:"+phoneNumber1;
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse(num));
                startActivity(call);
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},11);
            }
        }

    }

    protected void sendSMSMessage(String phoneNumber, String message)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
        {
            try
            {
                SmsManager smsMgrVar = SmsManager.getDefault();
                smsMgrVar.sendTextMessage(phoneNumber, null, message, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            }
            catch (Exception ErrVar)
            {
                Toast.makeText(getApplicationContext(),ErrVar.getMessage(),
                        Toast.LENGTH_LONG).show();
                ErrVar.printStackTrace();
            }
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 10);
            }
        }

    }


    private void getNumber(){
        user = dbHelper.getValue();
        phoneNumber = new String[]{user.getPhone_no1(), user.getPhone_no2(), user.getPhone_no3()};

    }

    public void startLocationUpdate()
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try{
            LocationServices.getFusedLocationProviderClient(MainActivity.this).requestLocationUpdates(locationRequest, locationCallback, null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }
    }

    private void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                {
                    Log.e("latitude: ", String.valueOf(location.getLatitude()));
                    Log.e("longitude: ", String.valueOf(location.getLongitude()));
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                }
                else
                {
                    System.out.println("location is null");
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[]permissions,@NonNull int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }

    }
}

