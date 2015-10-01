package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;

public class Book extends Reference {
    
    public Book() {
        super();
        setPropertyDescriptions();
    }
    
    public Book(String author, String title, int year, String publisher) {
        super(author, title, year);
        setPropertyDescriptions();
        setProperty("publisher", publisher);
    }

    /**
     * Checks if label is an allowed property of Book
     * If yes, sets value to label via supertype
     */
    @Override
    public boolean setProperty(String label, String value) {
        if (!getPropertyDescriptions().containsKey(label)) return false;
        super.setProperty(label, value);
        return true;
    }
    
    /**
     * Returns all the properties with name and description of the Book model.
     * The returned HashMap can be used in generating output labels to guide 
     * user before entering input.
     * 
     * Also used to check which are allowed labels for this type
     * 
     * @return HashMap<String, String>
     */
    @Override
    public HashMap<String, String> getPropertyDescriptions() {
        return super.getPropertyDescriptions();
    }
    
    private void setPropertyDescriptions() {
        HashMap<String, String> propertyDescriptions = super.getPropertyDescriptions();
        propertyDescriptions.put("publisher", "Publisher of the Book");
        propertyDescriptions.put("address", "Address of the Publisher");
        propertyDescriptions.put("volume", "Volume of the Book");
        propertyDescriptions.put("series", "Which series is this book a part of");
        propertyDescriptions.put("edition", "Edition of the Book");
        propertyDescriptions.put("month", "Month of the publication");
        propertyDescriptions.put("note", "NOTE ??????????????????");
        propertyDescriptions.put("key", "KEY ????????????????");
    }
}
