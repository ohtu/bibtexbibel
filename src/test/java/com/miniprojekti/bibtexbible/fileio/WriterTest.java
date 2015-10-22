package com.miniprojekti.bibtexbible.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

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
            writer.write("Yksi kaksi kolme\n"
                    + "Yksi kaksi kolme");
            writer.close();
            BufferedReader br;
            br = new BufferedReader(new FileReader(mockFile));
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

    @Test
    public void testFilename() {
        try {
            Writer writer = new Writer("tiedosto.bib");
            writer.close();
            assertTrue(writer.getFile().getName().equals("tiedosto.bib"));
            File file = writer.getFile();
            if (file.exists()) {
                file.delete();
            }
            
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddsBibToFilename() {
        try {
            Writer writer = new Writer("tiedosto");
            writer.close();
            assertTrue(writer.getFile().getName().equals("tiedosto.bib"));
            File file = writer.getFile();
            if (file.exists()) {
                file.delete();
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(WriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
