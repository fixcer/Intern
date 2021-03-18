package com.example.demo;

public class Magazine extends Document {

    public String publicDate;

    public Magazine(String title, String ISBN, Author[] authors, String publicDate) {
        super(title, ISBN, authors);
        this.publicDate = publicDate;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }
}
