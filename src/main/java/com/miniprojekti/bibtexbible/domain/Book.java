package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;

public class Book extends Reference {
    
    public Book() {
        super();
        setPropertyDescriptions();
    }
    
    public Book(String author, String title, String year, String publisher) {
        super();
        setPropertyDescriptions();
        setProperty("author", author);
        setProperty("title", title);
        setProperty("year", year);
        setProperty("publisher", publisher);
    }

    @Override
    public boolean setProperty(String label, String value) {
        if (!getPropertyDescriptions().containsKey(label)) {
            // If label is not a property of Book
            return false;
        }
        super.setProperty(label, value);
        return true;
    }
    
    
    
    private void setPropertyDescriptions() {
        HashMap<String, String> propertyDescriptions = super.getPropertyDescriptions();
        propertyDescriptions.put("author", "Author of the Title");
        propertyDescriptions.put("title", "The tile of the publication");
        propertyDescriptions.put("year", "Publication year");
        propertyDescriptions.put("publisher", "Publisher of the Book");
        propertyDescriptions.put("address", "Address of the Publisher");
        propertyDescriptions.put("volume", "Volume of the Book");
        propertyDescriptions.put("number", "Number of the Book");
        propertyDescriptions.put("series", "Which series is this book a part of");
        propertyDescriptions.put("edition", "Edition of the Book");
        propertyDescriptions.put("month", "Month of the publication");
    }
}
