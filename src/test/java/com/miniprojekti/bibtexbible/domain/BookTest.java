package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    Book emptyBook;
    Book book;
    
    public BookTest() {
    }
    
    @Before
    public void setUp() {
        emptyBook = new Book();
        book = new Book(
            "Dostojevski, Fyodor", 
            "Crime and Punishment", 
            1866, 
            "The Russian Messenger", 
            "Russia"
        );
    }
    
    @Test
    public void getAuthorRetursCorrectAuthor() {
        assertEquals("Dostojevski, Fyodor", book.getAuthor());
    }
    
    @Test
    public void getTitleReturnsCorrectTitle() {
        assertEquals("Crime and Punishment", book.getTitle());
    }
    
    @Test
    public void getYearReturnsCorrectYear() {
        assertEquals(1866, book.getYear());
    }
    
    @Test
    public void getPublisherReturnsCorrectPublisher() {
        assertEquals("The Russian Messenger", book.getPublisher());
    }
    
    @Test
    public void getAddressReturnsCorrectAddress() {
        assertEquals("Russia", book.getAddress());
    }
    
    @Test
    public void setAuthorSetsAuthor() {
        emptyBook.setAuthor("Dostojevski, Fyodor");
    }
    
    @Test
    public void setTitleSetsTitle() {
        emptyBook.setTitle("Crime and Punishment");
        assertEquals("Crime and Punishment", emptyBook.getTitle());
    }
    
    @Test
    public void getYearSetsYear() {
        emptyBook.setYear(1866);
        assertEquals(1866, emptyBook.getYear());
    }
    
    @Test
    public void setPublisherSetsPublisher() {
        emptyBook.setPublisher("The Russian Messenger");
        assertEquals("The Russian Messenger", emptyBook.getPublisher());
    }
    
    @Test
    public void setAddressSetsAddress() {
        emptyBook.setAddress("Russia");
        assertEquals("Russia", emptyBook.getAddress());
    }
    
    @Test
    public void getPropertiesReturnsHashMapWithCorrecNumberOfProperties() {
        HashMap<String, String> properties = book.getProperties();
        assertEquals(true, properties instanceof HashMap);
        assertEquals(5, properties.size());
    }
    
}
