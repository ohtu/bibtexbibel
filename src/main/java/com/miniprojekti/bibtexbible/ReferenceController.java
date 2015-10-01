package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.io.IO;
import com.miniprojekti.bibtexbible.ui.UI;
import java.util.List;

public class ReferenceController {

    private UI ui;
    private IO io;
    private ReferenceList references;
    

    public ReferenceController(UI ui, IO io) {
        this.ui = ui;
        this.io = io;
        references = new ReferenceList();
    }

    public boolean create() {
        boolean running = true;
        while (running) {
            this.io.write(this.ui.getConsoleCreateMenu());
            String input = this.io.readline();
            int action;
            try {
                action = Integer.parseInt(input);
                running = routeCreate(action);
            }
            catch (NumberFormatException e) {
                this.io.write("non integer input");
                continue;
            }
        }
        return true;
    }

    public List<Reference> list() {
        for (Reference reference : references.list()) {
            io.write(reference.toString());
        }
        return null;
    }

    public boolean update() {
        return true;
    }

    public boolean delete() {
        return true;
    }

    private boolean routeCreate(int action) {
        switch (action) {
            case (1):
                this.io.write("Creating book reference");
                references.add(createReference(1));
                break;
            case (0):
                this.io.write("Returning");
                return false;
            default:
                this.io.write("Not a valid command");
                break;
        }
        return true;
    }

    private Reference createReference(int type) {
        Reference reference = null;
        switch (type) {
            case (1):
                reference = new Book();
                //Järkevä toteutus vaatii vielä Reference- ja Book- luokkien
                //modifiointia
                break;
            default:
                break;
        }
    return reference;
    }
}
