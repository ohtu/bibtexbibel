package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.ui.UI;

public class App {

    private final UI ui;
    private final ReferenceController controller;

    public App(UI ui, ReferenceController controller) {
        this.ui = ui;
        this.controller = controller;
    }

    /**
     * Suorittaa ohjelmaa, kunnes käyttäjä lopettaa sen päävalikon kautta
     */
    public void runConsoleApp() {
        ui.init();
        boolean running = true;
        while (running) {
            int action = ui.selectMenuOption();
            running = route(action);
        }
        ui.exit();
    }

    // Reititää ohjelman controllerille
    private boolean route(int action) {
        switch (action) {
            case (1):
                this.controller.create();
                break;
            case (2):
                this.controller.list();
                break;
            case (3):
                this.controller.delete();
                break;
            case (4):
                this.controller.export();
                break;
            case (5):
                this.controller.importBibtex();
                break;
            case (0):
                return false;
            default:
                break;
        }
        return true;
    }
}
