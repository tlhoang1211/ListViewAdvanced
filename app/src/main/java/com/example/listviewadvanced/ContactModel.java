package com.example.listviewadvanced;

public class ContactModel {
    private final String name;
    private final String phone;
    private final int image;

    public ContactModel(String name, String phone, int image) {
        this.name = name;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getImage() {
        return image;
    }
}

