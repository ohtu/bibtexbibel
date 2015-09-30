package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;

public abstract class Reference {
    
    private String author;
    private String title;
    private int year;
    
    public Reference() {
        
    }
    
    public Reference(String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public HashMap<String, String> getProperties() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("author", "Author of the Title");
        properties.put("title", "The tile of the publication");
        properties.put("year", "Publication year");
        return properties;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
