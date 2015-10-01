package com.miniprojekti.bibtexbible.ui;

import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.io.IO;
import java.util.List;

public class ConsoleUI implements UI {
    
    private IO io;
    
    public ConsoleUI(IO io) {
        this.io = io;
    }
    

    private void printStartMenu() {
        io.write("* * * * * * * * * * * * * * * * * * * * * * * * *\n"
                + " BIBTEXBIBLE - BibTex reference manager\n"
                + "* * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    /**
     * Returns create objects menu with options as text
     * @return String
     */
    @Override
    public int selectMenuOption() {
        io.write("Choose a command:\n"
                + "1) Add a new reference\n"
                + "2) List all references\n"
                + "3) Delete reference\n"
                + "0) Exit");
        return io.readInteger();
    }
    
    /**
     * Returns menu with options for creating a specific refrence
     * @return String 
     */
    @Override
    public int selectReferenceType() {
        io.write("Create reference with entry type:\n"
                + "1) Book\n"
                + "2) Article\n"
                + "3) In Proceedigns\n"
                + "0) Return");
        while (true) {
            int action = io.readInteger();
            if (action >= 0 && action <= 3) {
                return action;
            } else {
                io.write("Action not found. Try again.");
            }
        }
        
    }

    @Override
    public void printReferences(List<Reference> references) {
        int i = 1;
        for (Reference reference : references) {
            io.write(i + ")" + reference.toString());
            i++;
        }
    }

    @Override
    public int selectReferenceToDelete(List<Reference> references) {
        printReferences(references);
        io.write("Select reference to delete:");
        while (true) {
            int id = io.readInteger() - 1;
            if (id >=0 && id < references.size()) {
                return id;
            } else {
                io.write("Refernce with id " + id + " not found. Try again");
            }
        }
    }

    @Override
    public void init() {
        printStartMenu();
    }

    @Override
    public void exit() {
        
    }
    
    
 
}
