package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

public class Bibtexbible {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        UI ui = new ConsoleUI(io);
        ReferenceController rc = new ReferenceController(ui);
        App app = new App(ui, rc);
        app.runConsoleApp();
    }
}
