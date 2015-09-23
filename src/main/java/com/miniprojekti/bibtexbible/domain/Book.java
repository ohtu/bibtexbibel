package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;

public class Book extends Reference {

    private String publisher;
    private String address;
    
    public Book() {
        super();
    }
    
    public Book(String author, String title, int year, String publisher, String address) {
        super(author, title, year);
        this.publisher = publisher;
        this.address = address;
    }
    
    /**
     * Returns all the properites with name and description of the Book model.
     * The returned HashMap can be used in generating output labels to guide 
     * user before entering input.
     * 
     * @return HashMap<String, String>
     */
    @Override
    public HashMap<String, String> getProperties() {
        HashMap<String, String> properties = super.getProperties();
        properties.put("publisher", "Publisher of the Book");
        properties.put("address", "Address of the Publisher");
        return properties;
    }
    
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Book{" + "author=" + getAuthor() + ", title=" + getTitle() + ", year=" + getYear() + ", publisher=" + publisher + ", address=" + address + '}';
    }
       
}
