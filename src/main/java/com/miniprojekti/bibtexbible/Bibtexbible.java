package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.logic.ReferenceController;
import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

public class Bibtexbible {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        ReferenceController rc = new ReferenceController();
        UI ui = new ConsoleUI(io, rc);
        ui.run();
    }
}
