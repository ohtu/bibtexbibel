package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.ui.UI;
import com.miniprojekti.bibtexbible.fileio.Writer;

public class ReferenceController {

    private UI ui;
    private ReferenceList references;

    public ReferenceController(UI ui) {
        this.ui = ui;
        references = new ReferenceList();
    }

    public void create() {
        int type = ui.selectReferenceType();
        Reference reference = createReference(type);
        references.add(reference);
    }

    public void list() {
        ui.printReferences(references.list());
    }

    public void delete() {
        int id = ui.selectReferenceToDelete(references.list());
        references.list().remove(id);
    }
    
    public void export() {
        String filename = ui.askFilename();
        Writer writer = new Writer(filename);
        for (Reference reference : references.list()) {
            writer.write(reference.toBibTex());
        }
        ui.printFilename(writer.getFilename());
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
