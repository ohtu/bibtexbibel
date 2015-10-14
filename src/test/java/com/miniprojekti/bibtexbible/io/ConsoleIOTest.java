package com.miniprojekti.bibtexbible.io;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.*;

public class ConsoleIOTest {
    
    private ConsoleIO io;
    
    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
    @Before
    public void setUp() {
        io = new ConsoleIO();
    }
    
    @Test
    public void writePrintsTheGivenString() {
        io.write("lol");
        assertEquals("lol\n", systemOutRule.getLogWithNormalizedLineSeparator());
    }
      
    @Test
    public void readLineReturnsTheGivenInput() {
        systemInMock.provideText("lol");
        assertEquals("lol", io.readline());
    }
     
    @Test
    public void readIntegerReturnsIntegerWhenValidInputIsGiven() {
        systemInMock.provideText("1");
        assertEquals(1, io.readInteger());
    }
    
    @Test
    public void readIntegerAsksForNewInputUntilValidIntegerHasBeenGiven() {
        systemInMock.provideLines("a","a","a", "1");
        assertEquals(1, io.readInteger());
    }

}

