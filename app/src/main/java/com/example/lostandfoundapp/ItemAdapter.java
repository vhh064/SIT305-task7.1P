package com.example.lostandfoundapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private boolean isDeleteMode;
    private Context context;  // Adding context to handle Intents

    // Constructor for the adapter
    public ItemAdapter(Context context, List<Item> items, boolean isDeleteMode) {
        this.context = context;
        this.items = items;
        this.isDeleteMode = isDeleteMode;
    }

    // ViewHolder class that holds the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView typeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewItemTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewItemDescription);
            typeTextView = itemView.findViewById(R.id.textViewItemType);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());
        if (holder.typeTextView != null) {
            holder.typeTextView.setText(item.getType());
        }

        // Click listener for item view
        holder.itemView.setOnClickListener(v -> {
            if (!isDeleteMode) {
                Intent intent = new Intent(context, RemoveITem.class);
                intent.putExtra("item", item);  // Ensure Item implements Serializable
                context.startActivity(intent);
            } else {
                // Handle item delete in delete mode
                removeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Method to remove an item from the dataset
    private void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size()); // Ensure correct position update
    }
}

