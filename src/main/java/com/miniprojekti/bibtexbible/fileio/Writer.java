package com.miniprojekti.bibtexbible.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private final FileWriter fileWriter;
    private String filename;
    private File file;

    public Writer(String filename) throws IOException {
        this.filename = !filename.endsWith(".bib") ? filename + ".bib" : filename;
        file = new File(this.filename);
        fileWriter = new FileWriter(file);
    }

    /*
     * Testing purposes mainly
     */
    public Writer(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public void write(String string) throws IOException {
        fileWriter.write(string);
    }

    public void close() throws IOException {
        fileWriter.close();
    }
    
    public File getFile() {
        return file;
    }
}
