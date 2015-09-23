package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.ConsoleIO;
import com.miniprojekti.bibtexbible.ui.ConsoleUI;

public class Bibtexbible {

    public static void main(String[] args) {

        App app = new App(new ConsoleUI(), new ConsoleIO());
        app.run();

    }

}
