/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.domain;

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
    private static Book kirja1;
    private static Book kirja2;
    
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
        kirja1 = new Book("Tekija", "Titteli", 1984, "Julkaisija", "Osoite");
        kirja2 = new Book("Tekija2", "Titteli2", 1985, "Julkaisija2", "Osoite2");
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
    public void testDeleteReference() {
        refList.add(kirja1);
        refList.delete(kirja1);
        assertFalse(refList.list().contains(kirja1));
    }
    
    @Test
    public void testDeleteFirstOf2() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja1);
        assertFalse(refList.list().contains(kirja1));
    }
    
    @Test
    public void testDeleteLastOf2() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja2);
        assertFalse(refList.list().contains(kirja2));
    }
    
    @Test
    public void testDeleteWhenNotFoundDoesntAccidentallyDeleteOtherItems() {
        refList.add(kirja1);
        refList.delete(kirja2);
        assertTrue(refList.list().contains(kirja1));
    }
    
    @Test
    public void testDeleteMultipleReferences() {
        refList.add(kirja1);
        refList.add(kirja2);
        refList.delete(kirja1);
        refList.delete(kirja2);
        assertTrue(refList.list().isEmpty());
    }

}
