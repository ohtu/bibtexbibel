package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

public class Bibtexbible {

    public static void main(String[] args) {
        UI ui = new ConsoleUI();
        IO io = new ConsoleIO();
        
        new App(ui, io, new ReferenceController(ui, io)).runConsoleApp();
    }
}
