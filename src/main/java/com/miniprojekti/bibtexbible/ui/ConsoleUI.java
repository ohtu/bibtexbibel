package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.logic.ReferenceController;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.io.IO;
import java.util.List;

public class ConsoleUI implements UI {

    private final IO io;
    private final ReferenceController rc;

    public ConsoleUI(IO io, ReferenceController rc) {
        this.io = io;
        this.rc = rc;
    }

    @Override
    public void run() {
        init();
        boolean running = true;
        while (running) {
            int action = selectMenuOption();
            running = route(action);
        }
        exit();
    }

    private void create() {
        Reference ref = rc.create(selectReferenceType());
        setProperties(ref);
        System.out.println(ref.getID());
    }

    private void delete() {
        rc.delete(selectReferenceToDelete(rc.list()));
    }

    private void list() {
        printReferences(rc.list());
    }

    private void export() {
        String filename = askFilename();
        switch (rc.export(filename)) {
            case 0:
                printLine("All references exported to " + filename);
                break;

            case 2:
                printLine("No references to export");
                break;

            default:
                printLine("Exporting to a file was unsuccessful");
        }
    }

    private void importBibtex() {
        String filename = askFilename();
        switch (rc.importBibtex(filename)) {
            case 0:
                printLine("Much import very success wow!");
                break;

            default:
                printLine("Importing from file was unsuccessful. Clearing database...");
        }
    }

    private boolean route(int action) {
        switch (action) {
            case (1):
                create();
                break;
            case (2):
                list();
                break;
            case (3):
                delete();
                break;
            case (4):
                export();
                break;
            case (5):
                importBibtex();
                break;
            case (0):
                return false;
            default:
                break;
        }
        return true;
    }

    @Override
    public void init() {
        printIntroText();
    }

    @Override
    public void exit() {
        printExitText();
    }

    public int selectMenuOption() {
        printStartMenu();
        return askInput(0, 5);
    }

    public int selectReferenceType() {
        printReferenceTypes();
        return askInput(0, 4);
    }

    public String askFilename() {
        printLine("Give a file name to the exported document:");
        while (true) {
            String name = io.readline();
            if (!name.isEmpty()) {
                return name;
            } else {
                io.write("Invalid filename. Try Again.");
            }
        }
    }

    public void printFilename(String filename) {
//        filename.
        io.write("All references exported to file " + filename);
    }

    public void printReferences(List<Reference> references) {
        if (references.isEmpty()) {
            io.write("No references.");
        }
        int i = 1;
        for (Reference reference : references) {
            io.write(i + ") " + reference.toString());
            i++;
        }
    }

    public void printLine(String s) {
        io.write(s);
    }

    public int selectReferenceToDelete(List<Reference> references) {
        if (references.isEmpty()) {
            io.write("No references in the list.");
            return -1;
        }
        io.write("Select reference to delete:");
        printReferences(references);
        return askInput(1, references.size()) - 1;
    }

    public void setProperties(Reference reference) {
        for (String label : reference.getPropertyDescriptions().keySet()) {
            io.write(reference.getPropertyDescriptions().get(label));
            reference.setProperty(label, askInput());
        }
    }

    // Tulostaa esittelytekstin
    private void printIntroText() {
        io.write(
                "* * * * * * * * * * * * * * * * * * * * * * * * *\n"
                + " BIBTEXBIBLE - BibTex reference manager\n"
                + "* * * * * * * * * * * * * * * * * * * * * * * * *"
        );
    }

    // Tulostaa lopetustekstin
    private void printExitText() {
        io.write("Exiting...");
    }

    // Tulostaa alkuvalikon vaihtoehdot
    private void printStartMenu() {
        io.write(
                "Choose a command:\n"
                + "1) Add a new reference\n"
                + "2) List all references\n"
                + "3) Delete reference\n"
                + "4) Export bibtex\n"
                + "5) Import bibtex\n"
                + "0) Exit"
        );
    }

    // Tulostaa valittavat tyypit
    private void printReferenceTypes() {
        io.write(
                "Create reference with entry type:\n"
                + "1) Book\n"
                + "2) Article\n"
                + "3) Proceedings\n"
                + "4) InProceedings\n"
                + "0) Return"
        );
    }

    // Pyytää ja palauttaa syötteen, jonka on oltava tietyllä arvovälillä
    private int askInput(int min, int max) {
        while (true) {
            int option = io.readInteger();
            if (option >= min && option <= max) {
                return option;
            } else {
                io.write("Invalid option. Try Again.");
            }
        }
    }

    private String askInput() {
        return io.readline();
    }

}
