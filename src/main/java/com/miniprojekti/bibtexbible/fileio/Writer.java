
package com.miniprojekti.bibtexbible.fileio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer {
    
    private FileWriter fileWriter;
    
    public Writer(String filename) {
        if (!filename.endsWith(".bib")) {
            filename += ".bib";
        }
        try {
            fileWriter = new FileWriter(new File(filename));
        }
        catch (IOException ex) {
            System.out.println("Something went wrong with the file creation");
        }
    }
    
    /*
    * Testing purposes mainly
    */
    public Writer(File file) {
        try {
            fileWriter = new FileWriter(file);
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when opening the file");
        }
    }
    
    public void writeLine(String line) {
        try {
            fileWriter.write(line);
            fileWriter.write(System.lineSeparator());
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when writing to a file");
        }
        
    }
    
    public void write(String string) {
        try {
            fileWriter.write(string);
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when writing to a file");
        }
    }
    
    public void endLine() {
        try {
            fileWriter.write(System.lineSeparator());
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when writing to a file");
        }
    }
    
    public void close() {
        try {
            fileWriter.close();
        }
        catch (IOException ex) {
            System.out.println("Something went wrong when saving the file");
        }
    }
    
}