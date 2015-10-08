package com.miniprojekti.bibtexbible.domain;

import java.util.Map;

public class Book extends Reference {

    public Book() {
        super();
        setPropertyDescriptions();
    }
    // pakolliset String author, String title, String year, String publisher

    private void setPropertyDescriptions() {
        Map<String, String> propertyDescriptions = super.getPropertyDescriptions();
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
