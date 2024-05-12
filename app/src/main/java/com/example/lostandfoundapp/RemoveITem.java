package com.example.lostandfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveITem extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription;
    private Button buttonDelete;
    private Item item;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_item);

        dbHelper = new DatabaseHelper(this);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Get the item from the intent
        item = (Item) getIntent().getSerializableExtra("item");
        if (item != null) {
            textViewTitle.setText(item.getTitle());
            textViewDescription.setText(item.getDescription());
        }

        buttonDelete.setOnClickListener(v -> {
            if (item != null) {
                dbHelper.deleteItem(item.getId());
                Toast.makeText(RemoveITem.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                navigateBackToHome();
            }
        });
    }

    private void navigateBackToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close this activity
    }
}
