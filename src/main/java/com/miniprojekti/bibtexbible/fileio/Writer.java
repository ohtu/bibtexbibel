package com.miniprojekti.bibtexbible.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private FileWriter fileWriter;
    private String filename;

    public Writer(String filename) throws IOException {
        this.filename = !filename.endsWith(".bib") ? filename + ".bib" : filename;
        fileWriter = new FileWriter(new File(filename));
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

    public String getFilename() {
        return filename;
    }
}
