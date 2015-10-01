package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ConsoleUITest {
    
    IO io;
    ConsoleUI consoleUI;
    
    @Before
    public void setUp() {
        IO io = mock(ConsoleIO.class);
        consoleUI = new ConsoleUI(io);
    }
    
    
}
