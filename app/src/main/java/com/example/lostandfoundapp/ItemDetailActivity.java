package com.example.lostandfoundapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ItemDetailActivity extends AppCompatActivity {

    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final String TAG = "ItemDetailActivity";

    private EditText editTextName, editTextPhone, editTextDescription, editTextDate, editTextLocation;
    private RadioGroup radioGroupType;
    private Button buttonGetCurrentLocation, saveButton;
    private FusedLocationProviderClient fusedLocationClient;
    private DatabaseHelper dbHelper;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        dbHelper = new DatabaseHelper(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        radioGroupType = findViewById(R.id.radioGroupType);
        editTextName = findViewById(R.id.editTextItemName);
        editTextPhone = findViewById(R.id.editTextItemPhone);
        editTextDescription = findViewById(R.id.editTextItemDescription);
        editTextDate = findViewById(R.id.editTextItemDate);
        editTextLocation = findViewById(R.id.editTextItemLocation);
        buttonGetCurrentLocation = findViewById(R.id.buttonGetCurrentLocation);
        saveButton = findViewById(R.id.SaveButton);

        // Initialize Places SDK
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

        // Set up the location autocomplete
        editTextLocation.setOnClickListener(v -> {
            Log.d(TAG, "Location field clicked");
            List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(this);
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
        });

        buttonGetCurrentLocation.setOnClickListener(v -> getCurrentLocation());

        saveButton.setOnClickListener(v -> saveItem());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            editTextLocation.setText(place.getName());
            LatLng latLng = place.getLatLng();
            if (latLng != null) {
                latitude = latLng.latitude;
                longitude = latLng.longitude;
            }
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Toast.makeText(this, "Error in getting place", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(ItemDetailActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (addresses != null && !addresses.isEmpty()) {
                            Address address = addresses.get(0);
                            editTextLocation.setText(address.getAddressLine(0));
                            latitude = address.getLatitude();
                            longitude = address.getLongitude();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void saveItem() {
        String type = ((RadioButton) findViewById(radioGroupType.getCheckedRadioButtonId())).getText().toString();
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();
        String location = editTextLocation.getText().toString();

        Item item = new Item(name, phone, description, date, location, type, latitude, longitude);
        dbHelper.addItem(item);

        Toast.makeText(this, "Item saved successfully", Toast.LENGTH_SHORT).show();
        finish(); // Go back to the previous activity
    }
}

