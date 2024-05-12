package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button createNewAdvertButton;
    private Button showAllLostAndFoundItemsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this is your main activity layout file name

        // Initialize buttons from the layout
        createNewAdvertButton = findViewById(R.id.CreateNewAdvertbutton);
        showAllLostAndFoundItemsButton = findViewById(R.id.ShowAllLostAndFoundItems);

        // Set up the listener for the Create New Advert button
        createNewAdvertButton.setOnClickListener(view -> {
            // Start the CreateAdvertActivity
            Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
            startActivity(intent);
        });

        // Set up the listener for the Show All Lost And Found Items button
        showAllLostAndFoundItemsButton.setOnClickListener(view -> {
            // Start the ShowItemsActivity
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            startActivity(intent);
        });
    }
}
