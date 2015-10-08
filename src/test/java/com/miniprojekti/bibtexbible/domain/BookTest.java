package com.miniprojekti.bibtexbible.domain;

import static com.miniprojekti.misc.Tool.replaceNullsWithEmpty;
import static com.miniprojekti.misc.Tool.replaceScandisForBibTex;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    Book emptyBook;
    Book typicalBook;
    Book fullyDescribedBook;
    Set<String> labels;

    public BookTest() {
    }

    @Before
    public void setUp() {
        emptyBook = new Book();
        labels = emptyBook.getPropertyDescriptions().keySet();
        typicalBook = new Book();
        typicalBook.setProperty("author", "Dostojevski, Fyodor");
        typicalBook.setProperty("title", "Crime and Punishment");
        typicalBook.setProperty("year", "1866");
        typicalBook.setProperty("publisher", "The Russian Messenger");
        replaceNullsWithEmpty(emptyBook);
        replaceNullsWithEmpty(typicalBook);
        fullyDescribedBook = new Book();
        for (String label : fullyDescribedBook.getPropertyDescriptions().keySet()) {
            fullyDescribedBook.setProperty(label, label + "x");
            // testissä esim. address arvo pitäisi olla "addressx"
        }
        labels = emptyBook.getPropertyDescriptions().keySet();
    }
    
    /*
    * Allowed labels for book should be the same regardless
    * of constructor used, set properties, etc.
    */
    @Test
    public void testBookLabelsAreTheSame() {
        Set<String> labels2 = typicalBook.getPropertyDescriptions().keySet();
        Set<String> labels3 = fullyDescribedBook.getPropertyDescriptions().keySet();
        assertTrue(labels.size() == labels2.size());
        assertTrue(labels2.size() == labels3.size());
        for (String label : labels) {
            assertTrue(labels2.contains(label));
            assertTrue(labels3.contains(label));
        }
    }
    
    @Test
    public void testConstructorSetsPropertyValuesCorrectlyTypicalBook() {
        assertTrue(typicalBook.getProperty("author").equals("Dostojevski, Fyodor"));
        assertTrue(typicalBook.getProperty("title").equals("Crime and Punishment"));
        assertTrue(typicalBook.getProperty("year").equals("1866"));
        assertTrue(typicalBook.getProperty("publisher").equals("The Russian Messenger"));
        // varmistetaan vielä ettei muita valueita löydy
        int count = 0; // kuinka monta valueta typicalproceedingsilta löytyy
        for (String label : labels) {
            if (!typicalBook.getProperty(label).isEmpty()) {
                count++;
            }
        }
        assertTrue(count == 4);
    }
    
    @Test
    public void testConstructorSetsPropertyValuesCorrectlyEmptyBook() {
        for (String label : labels) {
            assertTrue(emptyBook.getProperty(label).isEmpty());
        }
    }
    
    @Test
    public void testSetProperty() {
        for (String label : labels) {
            String value = fullyDescribedBook.getProperty(label);
            assertTrue(value.equals(label + "x"));
        }
    }
    
    @Test
    public void testSetPropertyWhenOverwriting() {
        fullyDescribedBook.setProperty("author", "Uolevi");
        assertTrue(fullyDescribedBook.getProperty("author").equals("Uolevi"));
    }
    
    @Test
    public void testSetPropertyWrongLabel() {
        typicalBook.setProperty("dummy1337", "testi");
        assertNull(fullyDescribedBook.getProperty("dummy1337"));
    }

    @Test
    public void testGetPropertiesReturnsHashMapWithCorrectNumberOfProperties() {
        Map<String, String> properties = emptyBook.getPropertyDescriptions();
        assertEquals(true, properties instanceof HashMap);
        assertEquals(11, properties.size());
    }
    
    @Test
    public void testToString() {
        assertTrue(typicalBook.toString().contains("Dostojevski"));
        assertTrue(typicalBook.toString().contains("1866"));
        assertTrue(typicalBook.toString().contains("Russian"));
        assertTrue(typicalBook.toString().contains("Crime"));
    }
    
    @Test
    public void testToBibTex() {
        testBookToBibTex(typicalBook, 4+2);
        testBookToBibTex(fullyDescribedBook, labels.size()+2);
        // test empty book to bibtex?
    }
    
    private void testBookToBibTex(Book book, int valueCount) {
        String b = book.toBibTex();
        String[] split = b.split("\r\n");
        assertTrue(split.length == valueCount);
        assertTrue(split[0].startsWith("@Book{")); // any ID is ok
        HashSet<String> labelsFound = new HashSet<>();
        for (int i=1; i<split.length-1; i++) {
            String[] rivi = split[i].split("\\=", 2);
            assertTrue(rivi.length == 2);
            String label = rivi[0].trim();
            String expected = book.getProperty(label);
            expected = replaceScandisForBibTex(expected);
            String found = rivi[1].trim();
            found = found.substring(1);
            found = found.substring(0, found.length()-2); // lopusta pois " ja ,
            labelsFound.add(label);
            assertTrue(found.equals(expected));
        }
        assertTrue(labelsFound.size() == valueCount-2);
        assertTrue(b.endsWith("}"));
    }    
}
