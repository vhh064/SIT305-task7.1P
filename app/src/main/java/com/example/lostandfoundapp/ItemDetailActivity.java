package com.example.lostandfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class ItemDetailActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextDescription, editTextDate, editTextLocation;
    private RadioGroup radioGroupType;
    private MaterialButton saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        dbHelper = new DatabaseHelper(this);
        radioGroupType = findViewById(R.id.radioGroupType);
        editTextName = findViewById(R.id.editTextItemName);
        editTextPhone = findViewById(R.id.editTextItemPhone);
        editTextDescription = findViewById(R.id.editTextItemDescription);
        editTextDate = findViewById(R.id.editTextItemDate);
        editTextLocation = findViewById(R.id.editTextItemLocation);
        saveButton = findViewById(R.id.SaveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
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

        Item newItem = new Item(name, phone, description, date, location, type);
        if (dbHelper.addItem(newItem)) {
            Toast.makeText(this, "Item saved successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Finish this activity and go back to the previous one
        } else {
            Toast.makeText(this, "Failed to save the item. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
