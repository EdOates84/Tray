package com.example.android.tray;

public class User {
    String Name, Mail;

    public User() {

    }

    public User(String name, String mail) {
        Name = name;
        Mail = mail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }
}