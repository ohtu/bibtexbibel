package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;
import com.miniprojekti.bibtexbible.ui.UI;

public class Bibtexbible {

    public static void main(String[] args) {
        String asdf = "boo; basdf; bee; blasdf; boo; basdf;";
        String pattern = "boo";
        int count = asdf.length() - asdf.replace(pattern, "").length();
        System.out.println("count " + count/pattern.length());
        
        IO io = new ConsoleIO();
        UI ui = new ConsoleUI(io);
        ReferenceController rc = new ReferenceController(ui);
        App app = new App(ui, rc);
        app.runConsoleApp();
    }
}
