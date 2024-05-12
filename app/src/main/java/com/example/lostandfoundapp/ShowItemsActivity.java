package com.example.lostandfoundapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        recyclerView = findViewById(R.id.recyclerViewItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Use a linear layout manager

        dbHelper = new DatabaseHelper(this);
        loadItems();
    }

    private void loadItems() {
        List<Item> items = dbHelper.getAllItems(); // Fetch all items from your database
        // Correctly passing context and isDeleteMode flag
        adapter = new ItemAdapter(this, items, false); // false indicates not in delete mode
        recyclerView.setAdapter(adapter);
    }
}



