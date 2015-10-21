package com.miniprojekti.bibtexbible.logic;

import com.miniprojekti.bibtexbible.domain.Article;
import com.miniprojekti.bibtexbible.domain.Book;
import com.miniprojekti.bibtexbible.domain.InProceedings;
import com.miniprojekti.bibtexbible.domain.Proceedings;
import com.miniprojekti.bibtexbible.domain.Reference;
import com.miniprojekti.bibtexbible.domain.ReferenceList;
import com.miniprojekti.bibtexbible.fileio.Writer;
import com.miniprojekti.misc.Tool;
import static com.miniprojekti.misc.Tool.safeSubstring;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReferenceController {

    private final ReferenceList references;

    public ReferenceController() {
        references = new ReferenceList();
    }

    public Reference create(int type) {
        Reference ref = createReference(type);
        references.add(ref);
        return ref;
    }

    public List<Reference> list() {
        return references.list();
    }

    public void delete(int index) {
        references.delete(index);
    }

    public int export(String filename) {
        try {
            if (!references.list().isEmpty()) {
                Writer writer = new Writer(filename);
                StringBuilder sb = new StringBuilder();
                for (Reference reference : references.list()) {
                    writer.write(reference.toBibTex());
                }
                writer.close();
                return 0;
            } else {
                return 2;
            }
        }
        catch (IOException ex) {
            return 1;
        }
    }

    public int importBibtex(String filename) {
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
            return 0;
        }
        catch (Exception ex) {
            references.clear();
            return 1;
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
