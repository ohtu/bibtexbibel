package com.miniprojekti.bibtexbible.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Mockito.mock;

public class WriterTest {

    public WriterTest() {
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testWriting() {
        try {
            File mockFile = folder.newFile("mock.bib");
            Writer writer = new Writer(mockFile);
            writer.writeLine("Yksi kaksi kolme");
            writer.write("Yksi ");
            writer.write("kaksi ");
            writer.write("kolme");
            writer.endLine();
            writer.close();
            BufferedReader br;
            br = new BufferedReader(new FileReader(mockFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            assertEquals("Yksi kaksi kolme", line);
            line = br.readLine();
            assertEquals("Yksi kaksi kolme", line);
            line = br.readLine();
            assertNull(line);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
