package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.domain.Article;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.InProceedings;
import com.miniprojekti.bibtexbible.domain.Proceedings;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.ui.UI;
import com.miniprojekti.bibtexbible.fileio.Writer;
import com.miniprojekti.misc.Tool;
import static com.miniprojekti.misc.Tool.safeSubstring;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        try {
            if (!references.list().isEmpty()) {
                String filename = ui.askFilename();
                Writer writer = new Writer(filename);
                StringBuilder sb = new StringBuilder();
                for (Reference reference : references.list()) {
                    writer.write(reference.toBibTex());
                }
                ui.printLine("All references exported to " + filename);
                writer.close();
            } else {
                ui.printLine("There are no references to export");
            }
        }
        catch (IOException ex) {
            ui.printLine("Exporting to a file was unsuccessful");
        }

    }

    public void importBibtex() {
        String filename = ui.askFilename();
        try {
            Scanner scanner = new Scanner(new File(filename));
            Reference ref = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = Tool.bibtexToScandis(line);
                if (line.startsWith("}")) {
                    line = safeSubstring(line, 1);
                }
                if (line.startsWith("@")) {
                    // Luodaan uusi reference
                    line = line.substring(1);
                    String[] split = line.split("\\{", 2);
                    String type = split[0]; // esim "Book"
                    String id = split[1].substring(0, split[1].length() - 2); // esim "Hass1994"
                    ref = new Book();
                    if (type.equals("Article")) {
                        ref = new Article();
                    }
                    if (type.equals("InProceedings")) {
                        ref = new InProceedings();
                    }
                    if (type.equals("Proceedings")) {
                        ref = new Proceedings();
                    }
                    ref.setID(id);
                    Tool.addMissingProperties(ref);
                    references.add(ref);
                } else if (!line.isEmpty() && ref != null) {
                    // Kirjataan yksi property ylös
                    String[] split = line.split("=", 2);
                    String label = split[0].substring(0, split[0].length() - 1);
                    String value = safeSubstring(split[1], 2, split[1].length() - 3);
                    ref.setProperty(label, value);
                }
            }
            ui.printLine("Much import very success wow!");
        }
        catch (Exception ex) {
            ui.printLine("Importing from file was unsuccessful. Clearing database...");
            references.clear();
        }

    }

    public ReferenceList getReferenceList() {
        return this.references;
    }

    private Reference createReference(int type) {
        switch (type) {
            case (2):
                return new Article();
            case (3):
                return new Proceedings();
            case (4):
                return new InProceedings();
            default:
                return new Book();
        }
    }
}
