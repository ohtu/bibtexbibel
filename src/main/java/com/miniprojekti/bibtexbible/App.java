package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.UI;

public class App {

    private final UI ui;
    private final IO io;
    private ReferenceController rc;

    public App(UI ui, IO io) {
        this.ui = ui;
        this.io = io;
        this.rc = new ReferenceController();
    }

    // a silly method for injecting refcontroller. 
    // koska voi muna ei voi injektoida miljoonaa luokkaa konstruktorissa
    public void setReferenceController(ReferenceController c) {
        this.rc=c;
    }
    
    public void runGUIApp() {

    }

    /**
     * Hyvin jännä metodi
     */
    public void runConsoleApp() {
        this.io.write(this.ui.getConsoleIntro());
        boolean running = true;
        while (running) {
            this.io.write(this.ui.getConsoleMenu());
            String input = this.io.readline();
            int parsed;
            try {
                parsed = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                this.io.write("non integer input");
                continue;
            }
            switch (parsed) {
                case (1):
//                    1) Add a new reference\n" +
//                    Reference(String author, String title, int year)
                    this.io.write("adding a reference");
                    this.rc.createReference("hemmo", "tuotoksen nimi", 2015);
                    break;
                case (2):
//                    "2) List all references\n" +
                    this.io.write("listing references");
                    this.rc.listReferences();
                    break;
                case (3):
//                    "3) Update existing reference\n"+
                    this.io.write("updating a reference");
                    this.rc.updateReference();
                    break;
                case (4):
//                    "4) Delete reference\n" +
                    this.io.write("deleting a reference");
                    this.rc.deleteReference();
                    break;
                case (0):
//                    "0) Exit";
                    this.io.write("exiting");
                    running = false;
                    break;
                default:
                    this.io.write("not a valid command");
                    break;
            }
        }
    }
}
