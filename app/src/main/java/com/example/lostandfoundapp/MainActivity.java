package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button createNewAdvertButton, showAllLostAndFoundItemsButton, showOnMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNewAdvertButton = findViewById(R.id.CreateNewAdvertbutton);
        showAllLostAndFoundItemsButton = findViewById(R.id.ShowAllLostAndFoundItems);
        showOnMapButton = findViewById(R.id.ShowOnMapButton);

        createNewAdvertButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
            startActivity(intent);
        });

        showAllLostAndFoundItemsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            startActivity(intent);
        });

        showOnMapButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });
    }
}
