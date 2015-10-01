package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

public class Bibtexbible {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        UI ui = new ConsoleUI(io);
        new App(ui, new ReferenceController(ui)).runConsoleApp();
    }
}
