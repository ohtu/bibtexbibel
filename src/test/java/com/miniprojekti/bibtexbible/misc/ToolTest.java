/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.misc;

import com.miniprojekti.misc.Tool;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author baobab
 */
public class ToolTest {
    
    public ToolTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testScandisToBibtexDoesSomething() {
        String s = "Ääkkönen";
        assertFalse(s.equals(Tool.scandisToBibtex(s)));
    }
    
    @Test
    public void testScandisToBibtexAndBackReturnsOriginal() {
        String s = "Ääkkönen";
        String sToBib = Tool.scandisToBibtex(s);
        String backToNormal = Tool.bibtexToScandis(s);
        assertTrue(s.equals(backToNormal));
    }
    
    @Test
    public void safeSubstringWithEmptyInput() {
        assertTrue(Tool.safeSubstring("", 3).isEmpty());
    }
    
    @Test
    public void safeSubstringBeginningOfString() {
        assertEquals("Moi", Tool.safeSubstring("Moikka", 0, 3));
    }
    
    @Test
    public void safeSubstringMiddleOfString() {
        assertEquals("oi", Tool.safeSubstring("Moikka", 1, 3));
    }
    
    @Test
    public void safeSubstringEndOfString() {
        assertEquals("a", Tool.safeSubstring("Moikka", 5));
    }
}
