package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.UI;

public class App {

    private final UI ui;
    private final IO io;
    private ReferenceController controller;

    public App(UI ui, IO io, ReferenceController controller) {
        this.ui = ui;
        this.io = io;
        this.controller = controller;
    }
    
    /**
     * Suorittaa ohjelmaa, kunnes käyttäjä lopettaa sen päävalikon kautta
     */
    public void runConsoleApp() {
        this.io.write(this.ui.getConsoleIntro());
        
        boolean running = true;
        while (running) {
            this.io.write(this.ui.getConsoleMenu());
            String input = this.io.readline();
            int action;
            try {
                action = Integer.parseInt(input);
                running = route(action);
            } catch (NumberFormatException e) {
                this.io.write("non integer input");
                continue;
            }
        }
        
    }
    
    // Reititää ohjelman controllerille
    private boolean route(int action) {
        switch (action) {
            case (1):
                this.io.write("Adding a reference");
                this.controller.create();
                break;
            case (2):
                this.io.write("Listing references");
                this.controller.list();
                break;
            case (3):
                this.io.write("Deleting a reference");
                this.controller.delete();
                break;
            case (0):
                this.io.write("Exiting");
                return false;
            default:
                this.io.write("Not a valid command");
                break;
        }
        return true;
    }
}
