package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.io.IO;
import java.util.List;

public class ConsoleUI implements UI {

    private final IO io;

    public ConsoleUI(IO io) {
        this.io = io;
    }

    @Override
    public void init() {
        printIntroText();
    }

    @Override
    public void exit() {
        printExitText();
    }

    @Override
    public int selectMenuOption() {
        printStartMenu();
        return askInput(0, 3);
    }

    @Override
    public int selectReferenceType() {
        printReferenceTypes();
        return askInput(0, 3);
    }

    @Override
    public void printReferences(List<Reference> references) {
        int i = 1;
        for (Reference reference : references) {
            io.write(i + ") " + reference.toString());
            i++;
        }
    }

    @Override
    public void printLine(String s) {
        io.write(s);
    }

    @Override
    public int selectReferenceToDelete(List<Reference> references) {
        if (references.isEmpty()) {
            io.write("No references in the list.");
            return -1;
        }
        io.write("Select reference to delete:");
        printReferences(references);
        return askInput(1, references.size()) - 1;
    }

    @Override
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
                + "0) Exit"
        );
    }

    // Tulostaa valittavat tyypit
    private void printReferenceTypes() {
        io.write(
                "Create reference with entry type:\n"
                + "1) Book\n"
                + "2) Article\n"
                + "3) In Proceedigns\n"
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
