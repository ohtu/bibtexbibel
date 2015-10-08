package com.miniprojekti.bibtexbible;

import com.miniprojekti.bibtexbible.*;
import com.miniprojekti.bibtexbible.domain.*;
import com.miniprojekti.bibtexbible.ui.*;
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
        assertEquals(1, controller.getReferenceList().size());
    }
    
    @Test
    public void createAllowsNewArticleReferenceToBeAdded() {
        when(ui.selectReferenceType()).thenReturn(2);
        controller.create();
        assertEquals(1, controller.getReferenceList().size());
        assertEquals(true, controller.getReferenceList().get(0) instanceof Article);
    }
    
    @Test
    public void createAllowsNewProceedingsReferenceToBeAdded() {
        when(ui.selectReferenceType()).thenReturn(3);
        controller.create();
        assertEquals(1, controller.getReferenceList().size());
        assertEquals(true, controller.getReferenceList().get(0) instanceof Proceedings);
    }
    
    @Test
    public void listPrintsAllReferences() {
        controller.list();
        verify(ui).printReferences(anyList());
    }
    
    @Test
    public void deleteDeletesReferenceFromReferenceList() {
        when(ui.selectReferenceType()).thenReturn(1);
        controller.create();
        assertEquals(1, controller.getReferenceList().size());
        List<Reference> references = controller.getReferenceList();
        when(ui.selectReferenceToDelete(references)).thenReturn(0);
        controller.delete();
        assertEquals(0, controller.getReferenceList().size());
    }
}