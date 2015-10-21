package com.miniprojekti.bibtexbible.logic;

import com.miniprojekti.bibtexbible.logic.ReferenceController;
import com.miniprojekti.bibtexbible.*;
import com.miniprojekti.bibtexbible.domain.*;
import com.miniprojekti.bibtexbible.ui.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReferenceControllerTest {

    private ReferenceController controller;

    @Before
    public void setUp() {
        controller = new ReferenceController();
    }

    @Test
    public void createAllowsNewBookReferenceToBeAdded() {
        controller.create(1);
        assertEquals(1, controller.getReferenceList().list().size());
    }

    @Test
    public void createAllowsNewArticleReferenceToBeAdded() {
        controller.create(2);
        assertEquals(1, controller.getReferenceList().list().size());
        assertEquals(true, controller.getReferenceList().list().get(0) instanceof Article);
    }

    @Test
    public void createAllowsNewProceedingsReferenceToBeAdded() {
        controller.create(3);
        assertEquals(1, controller.getReferenceList().list().size());
        assertEquals(true, controller.getReferenceList().list().get(0) instanceof Proceedings);
    }

    @Test
    public void listPrintsAllReferences() {
        controller.create(2);
        assertEquals(1, controller.list().size());
        assertEquals(true, controller.list().get(0) instanceof Article);
    }

    @Test
    public void deleteDeletesReferenceFromReferenceList() {
        controller.create(1);
        assertEquals(1, controller.getReferenceList().list().size());
        List<Reference> references = controller.getReferenceList().list();
        controller.delete(0);
        assertEquals(0, controller.getReferenceList().list().size());
    }

    @Test
    public void testUnsuccesfulImport() {
        assertEquals(1, controller.importBibtex("diohg8934rhoifdf.bib"));
    }

    @Test
    public void testSuccesfulImport() {
        controller.importBibtex("demodb.bib");
        HashSet<String> kaytetyt_idt = new HashSet<>();
        List<Reference> list = controller.getReferenceList().list();
        assertTrue(list.size() == 7); // demodb sisältää 7 referenceä
        for (Reference r : list) {
            int filledProperties = 0;
            for (String label : r.getPropertyDescriptions().keySet()) {
                assertNotNull(r.getProperty(label)); // nullit korvattu tyhjilla
                if (!r.getProperty(label).isEmpty()) {
                    filledProperties++;
                }
            }
            assertTrue(filledProperties > 0); // kaikki kentat ei saa olla tyhjia
            assertTrue(!kaytetyt_idt.contains(r.getID()));
            kaytetyt_idt.add(r.getID()); // sama ID ei saa esiintya 2x
        }
    }

    @Test
    public void testEmptyExport() {
        assertEquals(2, controller.export("tilapainentestitiedosto.txt"));
    }

    @Test
    public void testSuccesfulExport() {
        // import -> export -> clear -> import again -> compare to original
        controller.importBibtex("demodb.bib");
        List<Reference> original = controller.getReferenceList().list();
        controller.export("tilapainentiedosto.bib");
        ReferenceList refList = controller.getReferenceList();
        refList.clear();
        controller.importBibtex("tilapainentiedosto.bib");
        assertTrue(refList.list().size() == original.size());
        for (Reference ref : original) {
            Reference verrokki = refList.getReference(ref.getID());
            assertEquals(ref, verrokki);
        }
        File poista = new File("tilapainentiedosto.bib");
        poista.delete();
    }

}
