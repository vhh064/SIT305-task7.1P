package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveITem extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription, textViewPhone, textViewDate, textViewLocation, textViewType;
    private Button buttonDelete;
    private DatabaseHelper dbHelper;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_item);

        dbHelper = new DatabaseHelper(this);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewDate = findViewById(R.id.textViewDate);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewType = findViewById(R.id.textViewType);
        buttonDelete = findViewById(R.id.buttonDelete);

        item = (Item) getIntent().getSerializableExtra("item");
        if (item != null) {
            textViewTitle.setText(item.getTitle());
            textViewDescription.setText(item.getDescription());
            textViewPhone.setText(item.getPhone());
            textViewDate.setText(item.getDate());
            textViewLocation.setText(item.getLocation());
            textViewType.setText(item.getType());
        }

        buttonDelete.setOnClickListener(v -> {
            dbHelper.deleteItem(item.getId());
            Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
            returnToHomePage();
        });
    }

    private void returnToHomePage() {
        Intent intent = new Intent(RemoveITem.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Finish RemoveItemActivity
    }
}
