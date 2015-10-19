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
}
