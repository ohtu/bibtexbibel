package com.miniprojekti.bibtexbible;

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
    private UI ui;

    @Before
    public void setUp() {
        ui = mock(ConsoleUI.class);
        controller = new ReferenceController(ui);
    }

    @Test
    public void createAllowsNewBookReferenceToBeAdded() {
        when(ui.selectReferenceType()).thenReturn(1);
        controller.create();
        assertEquals(1, controller.getReferenceList().list().size());
    }

    @Test
    public void createAllowsNewArticleReferenceToBeAdded() {
        when(ui.selectReferenceType()).thenReturn(2);
        controller.create();
        assertEquals(1, controller.getReferenceList().list().size());
        assertEquals(true, controller.getReferenceList().list().get(0) instanceof Article);
    }

    @Test
    public void createAllowsNewProceedingsReferenceToBeAdded() {
        when(ui.selectReferenceType()).thenReturn(3);
        controller.create();
        assertEquals(1, controller.getReferenceList().list().size());
        assertEquals(true, controller.getReferenceList().list().get(0) instanceof Proceedings);
    }

    @Test
    public void listPrintsAllReferences() {
        //TODO
        controller.list();
        verify(ui).printReferences(anyList());
    }

    @Test
    public void deleteDeletesReferenceFromReferenceList() {
        when(ui.selectReferenceType()).thenReturn(1);
        controller.create();
        assertEquals(1, controller.getReferenceList().list().size());
        List<Reference> references = controller.getReferenceList().list();
        when(ui.selectReferenceToDelete(references)).thenReturn(0);
        controller.delete();
        assertEquals(0, controller.getReferenceList().list().size());
    }

    @Test
    public void testUnsuccesfulImport() {
        controller.importBibtex();
        when(ui.askFilename()).thenReturn("diohg8934rhoifdf.bib");
        verify(ui).printLine("Importing from file was unsuccessful. Clearing database...");
    }

    @Test
    public void testSuccesfulImport() {
        when(ui.askFilename()).thenReturn("demodb.bib");
        controller.importBibtex();
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
        when(ui.askFilename()).thenReturn("tilapainentestitiedosto.txt");
        controller.export();
        verify(ui).printLine("There are no references to export");
    }

    @Test
    public void testSuccesfulExport() {
        // import -> export -> clear -> import again -> compare to original
        when(ui.askFilename()).thenReturn("demodb.bib");
        controller.importBibtex();
        List<Reference> original = controller.getReferenceList().list();
        when(ui.askFilename()).thenReturn("tilapainentiedosto.bib");
        controller.export();
        ReferenceList refList = controller.getReferenceList();
        refList.clear();
        controller.importBibtex();
        assertTrue(refList.list().size() == original.size());
        for (Reference ref : original) {
            Reference verrokki = refList.getReference(ref.getID());
            assertEquals(ref, verrokki);
        }
        File poista = new File("tilapainentiedosto.bib");
        poista.delete();
    }

}
