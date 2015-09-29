package com.miniprojekti.bibtexbible.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    
    Book book;
    
    public BookTest() {
    }
    
    @Before
    public void setUp() {
        book = new Book("Dostojevski, Fyodor", "Crime and Punishment", 1866, "The Russian Messenger", "");
    }

}
