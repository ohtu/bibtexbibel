package com.miniprojekti.bibtexbible.fileio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BibTexReader {
    
    private Scanner scanner;
    private String filename;

    public BibTexReader(String filename) throws IOException {
        if (!filename.endsWith(".bib")) {
            filename += ".bib";
        }
        scanner = new Scanner(new File(filename), "UTF-8");
    }
    
    public String read() {
        StringBuilder contents = new StringBuilder("");
        while (scanner.hasNextLine()) {
            contents.append(scanner.nextLine()).append(System.lineSeparator());
        }
        return contents.toString();
    }
    
}