/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author asjuvone
 */
public class ReferenceListTest {

    private static ReferenceList refList;
    private static MockBook kirja1;
    private static MockBook kirja2;

    private static class MockBook extends Reference {

        public MockBook(String author, String title, String year) {
            super();
            HashMap<String, String> pD = getPropertyDescriptions();
            pD.put("author", "");
            pD.put("title", "");
            pD.put("year", "");
            setProperty("author", author);
            setProperty("title", title);
            setProperty("year", year);
        }

    }

    public ReferenceListTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        refList = new ReferenceList();
        kirja1 = new MockBook("Tekija", "Titteli", "1984");
        kirja2 = new MockBook("Tekija2", "Titteli2", "1985");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddReferenceOneBook() {
        refList.add(kirja1);
        assertTrue(refList.list().contains(kirja1));
    }

    @Test
    public void testAddReferencesSecondAddedBookFound() {
        refList.add(kirja1);
        refList.add(kirja2);
        assertTrue(refList.list().contains(kirja2));
    }

    @Test
    public void testAddReferenceDoesntReplaceOldItems() {
        refList.add(kirja1);
        refList.add(kirja2);
        assertTrue(refList.list().contains(kirja1));
    }

    @Test
    public void testAddSameReferenceMultipleTimes() {
        Book a1 = new Book("Mauri Rynnäs", "Teos1", "2015", "UGK");
        Book a2 = new Book("Mauri Rynnäs", "Teos1", "2015", "UGK");
        refList.add(a1);
        refList.add(a2);
        refList.add(a2);
        assertTrue(refList.list().size() == 1);
    }

    @Test
    public void testAddReferencesWithSameID() {
        Book a1 = new Book("Mauri Rynnäs", "Teos1", "2015", "UGK");
        Book a2 = new Book("Mauri Rynnäs", "Teos2", "2015", "UGK");
        Book a3 = new Book("Mauri Rynnäs", "Teos3", "2015", "UGK");
        Book a4 = new Book("Mauri Rynnäs", "Teos4", "2015", "UGK");
        refList.add(a1);
        refList.add(a2);
        refList.add(a3);
        refList.add(a4);
        assertEquals(1, refList.list().size());
    }

    @Test
    public void testDeleteReference() {
        refList.add(kirja1);
        refList.delete(kirja1.getID());
        assertFalse(refList.list().contains(kirja1));
    }

    @Test
    public void testDeleteFirstOf2() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja1.getID());
        assertFalse(refList.list().contains(kirja1));
    }

    @Test
    public void testDeleteLastOf2() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja2.getID());
        assertFalse(refList.list().contains(kirja2));
    }

    @Test
    public void testDeleteWhenNotFoundDoesntAccidentallyDeleteOtherItems() {
        refList.add(kirja1);
        refList.delete(kirja2.getID());
        assertTrue(refList.list().contains(kirja1));
    }

    @Test
    public void testDeleteMultipleReferences() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja1.getID());
        refList.delete(kirja2.getID());
        assertTrue(refList.list().isEmpty());
    }

}
