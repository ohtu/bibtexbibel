/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miniprojekti.bibtexbible.io;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teekoivi
 */
public class StubIOTest {

    public StubIOTest() {
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
    public void testInitilization() {
        StubIO stubb = new StubIO("1");
        assertTrue(stubb.readline().equals("1"));
    }
    
    @Test
    public void testReadLine() {
        StubIO stubb = new StubIO("1", "asdfasdfasdf123!");
        assertTrue(stubb.readline().equals("1"));
        assertTrue(stubb.readline().equals("asdfasdfasdf123!"));
    }
    
    @Test
    public void testReadInteger() {
        StubIO stubb = new StubIO("1", "0");
        assertTrue(stubb.readInteger() == 1);
        assertTrue(stubb.readInteger() == 0);
    }
    
    @Test
    public void testWrite() {
        StubIO stubb = new StubIO();
        ArrayList<String> prints = new ArrayList<>(Arrays.asList("1", "asdfasdfasdfasdf   000000"));
        stubb.write("1");
        stubb.write("asdfasdfasdfasdf   000000");
        assertEquals(prints, stubb.getPrints());
    }
}
