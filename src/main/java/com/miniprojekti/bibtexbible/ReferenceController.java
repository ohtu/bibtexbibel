package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Article;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.Proceedings;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.fileio.BibTexReader;
import com.miniprojekti.bibtexbible.ui.UI;
import com.miniprojekti.bibtexbible.fileio.Writer;
import java.io.IOException;
import java.util.List;

public class ReferenceController {

    private final UI ui;
    private final ReferenceList references;

    public ReferenceController(UI ui) {
        this.ui = ui;
        references = new ReferenceList();
    }

    public void create() {
        int type = ui.selectReferenceType();
        if (type == 0) {
            return;
        }
        Reference reference = createReference(type);

        ui.setProperties(reference);

        references.add(reference);
    }

    public void list() {
        ui.printReferences(references.list());
    }

    public void delete() {
        int index = ui.selectReferenceToDelete(references.list());
        references.delete(index);
    }

    public void export() {
        Writer writer;
        try {
            if (references.list().size() != 0) {
                String filename = ui.askFilename();
                writer = new Writer(filename);
                for (Reference reference : references.list()) {
                    writer.write(reference.toBibTex());
                }
                writer.close();
                ui.printLine("All references exported to file" + writer.getFilename());
            } else {
                ui.printLine("There are no references to export");
            }
        }
        catch (IOException ex) {
            ui.printLine("Exporting to a file was unsuccessful");
        }
    }
    
    public void importReferences() {
        BibTexReader reader;
        try {
            String filename = ui.askFilename();
            reader = new BibTexReader(filename);
            this.references.toReferenceList(reader.read());
        } catch (IOException ex) {
            ui.printLine("Importing from file failed");
        }
    }
    
    public List<Reference> getReferenceList() {
        return this.references.list();
    }

    private Reference createReference(int type) {
        switch (type) {
            case (2):
                return new Article();
            case (3):
                return new Proceedings();
            default:
                return new Book();
        }
    }

}
