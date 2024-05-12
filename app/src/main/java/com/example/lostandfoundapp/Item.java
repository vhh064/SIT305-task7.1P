package com.example.lostandfoundapp;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String title;
    private String phone;
    private String description;
    private String date;
    private String location;
    private String type; // "lost" or "found"
    private boolean isSelected; // Assuming you need this for some UI logic

    // Constructor with all properties
    public Item(int id, String title, String phone, String description, String date, String location, String type) {
        this.id = id;
        this.title = title;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.type = type;
    }

    // Constructor without id, for creating new items
    public Item(String title, String phone, String description, String date, String location, String type) {
        this(-1, title, phone, description, date, location, type);
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public boolean isSelected() { return isSelected; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setType(String type) { this.type = type; }
    public void setSelected(boolean selected) { isSelected = selected; }

    // ToString method for debugging and displaying the item details in a readable format
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}

